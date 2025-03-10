#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <sys/wait.h>
#include <sys/time.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <mqueue.h>
#include <string.h>

#define NPROCS 4
#define SERIES_MEMBER_COUNT 200000
#define QUEUE_NAME "/mercator_queue"
#define MAX_SIZE 1024

double get_member(int n, double x)
{
    int i;
    double numerator = 1;
    for(i=0; i<n; i++)
        numerator = numerator * x;
    if (n % 2 == 0)
        return (-numerator / n);
    else
        return numerator / n;
}

void proc(int proc_num, double x_val)
{
    mqd_t mq;
    char buffer[MAX_SIZE];
    double sum = 0;

    // Cada proceso realiza el cálculo de los términos que le tocan
    for(int i = proc_num; i < SERIES_MEMBER_COUNT; i += NPROCS) {
        sum += get_member(i + 1, x_val);
    }

    // Enviamos el resultado al maestro a través de la cola de mensajes
    snprintf(buffer, MAX_SIZE, "%lf", sum);
    mq = mq_open(QUEUE_NAME, O_WRONLY);
    if(mq == -1) {
        perror("Error abriendo la cola de mensajes en el esclavo");
        exit(1);
    }

    if(mq_send(mq, buffer, MAX_SIZE, 0) == -1) {
        perror("Error enviando mensaje desde esclavo");
        exit(1);
    }

    mq_close(mq);
    exit(0);
}

void master_proc()
{
    mqd_t mq;
    struct mq_attr attr;
    char buffer[MAX_SIZE];
    double total_sum = 0;
    double x_val;

    // Obtener el valor de x desde el archivo entrada.txt
    FILE *fp = fopen("entrada.txt", "r");
    if(fp == NULL) {
        perror("Error abriendo archivo entrada.txt");
        exit(1);
    }
    fscanf(fp, "%lf", &x_val);
    fclose(fp);

    // Configuración de la cola de mensajes
    attr.mq_flags = 0;
    attr.mq_maxmsg = 10;
    attr.mq_msgsize = MAX_SIZE;
    attr.mq_curmsgs = 0;

    mq = mq_open(QUEUE_NAME, O_CREAT | O_RDONLY, 0644, &attr);
    if(mq == -1) {
        perror("Error creando la cola de mensajes en el maestro");
        exit(1);
    }

    // Crear procesos esclavos
    for(int i = 0; i < NPROCS; i++) {
        pid_t pid = fork();
        if(pid == 0) {
            proc(i, x_val);
        }
    }

    // Recibir resultados de los esclavos y sumar
    for(int i = 0; i < NPROCS; i++) {
        if(mq_receive(mq, buffer, MAX_SIZE, NULL) == -1) {
            perror("Error recibiendo mensaje en el maestro");
            exit(1);
        }
        total_sum += atof(buffer);
    }

    // Mostrar resultados
    printf("El resultado es %10.8f\n", total_sum);
    printf("Llamando a la función ln(1 + %f) = %10.8f\n", x_val, log(1 + x_val));

    // Cerrar y eliminar la cola de mensajes
    mq_close(mq);
    mq_unlink(QUEUE_NAME);
}

int main()
{
    struct timeval ts;
    long long start_ts, stop_ts, elapsed_time;

    gettimeofday(&ts, NULL);
    start_ts = ts.tv_sec;

    // Llamar al proceso maestro
    master_proc();

    // Esperar a todos los procesos esclavos
    for(int i = 0; i < NPROCS; i++) {
        wait(NULL);
    }

    gettimeofday(&ts, NULL);
    stop_ts = ts.tv_sec;
    elapsed_time = stop_ts - start_ts;
    printf("Tiempo = %lld segundos\n", elapsed_time);

    return 0;
}
