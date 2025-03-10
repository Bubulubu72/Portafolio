# Actividad 6

Este programa implementa un modelo de productor-consumidor utilizando semáforos y un buffer circular compartido, con sincronización para evitar condiciones de carrera y garantizar el acceso exclusivo a los recursos compartidos.

1. Buffer Circular: El buffer utilizado es circular y tiene una capacidad de 10 elementos. El productor produce datos que son almacenados en el buffer, y el consumidor consume esos datos. Ambos actores interactúan con el buffer de manera controlada usando semáforos para evitar problemas de concurrencia.

2. Sincronización con Semáforos:

Se utilizan dos semáforos para la sincronización:
- mutex: Semáforo binario para garantizar que solo un hilo acceda a las operaciones críticas (insertar o retirar datos del buffer) a la vez.
- condition: Semáforo de condición utilizado para gestionar los casos en los que el productor debe esperar cuando el buffer está lleno, o el consumidor debe esperar cuando el buffer está vacío.
Además, se implementan las funciones enter_monitor(), leave_monitor(), cwait() y cnotify() para manejar las esperas y notificaciones entre hilos.

3. Productor y Consumidor:

- Productor: Produce números enteros y los coloca en el buffer. Si el buffer está lleno, el productor espera hasta que haya espacio disponible.
- Consumidor: Consume los números enteros del buffer. Si el buffer está vacío, el consumidor espera hasta que haya elementos disponibles para consumir.
  
4. Función del Buffer:

- buffer_put(): Esta función permite al productor agregar elementos al buffer. Si el buffer está lleno, el productor espera hasta que haya espacio disponible.
- buffer_get(): Esta función permite al consumidor retirar elementos del buffer. Si el buffer está vacío, el consumidor espera hasta que haya elementos para consumir.

5. Secuencia de Ejecución:

El programa crea dos hilos: uno para el productor y otro para el consumidor.
Cada hilo ejecuta su función correspondiente, con el productor produciendo elementos y el consumidor consumiendo elementos.
Ambos hilos se sincronizan utilizando semáforos y las funciones de espera y notificación para evitar conflictos.

6. Detalles Adicionales:

Se usa usleep() para simular el tiempo de producción y consumo de los elementos, lo que introduce un retraso aleatorio para dar un comportamiento más realista a los hilos.

Beneficios de este enfoque:
Utiliza semáforos y mecanismos de espera y notificación, lo que permite una sincronización eficaz entre los hilos.
Evita condiciones de carrera y asegura que los hilos accedan al buffer de forma segura y controlada.

Este programa es un buen ejemplo de cómo resolver el problema clásico de productor-consumidor utilizando sincronización explícita con semáforos en C.





