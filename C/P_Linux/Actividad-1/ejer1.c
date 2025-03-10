#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    pid_t pid = fork();  // Crear un proceso hijo

    if (pid < 0) {  // Error en la creación del hijo
        perror("Error al crear el proceso hijo");
        exit(1);
    }

    if (pid == 0) {  // Código que ejecuta el proceso hijo
        for (int i = 0; i < 10; i++) {
            printf("Soy el hijo\n");
            sleep(1);  // Pausa de un segundo
        }
        exit(0);  // Termina el proceso hijo
    } else {  // Código que ejecuta el proceso padre
        for (int i = 0; i < 10; i++) {
            printf("Soy el padre\n");
            sleep(1);  // Pausa de un segundo
        }
        wait(NULL);  // Espera a que el proceso hijo termine
        printf("Mi proceso hijo ya ha terminado\n");
    }

   return 0;
}