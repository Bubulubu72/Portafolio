#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

void execute_command(char *command) {
    char *args[100];
    char *token = strtok(command, " ");
    int i = 0;
    while (token != NULL) {
        args[i] = token;
        i++;
        token = strtok(NULL, " ");
    }
    args[i] = NULL;

    if (fork() == 0) {
        // Proceso hijo: ejecutar comando
        if (execvp(args[0], args) == -1) {
            perror("Error al ejecutar comando");
        }
        exit(1);
    } else {
        wait(NULL); // Esperar a que el comando termine
    }
}

int main() {
    char command[100];
    
    while (1) {
        printf("sh > ");
        fgets(command, sizeof(command), stdin);
        command[strcspn(command, "\n")] = 0; // Eliminar el salto de línea
        
        if (strcmp(command, "exit") == 0) {
            exit(0); // Salir del shell
        } else if (strcmp(command, "shutdown") == 0) {
            // Terminar todos los procesos
            kill(0, SIGTERM); // Enviar señal de terminación
        } else {
            execute_command(command); // Ejecutar comando normal
        }
    }

    return 0;
}
