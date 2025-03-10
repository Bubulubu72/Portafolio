#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char comando[256]; // Buffer para almacenar el comando ingresado por el usuario

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

        // Ejecutar el comando ingresado
        int resultado = system(comando);

        // Verificar si la ejecución del comando falló
        if (resultado == -1) {
            printf("Error al ejecutar el comando '%s'.\n", comando);
        }
    }

    printf("Shell terminado. ¡Hasta luego!\n");
    return 0;
}