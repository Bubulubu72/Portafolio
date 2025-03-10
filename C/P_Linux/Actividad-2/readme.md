# Actividad 2

Primer programa: 

  Este programa crea un proceso hijo utilizando fork(). El proceso hijo imprime su PID y el PID de su padre. 
  Luego, el hijo envía una señal SIGTERM al proceso padre usando kill(), lo que termina al proceso padre. Después, el proceso hijo imprime un mensaje indicando que ha terminado al padre y termina su ejecución. 
  El proceso padre queda bloqueado en la función pause(), esperando a que se le envíe una señal. Cuando el proceso padre recibe esta señal, termina su ejecución.

Segundo programa: 

  En este programa, el proceso padre crea un proceso hijo con fork(). 
  El hijo duerme durante 1 segundo (sleep(1)), imprime su PID y un mensaje indicando que ha terminado. Mientras tanto, el proceso padre duerme durante 20 segundos (sleep(20)). 
  El proceso padre imprime su propio mensaje de finalización después de que termine de dormir. Aquí se observa cómo los procesos pueden ejecutarse en paralelo con diferentes tiempos de espera.

Tercer programa: 
  
  Este programa crea un proceso hijo usando fork(). 
  El hijo duerme durante 20 segundos (sleep(20)), mientras que el proceso padre solo duerme 1 segundo. 
  El proceso padre imprime su mensaje de finalización antes de que el hijo termine su espera. 
  El hijo sigue en ejecución después de que el padre termine. 
  Este programa muestra cómo un proceso hijo puede continuar ejecutándose incluso después de que su proceso padre haya finalizado.

Cuarto programa:

  Este programa maneja la creación de un árbol de procesos. 
  El proceso padre crea dos hijos, y cada hijo crea tres procesos nietos. 
  Todos los procesos (padre, hijos y nietos) duermen durante 20 segundos. 
  Antes de que los hijos e nietos terminen, el proceso padre los termina enviando una señal SIGKILL a todos los procesos hijos y nietos. 
  Después, el proceso padre espera a que los procesos hijos e nietos terminen usando waitpid() y luego imprime un mensaje indicando que el proceso padre ha terminado.
