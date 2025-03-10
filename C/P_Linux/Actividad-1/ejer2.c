#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void create_process_tree(int current_level, int max_level) {
    // Imprimir el nivel actual
    printf("%d\n", current_level);
    
    // Si no hemos alcanzado el nivel máximo, seguimos creando procesos
    if (current_level < max_level) {
        pid_t pid1 = fork();  // Crear el primer hijo

        if (pid1 < 0) {
            perror("Error al crear el primer proceso hijo");
            exit(1);
        } else if (pid1 == 0) {
            // Este es el primer hijo: recursivamente creamos el árbol para él
            create_process_tree(current_level + 1, max_level);
            exit(0);
        } else {
            // Este es el proceso padre, que ahora crea el segundo hijo
            pid_t pid2 = fork();

            if (pid2 < 0) {
                perror("Error al crear el segundo proceso hijo");
                exit(1);
            } else if (pid2 == 0) {
                // Este es el segundo hijo: recursivamente creamos el árbol para él
                create_process_tree(current_level + 1, max_level);
                exit(0);
            }
        }
    }

    // El proceso espera a que ambos hijos terminen antes de continuar
    wait(NULL);
    wait(NULL);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <nivel>\n", argv[0]);
        exit(1);
    }

    int n = atoi(argv[1]);

    if (n < 1) {
        fprintf(stderr, "El nivel debe ser un número entero positivo.\n");
        exit(1);
    }

    // Empezamos la creación del árbol de procesos desde el nivel 0
    create_process_tree(0, n);

  return 0;
}