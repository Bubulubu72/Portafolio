#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>

// Función para terminar todos los procesos hijos
void kill_all_children(pid_t *children, int num_children) {
    for (int i = 0; i < num_children; i++) {
        if (children[i] > 0) {
            kill(children[i], SIGKILL);
            printf("Proceso hijo o nieto terminado: PID = %d\n", children[i]);
        }
    }
}

int main() {
    pid_t children[8];  
    int child_index = 0;

    for (int i = 0; i < 2; i++) {
        pid_t pid = fork();
        if (pid < 0) {
            perror("Error al crear proceso hijo");
            exit(EXIT_FAILURE);
        }

        if (pid == 0) {
            for (int j = 0; j < 3; j++) {
                pid_t pid_nieto = fork();
                if (pid_nieto < 0) {
                    perror("Error al crear proceso nieto");
                    exit(EXIT_FAILURE);
                }
                if (pid_nieto == 0) {
                    printf("Proceso nieto: PID = %d, padre PID = %d\n", getpid(), getppid());
                    sleep(20); 
                    exit(EXIT_SUCCESS);
                } else {
                    children[child_index++] = pid_nieto;
                }
            }
            printf("Proceso hijo: PID = %d, padre PID = %d\n", getpid(), getppid());
            sleep(20); 
            exit(EXIT_SUCCESS);
        } else {
            children[child_index++] = pid;
        }
    }
    sleep(5);

    kill_all_children(children, child_index);

    for (int i = 0; i < child_index; i++) {
        waitpid(children[i], NULL, 0);
    }

    printf("Proceso padre ha terminado.\n");

    return 0;
}