// Definir el número de jugadores
N = 4

// Inicializar una cola de mensajes para cada jugador
cola_mensajes[N]

// Proceso de cada jugador Pi
Proceso Pi:
    mientras (juego_no_termina):
        // Esperar mensaje que indique que es su turno
        recibir_mensaje(cola_mensajes[i])

        // Intentar jugar o pasar
        si (puede_jugar):
            jugar_ficha()
            imprimir("Jugador " + i + " juega una ficha")
        sino:
            imprimir("Jugador " + i + " pasa")

        // Enviar mensaje al siguiente jugador (i + 1) % N
        enviar_mensaje(cola_mensajes[(i + 1) % N])

        // Fin del turno de este jugador

// Proceso inicial que comienza el juego
iniciar_juego():
    // Enviar primer mensaje al jugador P0 para que comience
    enviar_mensaje(cola_mensajes[0])