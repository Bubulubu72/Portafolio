#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <semaphore.h>
#include <fcntl.h> // Para O_CREAT

#define NPROCS 4
#define SERIES_MEMBER_COUNT 200000

typedef struct {
    double sums[NPROCS];
    int proc_count;
    double x_val;
    double res;
} SHARED;

SHARED *shared;
sem_t *sem_start, *sem_count, *sem_master;  // Semáforos

double get_member(int n, double x) {
    int i;
    double numerator = 1;
    for (i = 0; i < n; i++)
        numerator = numerator * x;
    if (n % 2 == 0)
        return (-numerator / n);
    else
        return numerator / n;
}

void proc(int proc_num) {
    int i;

    // Espera a que el proceso maestro ponga la bandera en 1
    sem_wait(sem_start);

    // Cada proceso realiza el cálculo de los términos que le tocan
    shared->sums[proc_num] = 0;
    for (i = proc_num; i < SERIES_MEMBER_COUNT; i += NPROCS) {
        shared->sums[proc_num] += get_member(i + 1, shared->x_val);
    }

    // Incrementa la variable proc_count usando un semáforo
    sem_wait(sem_count);
    shared->proc_count++;
    sem_post(sem_count);

    // Notifica al proceso maestro que terminó
    sem_post(sem_master);

    exit(0);
}

void master_proc() {
    int i;

    // Obtener el valor de x desde el archivo entrada.txt
    FILE *fp = fopen("entrada.txt", "r");
    if (fp == NULL)
        exit(1);

    fscanf(fp, "%lf", &shared->x_val);
    fclose(fp);

    // El proceso maestro da inicio a los esclavos
    for (i = 0; i < NPROCS; i++) {
        sem_post(sem_start);
    }

    // Espera a que todos los procesos esclavos terminen
    for (i = 0; i < NPROCS; i++) {
        sem_wait(sem_master);
    }

    // Suma el resultado total de cada proceso esclavo
    shared->res = 0;
    for (i = 0; i < NPROCS; i++) {
        shared->res += shared->sums[i];
    }

    exit(0);
}

int main() {
    int shmid;
    int status;
    int p;
    struct timeval ts;
    long long start_ts, stop_ts, elapsed_time;

    // Crear y conectar la memoria compartida
    shmid = shmget(0x1234, sizeof(SHARED), 0666 | IPC_CREAT);
    shared = shmat(shmid, NULL, 0);

    // Inicializar las variables en memoria compartida
    shared->proc_count = 0;

    // Inicializar los semáforos
    sem_start = sem_open("/sem_start", O_CREAT, 0644, 0);
    sem_count = sem_open("/sem_count", O_CREAT, 0644, 1);
    sem_master = sem_open("/sem_master", O_CREAT, 0644, 0);

    // Obtener el tiempo inicial
    gettimeofday(&ts, NULL);
    start_ts = ts.tv_sec;

    // Crear los procesos esclavos
    for (int i = 0; i < NPROCS; i++) {
        p = fork();
        if (p == 0) {
            proc(i);
        }
    }

    // Crear el proceso maestro
    p = fork();
    if (p == 0) {
        master_proc();
    }

    // Esperar a que todos los procesos terminen
    for (int i = 0; i < NPROCS + 1; i++) {
        wait(&status);
        if (status == 0x100) { // Si el proceso maestro termina con error
            fprintf(stderr, "Proceso no puede abrir el archivo de entrada\n");
            break;
        }
    }

    // Obtener el tiempo final
    gettimeofday(&ts, NULL);
    stop_ts = ts.tv_sec;
    elapsed_time = stop_ts - start_ts;

    // Mostrar el resultado
    printf("Tiempo = %lld segundos\n", elapsed_time);
    printf("El resultado es %10.8f\n", shared->res);
    printf("Llamando a la función ln(1 + %f) = %10.8f\n", shared->x_val, log(1 + shared->x_val));

    // Desconectar y eliminar la memoria compartida
    shmdt(shared);
    shmctl(shmid, IPC_RMID, NULL);

    // Cerrar y eliminar los semáforos
    sem_close(sem_start);
    sem_close(sem_count);
    sem_close(sem_master);
    sem_unlink("/sem_start");
    sem_unlink("/sem_count");
    sem_unlink("/sem_master");

    return 0;
}