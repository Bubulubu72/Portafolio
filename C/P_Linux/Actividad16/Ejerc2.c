#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define MAX_MSG 10 // Tamaño máximo del buzón

typedef struct {
    char *arregloMensajes[MAX_MSG]; // Arreglo para mensajes
    int indiceEscritura;             // Índice de escritura
    int indiceLectura;               // Índice de lectura
    Semaforo semaforo;               // Semáforo para exclusión mutua
    Semaforo semaforoEspacios;       // Semáforo para controlar espacios
    Semaforo semaforoMensajes;       // Semáforo para controlar mensajes
} Buzon;

// Inicialización del buzón
void initbuzon(Buzon *buzon, int tamaño) {
    initsem(&buzon->semaforo, 1); // Semáforo para exclusión mutua
    initsem(&buzon->semaforoEspacios, tamaño); // Semáforo para espacios disponibles
    initsem(&buzon->semaforoMensajes, 0); // Semáforo para mensajes disponibles
    buzon->indiceEscritura = 0;
    buzon->indiceLectura = 0;
}

// Enviar un mensaje
void send(Buzon *buzon, char *mensaje) {
    waitsem(&buzon->semaforoEspacios); // Esperar a que haya espacio
    waitsem(&buzon->semaforo); // Entrar en sección crítica
    buzon->arregloMensajes[buzon->indiceEscritura] = mensaje; // Guardar el mensaje
    buzon->indiceEscritura = (buzon->indiceEscritura + 1) % MAX_MSG; // Actualizar el índice
    signalsem(&buzon->semaforo); // Salir de la sección crítica
    signalsem(&buzon->semaforoMensajes); // Incrementar el contador de mensajes disponibles
}

// Recibir un mensaje
char* receive(Buzon *buzon) {
    waitsem(&buzon->semaforoMensajes); // Esperar a que haya un mensaje
    waitsem(&buzon->semaforo); // Entrar en sección crítica
    char *mensaje = buzon->arregloMensajes[buzon->indiceLectura]; // Leer el mensaje
    buzon->indiceLectura = (buzon->indiceLectura + 1) % MAX_MSG; // Actualizar el índice
    signalsem(&buzon->semaforo); // Salir de la sección crítica
    signalsem(&buzon->semaforoEspacios); // Incrementar el contador de espacios disponibles
    return mensaje; // Devolver el mensaje recibido
}