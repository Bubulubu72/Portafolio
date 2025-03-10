#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <math.h>
#include <sys/time.h>
#include <pthread.h>

#define SIZE 2000
#define INICIAL 900000000
#define FINAL 1000000000

int mat[SIZE][SIZE];

void initmat(int mat[][SIZE]);
void printnonzeroes(int mat[SIZE][SIZE]);
int isprime(int n);
void *process_row(void *arg);

int main() {
    long long start_ts;
    long long stop_ts;
    long long elapsed_time;
    struct timeval ts;
    pthread_t threads[SIZE];
    int row_indices[SIZE];

    // Initialize the matrix with random numbers
    initmat(mat);

    // Get the start time
    gettimeofday(&ts, NULL);
    start_ts = ts.tv_sec;

    // Create threads to process each row of the matrix
    for (int i = 0; i < SIZE; i++) {
        row_indices[i] = i; // Each thread gets its corresponding row index
        pthread_create(&threads[i], NULL, process_row, &row_indices[i]);
    }

    // Wait for all threads to finish
    for (int i = 0; i < SIZE; i++) {
        pthread_join(threads[i], NULL);
    }

    // Get the end time
    gettimeofday(&ts, NULL);
    stop_ts = ts.tv_sec;
    elapsed_time = stop_ts - start_ts;

    // Print all non-zero numbers in the matrix
    printnonzeroes(mat);

    printf("------------------------------\n");
    printf("TIEMPO TOTAL, %lld segundos\n", elapsed_time);

    return 0;
}

void initmat(int mat[][SIZE]) {
    int i, j;
    srand(getpid());
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            mat[i][j] = INICIAL + rand() % (FINAL - INICIAL);
        }
    }
}

void printnonzeroes(int mat[SIZE][SIZE]) {
    int i, j;
    for (i = 0; i < SIZE; i++) {
        for (j = 0; j < SIZE; j++) {
            if (mat[i][j] != 0) {
                printf("%d\n", mat[i][j]);
            }
        }
    }
}

int isprime(int n) {
    int d = 3;
    int prime = n == 2;
    int limit = sqrt(n);

    if (n > 2 && n % 2 != 0) {
        while (d <= limit && n % d != 0) {
            d += 2;
        }
        prime = d > limit;
    }

    return prime;
}

// Function to process each row of the matrix
void *process_row(void *arg) {
    int row = *(int *)arg; // Get the row index from the argument
    for (int j = 0; j < SIZE; j++) {
        if (!isprime(mat[row][j])) {
            mat[row][j] = 0; // Replace non-prime numbers with 0
        }
    }
    pthread_exit(NULL);
}