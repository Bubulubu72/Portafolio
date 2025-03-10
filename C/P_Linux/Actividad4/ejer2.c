#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid = fork();

    if (pid < 0) {
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        printf("Proceso hijo: PID = %d, durmiendo por 1 segundo.\n", getpid());
        sleep(1); 
        printf("Proceso hijo: PID = %d, ha terminado.\n", getpid());
        exit(EXIT_SUCCESS); 
    } else {
        printf("Proceso padre: PID = %d, durmiendo por 20 segundos.\n", getpid());
        sleep(20);
        printf("Proceso padre: PID = %d, ha terminado.\n", getpid());
    }

    return 0;
}