#ifndef MODELOHILOS_H
#define MODELOHILOS_H

#include <pthread.h>

#define ITERATIONS 1000000000 // 1,000,000,000 de iteraciones
#define HILOS 4 // Número de hilos

extern double total_sum;
extern pthread_mutex_t mutex;

double calculoTrapecioSerial2(long n);
void* calculoTrapecio(void* arg);

#endif 