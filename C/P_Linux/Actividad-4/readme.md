# Actividad 4

Estos programas en C implementa sistemas de sincronización entre procesos e hilos utilizando semáforos y mutexes, respectivamente. 

El primer programa utiliza semáforos para coordinar dos procesos: un productor que imprime "A", "B" y "C", y un consumidor que imprime "D" y "E", 
  asegurando el orden de impresión a través de la sincronización de los semáforos. 

El segundo programa implementa el clásico problema de productor-consumidor utilizando hilos y un buffer circular. 
  En este caso, el productor genera caracteres y los coloca en el buffer, mientras que el consumidor los extrae y los procesa. 
La sincronización entre los hilos se logra con mutexes y variables de condición para gestionar el acceso al buffer compartido y evitar condiciones de carrera. 
  
Ambos programas utilizan mecanismos de sincronización para asegurar un flujo controlado entre las tareas concurrentes.
