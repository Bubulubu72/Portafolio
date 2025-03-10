#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>

int main() {
    pid_t pid = fork();  

    if (pid < 0) {
        perror("fork failed");
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {
        printf("Proceso hijo: PID = %d, PPID = %d\n", getpid(), getppid());

        kill(getppid(), SIGTERM);

        printf("Proceso hijo ha terminado al padre.\n");
        exit(EXIT_SUCCESS);

    } else {
        printf("Proceso padre: PID = %d\n", getpid());

        pause();
        
        printf("Proceso padre terminando...\n");
    }

    return 0;
}