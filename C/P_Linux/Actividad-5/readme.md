# Actividad 5
Estos dos programas en C implementan un sistema de sincronización utilizando semáforos y control de acceso en un entorno de múltiples procesos e hilos.

Primer programa:

Este programa simula un juego con cuatro jugadores, donde cada uno tiene su turno para jugar. 
  Utiliza un sistema de semáforos para controlar el acceso a la sección crítica (la jugada de cada jugador). Los semáforos se inicializan de la siguiente manera:

  semaforo[0] = 1: El jugador 0 puede comenzar.
  semaforo[1], semaforo[2], semaforo[3] = 0: Los jugadores 1, 2 y 3 están bloqueados.
  Cada jugador espera su turno utilizando el semáforo correspondiente (wait(semaforo[id])), realiza su jugada y luego desbloquea al siguiente jugador (signal(semaforo[(id + 1) % 4])). 
  Los jugadores se ejecutan en paralelo, y este esquema asegura que solo un jugador realice su jugada a la vez, en un ciclo continuo de turnos.

Segundo programa:

En este programa, se implementa un sistema de semáforo usando hilos. Cada hilo ejecuta una función que entra en una sección crítica, donde realiza alguna tarea (en este caso, imprimir un mensaje), y luego sale. 
  La sincronización se maneja con un semáforo (SEMAPHORE), que se inicializa en 1 para permitir el acceso a un solo hilo a la vez. 
  Los hilos utilizan la función wait(&sem) para bloquearse antes de entrar a la sección crítica y signal(&sem) para liberar el semáforo una vez que terminan su tarea. 
  Cada hilo ejecuta esta operación varias veces, con pausas aleatorias entre cada ciclo, para simular un comportamiento concurrente. 
  El semáforo asegura que los hilos accedan a la sección crítica de manera exclusiva y sin interferencias.

Ambos programas usan semáforos para controlar el acceso concurrente a recursos compartidos, ya sea entre jugadores en un juego o hilos ejecutando tareas críticas.
La sincronización se logra con semáforos implementados con mutexes y variables de condición.
