#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    char comando[256]; // Buffer para almacenar el comando ingresado por el usuario
    char *args[10];    // Argumentos para el comando, asumimos un máximo de 10 argumentos

    while (1) {
        // Pedir al usuario que ingrese un comando
        printf("Ingresa el nombre del programa a ejecutar (o 'exit' para salir): ");
        fgets(comando, sizeof(comando), stdin);

        // Remover el carácter de nueva línea '\n' que fgets añade al final de la cadena
        comando[strcspn(comando, "\n")] = 0;

        // Verificar si el usuario quiere salir
        if (strcmp(comando, "exit") == 0) {
            break;
        }

        // Crear un proceso hijo
        pid_t pid = fork();

        if (pid < 0) {  // Error al crear el proceso hijo
            perror("Error al crear el proceso hijo");
            exit(1);
        } else if (pid == 0) {  // Código del proceso hijo
            // Separar el comando y los argumentos
            int i = 0;
            args[i] = strtok(comando, " "); // Primer argumento
            while (args[i] != NULL) {
                i++;
                args[i] = strtok(NULL, " "); // Siguientes argumentos
            }

            // Ejecutar el comando
            if (execvp(args[0], args) == -1) {
                perror("Error al ejecutar el comando");
                exit(1);
            }
        } else {  // Código del proceso padre
            // Esperar a que el proceso hijo termine
            wait(NULL);
        }
    }

    printf("Shell terminado. ¡Hasta luego!\n");
    return 0;
}