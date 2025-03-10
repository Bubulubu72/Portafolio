# Actividad 7

Primer Programa: Semáforo con Lectores y Escritores
Este programa implementa un semáforo con el patrón de Lectores y Escritores, donde múltiples hilos pueden leer de un recurso, pero solo un hilo puede escribir en él a la vez. Para lograr esta sincronización, se utilizan semáforos y condiciones.

Estructura:

Semáforo (Semaforo):

- valor: Controla el semáforo.
- colaLectores y colaEscritores: Son colas de condición para bloquear a los hilos si no pueden proceder.
- mutex: Mutex para garantizar la exclusión mutua al acceder a los recursos.
  
Funciones de Semáforo:

- initsem(): Inicializa el semáforo.
- waitsem(): Decrementa el valor del semáforo y, si es negativo, bloquea al hilo.
- signalsem(): Incrementa el valor del semáforo y desbloquea a los hilos que están esperando.

Patrón Lectores/Escritores:

Se tiene un semáforo que limita cuántos hilos pueden leer y escribir. Los lectores pueden acceder al recurso si no hay escritores, pero si un escritor está en el recurso, los lectores deben esperar.
Uso: Este tipo de semáforo se usa cuando se tiene un recurso compartido y se desea permitir múltiples lectores (que solo leen sin modificar el recurso), pero se garantiza que solo haya un escritor a la vez.

///

Segundo Programa: Buzón con Semáforos
Este programa implementa un buzón de mensajes con semáforos, utilizado para la comunicación entre hilos productor y consumidor. El buzón es un buffer circular que almacena mensajes y se asegura de que los productores y consumidores accedan al buffer de manera segura mediante semáforos.

Estructura:

Buzón (Buzon):

- arregloMensajes: Almacena los mensajes.
- indiceEscritura y indiceLectura: Controlan las posiciones de lectura y escritura en el buffer circular.
- Semáforos:
- semaforo: Para exclusión mutua (evitar condiciones de carrera).
- semaforoEspacios: Controla los espacios disponibles en el buzón.
- semaforoMensajes: Controla la cantidad de mensajes disponibles en el buzón.
  
Funciones:

- initbuzon(): Inicializa el buzón con los semáforos correspondientes.
- send(): Permite a un productor colocar un mensaje en el buzón. Si el buzón está lleno, espera hasta que haya espacio disponible.
- receive(): Permite a un consumidor leer un mensaje del buzón. Si el buzón está vacío, espera hasta que haya un mensaje disponible.
- Uso: Este tipo de sistema de buzón es muy común en la programación concurrente, donde se necesita una comunicación segura y eficiente entre múltiples hilos. Los productores envían mensajes al buzón, y los consumidores los leen, asegurando que no haya conflictos ni pérdida de datos.

Ambos programas utilizan semáforos para la sincronización y exclusión mutua entre hilos, lo que es común en sistemas multihilo para evitar condiciones de carrera y asegurar el acceso ordenado a los recursos compartidos.

