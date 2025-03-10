semaforo[0] = 1 // El jugador P0 puede empezar
semaforo[1] = 0 // Jugador P1 está bloqueado
semaforo[2] = 0 // Jugador P2 está bloqueado
semaforo[3] = 0 // Jugador P3 está bloqueado

// Función que ejecuta cada jugador
Proceso jugador(int id) {
    while (true) {
        wait(semaforo[id])  // Espera su turno (decrementa semáforo)
        
        // Sección crítica: el jugador realiza su movimiento
        print("Jugador ", id, " hace su jugada")
        
        // Turno pasa al siguiente jugador en sentido de las manecillas del reloj
        signal(semaforo[(id + 1) % 4])  // Desbloquea al siguiente jugador
    }
}

// Procesos concurrentes para los 4 jugadores
paralelo {
    jugador(0)  // Jugador P0
    jugador(1)  // Jugador P1
    jugador(2)  // Jugador P2
    jugador(3)  // Jugador P3
}
