#include <stdio.h>
#include <math.h>
#include <sys/time.h>
#include <pthread.h>

#define ITERS 1000000000
#define NUM_THREADS 4  // You can adjust this based on your CPU cores

typedef struct {
    int start;
    int end;
    float x;
    double partial_sum;
} ThreadData;

void *compute_series(void *arg) {
    ThreadData *data = (ThreadData *)arg;
    double sum = 0.0;
    for (int n = data->start; n < data->end; n++) {
        sum += pow(-1, n + 1) * pow(data->x, n) / n;
    }
    data->partial_sum = sum;
    pthread_exit(NULL);
}

int main() {
    long long start_ts;
    long long stop_ts;
    long long elapsed_time;
    struct timeval ts;
    float x;
    double sum = 0.0;
    pthread_t threads[NUM_THREADS];
    ThreadData thread_data[NUM_THREADS];

    printf("Calcular el logaritmo natural de 1+x\n");
    printf("Dame el valor de x :");
    scanf("%f", &x);

    gettimeofday(&ts, NULL);
    start_ts = ts.tv_sec; // Tiempo inicial

    // Divide the work among threads
    int chunk_size = ITERS / NUM_THREADS;
    for (int i = 0; i < NUM_THREADS; i++) {
        thread_data[i].start = i * chunk_size + 1;
        thread_data[i].end = (i + 1) * chunk_size + 1;
        thread_data[i].x = x;
        pthread_create(&threads[i], NULL, compute_series, &thread_data[i]);
    }

    // Wait for all threads to finish and accumulate results
    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
        sum += thread_data[i].partial_sum;
    }

    gettimeofday(&ts, NULL);
    stop_ts = ts.tv_sec; // Tiempo final
    elapsed_time = stop_ts - start_ts;

    printf("Resultado = %.4lf\n", sum);
    printf("------------------------------\n");
    printf("TIEMPO TOTAL, %lld segundos\n", elapsed_time);

    return 0;
}