#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

typedef struct {
    int valor;                  // Valor del semáforo
    pthread_cond_t colaLectores; // Cola de lectores bloqueados
    pthread_cond_t colaEscritores; // Cola de escritores bloqueados
    pthread_mutex_t mutex;     // Mutex para exclusión mutua
} Semaforo;

// Inicialización del semáforo
void initsem(Semaforo *sem, int n) {
    sem->valor = n;
    pthread_mutex_init(&sem->mutex, NULL);
    pthread_cond_init(&sem->colaLectores, NULL);
    pthread_cond_init(&sem->colaEscritores, NULL);
}

// Operación wait (decrementar semáforo)
void waitsem(Semaforo *sem) {
    pthread_mutex_lock(&sem->mutex); // Entrar en sección crítica
    sem->valor--; // Decrementa el valor del semáforo
    if (sem->valor < 0) { // Si el semáforo es negativo
        pthread_cond_wait(&sem->colaLectores, &sem->mutex); // Bloquear el proceso
    }
    pthread_mutex_unlock(&sem->mutex); // Salir de sección crítica
}

// Operación signal (incrementar semáforo)
void signalsem(Semaforo *sem) {
    pthread_mutex_lock(&sem->mutex); // Entrar en sección crítica
    sem->valor++; // Incrementa el valor del semáforo
    if (sem->valor <= 0) { // Si hay procesos bloqueados
        pthread_cond_signal(&sem->colaLectores); // Desbloquear un lector
    }
    pthread_mutex_unlock(&sem->mutex); // Salir de sección crítica
}