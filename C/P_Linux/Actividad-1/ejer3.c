#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <número de procesos>\n", argv[0]);
        exit(1);
    }

    int n = atoi(argv[1]);  // Convertir el argumento a un número entero

    if (n <= 0) {
        fprintf(stderr, "El número de procesos debe ser un entero positivo.\n");
        exit(1);
    }

    for (int i = 1; i <= n; i++) {
        pid_t pid = fork();  // Crear un proceso hijo

        if (pid < 0) {  // Error al crear el proceso hijo
            perror("Error al crear el proceso hijo");
            exit(1);
        }

        if (pid == 0) {  // Código del proceso hijo
            printf("Proceso hijo %d\n", i);
            exit(0);  // El hijo termina después de imprimir su número
        }
    }

    // Código del proceso padre: esperar a que todos los hijos terminen
    for (int i = 1; i <= n; i++) {
        wait(NULL);
    }

    printf("Fin\n");
    return 0;
}