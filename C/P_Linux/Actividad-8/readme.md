# Actividad 8

Este es el programa que implementa el juego con 4 jugadores, usando colas de mensajes para la sincronización de turnos. 

A continuación se detalla el proceso de los jugadores y cómo se manejan los mensajes en el juego:

Descripción:
- Número de jugadores: Se define como N = 4.
- Colas de mensajes: Se utiliza una cola de mensajes para enviar y recibir información entre los jugadores. Cada jugador tiene su propio proceso, y la cola de mensajes facilita la comunicación entre ellos.
- Juego de turnos: Cada jugador recibe un mensaje indicando que es su turno. Después de intentar jugar o pasar, envía un mensaje al siguiente jugador indicando que es su turno.

1. Estructura del mensaje:
- long tipo: Es el identificador del jugador al que se enviará el mensaje. Cada jugador tiene un identificador único entre 1 y N (en este caso, 1 a 4).
- char texto[100]: Puede ser utilizado para incluir más detalles, pero en este caso no se utiliza.

2. Función jugador(int id, int cola_mensajes):
- Cada jugador espera un mensaje con msgrcv indicando que es su turno.
- Después de recibir el mensaje, el jugador decide si puede jugar o si pasa. Esto se determina aleatoriamente con rand().
- Luego, el jugador envía el mensaje al siguiente jugador utilizando msgsnd. El jugador siguiente es determinado por (id + 1) % N + 1 para garantizar que el turno vuelva al jugador inicial después del último.

3. Función main():
- Crea una cola de mensajes utilizando msgget.
- Se crean los procesos de los jugadores usando fork. Cada proceso ejecutará la función jugador de forma independiente.
- Inicia el juego enviando el primer mensaje al jugador 0 (con msg.tipo = 1).
- Espera a que todos los jugadores terminen con wait(NULL). En este caso, el juego nunca termina porque los jugadores siguen jugando indefinidamente.
- Al final, se elimina la cola de mensajes con msgctl(cola_mensajes, IPC_RMID, NULL).

///

El proceso principal crea la cola de mensajes y luego crea un proceso hijo para cada jugador. Cada jugador es un proceso independiente que maneja su turno.
Cada jugador, a través de un bucle infinito, espera recibir un mensaje que le indique que es su turno. Después de realizar su acción (jugar o pasar), envía el mensaje al siguiente jugador.
El juego comienza enviando un mensaje al primer jugador (jugador 0), y después los jugadores intercambian mensajes para indicar quién es el siguiente en su turno.

Este código utiliza IPC (Inter-Process Communication) con colas de mensajes para permitir que los procesos se comuniquen entre sí.
Los jugadores se gestionan como procesos separados utilizando fork, lo que permite la simulación de un juego de múltiples jugadores en paralelo.
