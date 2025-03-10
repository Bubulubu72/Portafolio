#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <semaphore.h>
#include <pthread.h>

#define TAMBUFFER 10

typedef struct {
    int in; // Posición del siguiente elemento por entrar al buffer
    int out; // Posición del siguiente elemento a retirar del buffer
    int buffer[TAMBUFFER];  // Buffer circular
} BUFFER;

// Semáforos para la sincronización
sem_t mutex;         // Semáforo binario para proteger el acceso al monitor
sem_t condition;     // Semáforo para implementar la cola de condición
int waiting = 0;     // Contador de procesos esperando en la cola de condición

void enter_monitor();
void leave_monitor();
void cwait();
void cnotify();

void buffer_init(BUFFER *bf);
void buffer_put(BUFFER *b, int data);
int buffer_get(BUFFER *b);

void *productor(void *args);
void *consumidor(void *args);

BUFFER buffer;
int elementos = 0;

int main() {
    pthread_t tid[2];

    srand(getpid());

    buffer_init(&buffer);

    // Inicializamos los semáforos
    sem_init(&mutex, 0, 1);        // El semáforo mutex comienza en 1 (disponible)
    sem_init(&condition, 0, 0);    // El semáforo de condición comienza en 0 (ningún hilo esperando)

    pthread_create(&tid[0], NULL, productor, NULL);
    pthread_create(&tid[1], NULL, consumidor, NULL);

    pthread_join(tid[0], NULL);
    pthread_join(tid[1], NULL);
}

void enter_monitor() {
    sem_wait(&mutex);  // Bloquea el acceso al monitor
}

void leave_monitor() {
    sem_post(&mutex);  // Libera el acceso al monitor
}

void cwait() {
    waiting++;         // Incrementa el número de procesos esperando en la cola
    leave_monitor();   // Libera el monitor para que otros puedan entrar
    sem_wait(&condition);  // Espera en la cola de condición
    enter_monitor();   // Vuelve a entrar al monitor cuando es notificado
}

void cnotify() {
    if (waiting > 0) {  // Si hay procesos esperando
        waiting--;      // Decrementa el contador de procesos esperando
        sem_post(&condition);  // Libera a un proceso de la cola de condición
    }
}

void buffer_init(BUFFER *bf) {
    bf->in = 0;
    bf->out = 0;
}

void buffer_put(BUFFER *bf, int e) {
    enter_monitor();

    while (elementos == TAMBUFFER)
        cwait();  // Si el buffer está lleno, espera

    bf->buffer[bf->in] = e;
    bf->in = (bf->in + 1) % TAMBUFFER;
    elementos++;

    cnotify();  // Notifica que hay un espacio disponible

    leave_monitor();
}

int buffer_get(BUFFER *bf) {
    int retval;

    enter_monitor();

    while (elementos == 0)
        cwait();  // Si el buffer está vacío, espera

    retval = bf->buffer[bf->out];
    bf->out = (bf->out + 1) % TAMBUFFER;
    elementos--;

    cnotify();  // Notifica que hay un espacio libre

    leave_monitor();

    return retval;
}

void *productor(void *args) {
    int e = 0;
    int n = 10;
    while (n--) {
        e = e + 1;
        printf("Productor produce %d\n", e);
        buffer_put(&buffer, e);
        usleep(rand() % 3000000);
    }
}

void *consumidor(void *args) {
    int e;
    int n = 10;
    while (n--) {
        e = buffer_get(&buffer);
        printf("\t\t\tConsumidor consume %d\n", e);
        usleep(rand() % 3000000);
    }
}

