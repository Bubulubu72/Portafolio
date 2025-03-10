#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#define N 4 // Número de jugadores

struct mensaje {
    long tipo;
    char texto[100];
};

// Función de cada jugador
void jugador(int id, int cola_mensajes) {
    struct mensaje msg;
    while (1) {
        // Recibir el mensaje de turno
        msgrcv(cola_mensajes, &msg, sizeof(msg), id + 1, 0);

        // Determinar si el jugador puede jugar
        if (rand() % 2 == 0) {
            printf("Jugador %d juega una ficha\n", id);
        } else {
            printf("Jugador %d pasa\n", id);
        }

        // Enviar el turno al siguiente jugador
        msg.tipo = (id + 1) % N + 1;
        msgsnd(cola_mensajes, &msg, sizeof(msg), 0);
    }
}

int main() {
    int cola_mensajes;
    struct mensaje msg;

    // Crear cola de mensajes
    cola_mensajes = msgget(IPC_PRIVATE, 0666 | IPC_CREAT);

    // Crear procesos para los jugadores
    for (int i = 0; i < N; i++) {
        if (fork() == 0) {
            jugador(i, cola_mensajes);
            exit(0);
        }
    }

    // Iniciar el juego enviando el primer mensaje al jugador 0
    msg.tipo = 1; // El primer mensaje es para el jugador P0
    msgsnd(cola_mensajes, &msg, sizeof(msg), 0);

    // Esperar a los jugadores (nunca termina en este ejemplo)
    for (int i = 0; i < N; i++) {
        wait(NULL);
    }

    // Eliminar la cola de mensajes
    msgctl(cola_mensajes, IPC_RMID, NULL);

    return 0;
}