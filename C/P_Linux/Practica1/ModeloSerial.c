#include <stdio.h>
#include <pthread.h>
#include <sys/time.h>
#include "ModeloHilos.h"

int main() {
    struct timeval start, end;

    gettimeofday(&start, NULL);

    double pi = calculoTrapecioSerial2(ITERATIONS);

    gettimeofday(&end, NULL);

    double Tiempo = (end.tv_sec - start.tv_sec) * 1e6;
    Tiempo = (Tiempo + (end.tv_usec - start.tv_usec)) * 1e-6;

    printf("El valor de pi (serial): %.20f\n", pi);
    printf("Tiempo de ejecución (serial): %f segundos\n", Tiempo);

    ///////////////////////////////////////////////////////////////////////////

    pthread_t threads[HILOS];
    pthread_mutex_init(&mutex, NULL);

    gettimeofday(&start, NULL);

    for (long i = 0; i < HILOS; i++) {
        pthread_create(&threads[i], NULL, calculoTrapecio, (void*)i);
    }

    for (long i = 0; i < HILOS; i++) {
        pthread_join(threads[i], NULL);
    }

    double piHilos = (1.0 / ITERATIONS) * total_sum;

    gettimeofday(&end, NULL);

    double TiempoHilos = (end.tv_sec - start.tv_sec) * 1e6;
    TiempoHilos = (TiempoHilos + (end.tv_usec - start.tv_usec)) * 1e-6;

    printf("El valor de pi (hilos): %.20f\n", piHilos);
    printf("Tiempo de ejecución (hilos): %f segundos\n", TiempoHilos);

    pthread_mutex_destroy(&mutex);

    return 0;
}