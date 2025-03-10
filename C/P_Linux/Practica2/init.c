#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>

#define NUM_GETTYS 6

int gettys[NUM_GETTYS];

void spawn_getty(int i) {
    pid_t pid = fork();
    if (pid == 0) {
        // Proceso hijo: reemplazar imagen con getty
        execlp("xterm", "xterm", "-e", "./getty", NULL);
        perror("Error al ejecutar getty");
        exit(1);
    } else if (pid > 0) {
        gettys[i] = pid;
    } else {
        perror("Error al crear proceso hijo");
    }
}

void monitor_gettys() {
    int status;
    while (1) {
        pid_t pid = waitpid(-1, &status, 0);
        for (int i = 0; i < NUM_GETTYS; i++) {
            if (gettys[i] == pid) {
                printf("getty %d terminó, reiniciando...\n", i);
                spawn_getty(i);
            }
        }
    }
}

int main() {
    // Crear 6 procesos getty
    for (int i = 0; i < NUM_GETTYS; i++) {
        spawn_getty(i);
    }

    // Monitorear los procesos getty
    monitor_gettys();

    return 0;
}
