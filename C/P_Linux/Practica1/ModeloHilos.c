#include "ModeloHilos.h"

double total_sum = 0.0;
pthread_mutex_t mutex;

double calculoTrapecioSerial2(long n) {
    if (n % 2 != 0) n++;
    double h = 1.0 / (double)n;
    double sum = 0.0;

    for (long i = 1; i < n; i++) {
        double x = h * (double)i;
        sum += 4.0 / (1.0 + x * x);
    }

    double f0 = 4.0 / (1.0 + 0.0 * 0.0);
    double fn = 4.0 / (1.0 + 1.0 * 1.0);

    return (h / 2.0) * (f0 + 2.0 * sum + fn);
}

void* calculoTrapecio(void* arg) {
    long thread_id = (long)arg;
    double local_sum = 0.0;
    double h = 1.0 / (double)ITERATIONS;
    long n_per_thread = ITERATIONS / HILOS;
    long start = thread_id * n_per_thread;
    long end = (thread_id + 1) * n_per_thread;

    for (long i = start; i < end; i++) {
        double x = h * (double)i;
        local_sum += 4.0 / (1.0 + x * x);
    }

    pthread_mutex_lock(&mutex);
    total_sum += local_sum;
    pthread_mutex_unlock(&mutex);

    return NULL;
}
