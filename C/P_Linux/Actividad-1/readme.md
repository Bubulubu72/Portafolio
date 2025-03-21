# Actividad 1
Primer programa: 

Este programa en C crea un proceso hijo utilizando fork() y realiza una ejecución concurrente de dos procesos: el proceso padre y el proceso hijo. 
  El proceso hijo imprime "Soy el hijo" diez veces con un intervalo de un segundo, mientras que el proceso padre hace lo mismo con el mensaje "Soy el padre". 
  Después de que ambos procesos completan sus ciclos de impresión, el proceso padre espera a que el hijo termine utilizando wait() y luego imprime "Mi proceso hijo ya ha terminado". 
  El propósito de este programa es ilustrar la creación y manejo de procesos concurrentes con fork().

Segundo programa: 

  Este programa implementa la creación de un árbol de procesos mediante un enfoque recursivo. 
  El proceso raíz (nivel 0) crea dos hijos en cada nivel, hasta alcanzar un nivel máximo especificado por el usuario. 
  Cada proceso imprime su nivel en el árbol, y después espera a que ambos hijos terminen antes de continuar. 
  Esto se hace utilizando la función wait() para asegurar que el proceso padre no continúe hasta que ambos hijos hayan finalizado. 
  Este programa es útil para entender cómo los procesos pueden crear otros procesos en una estructura jerárquica.

Tercer programa: 

  En este programa, el proceso padre crea n procesos hijos, donde cada hijo imprime su número (identificador) y luego termina. 
  El proceso padre espera a que todos los procesos hijos terminen utilizando wait() antes de imprimir "Fin". 
  Este programa demuestra cómo manejar múltiples procesos hijos y coordinar su ejecución, asegurando que el padre espere la finalización de todos los hijos antes de terminar su ejecución.

Cuarto programa: 

  Este programa simula una shell simple en C que permite al usuario ingresar comandos para ejecutar programas del sistema. Utiliza la función system() para ejecutar los comandos proporcionados por el usuario, 
  y el programa continúa ejecutándose hasta que el usuario ingresa el comando "exit". 
  Si el comando ingresado no puede ser ejecutado, se muestra un mensaje de error. Este programa es útil para comprender cómo crear una shell interactiva básica que ejecute comandos del sistema.

Quinto programa: 

  Este programa mejora el anterior al permitir que el usuario ingrese comandos con varios argumentos. 
  Después de que el usuario introduce un comando, el programa crea un proceso hijo mediante fork() y ejecuta el comando utilizando execvp(). 
  Si el comando tiene argumentos, estos se separan y pasan a execvp(). El proceso padre espera a que el hijo termine antes de continuar. 
  Este programa es una versión más avanzada de una shell, que permite ejecutar programas con múltiples argumentos y manejar procesos hijos de manera más flexible.
