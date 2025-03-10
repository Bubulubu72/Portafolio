# Practica 4 Calculo de Serie (Cola de Msg)

El programa divide el trabajo de calcular una serie matemática entre varios procesos esclavos, con el proceso maestro que recoge los resultados utilizando una cola de mensajes. 
Al final, muestra el tiempo de ejecución y el resultado calculado.

## 1. Función get_member(int n, double x)
Esta función calcula el término n-ésimo de la serie matemática utilizando el valor de x.
Si el término n es par, el valor calculado es negativo, y si es impar, el valor es positivo.

## 2. Proceso esclavo (proc(int proc_num, double x_val))
Cada proceso esclavo calcula una parte de la serie. Los procesos se dividen el trabajo de forma que cada uno se encarga de los términos correspondientes a su número de proceso.
Después de calcular su parte, el proceso esclavo envía el resultado (sumatoria parcial) al proceso maestro a través de una cola de mensajes.

## 3. Proceso maestro (master_proc())
El proceso maestro obtiene el valor de x desde el archivo entrada.txt, que se utilizará en el cálculo de la serie.
Configura una cola de mensajes para recibir los resultados de los procesos esclavos.
Crea varios procesos esclavos y espera recibir sus resultados. Una vez que los recibe, el maestro suma todos los resultados parciales para obtener el resultado final de la serie.
Luego, calcula el logaritmo natural de 1 + x y muestra los resultados.

## 4. Cola de mensajes (MQ)
Se utiliza una cola de mensajes para la comunicación entre los procesos maestro y esclavos. Los procesos esclavos envían sus resultados parciales al maestro a través de esta cola.
La cola de mensajes se configura con un tamaño máximo de mensaje (MAX_SIZE) y se asegura de que se pueda enviar y recibir múltiples mensajes entre los procesos.

## 5. Flujo general del programa:
- Proceso maestro:
  Lee el valor de x desde el archivo entrada.txt.
  Crea la cola de mensajes.
  Lanza varios procesos esclavos para que calculen su parte de la serie.
  Recibe los resultados parciales de los procesos esclavos a través de la cola de mensajes.
  Suma los resultados y muestra el resultado final junto con el logaritmo natural de 1 + x.
- Procesos esclavos:
  Cada proceso esclavo calcula su parte de la serie y la envía al maestro mediante la cola de mensajes.
- Sincronización:
  El maestro espera a que todos los procesos terminen antes de mostrar los resultados.

## 6. Tiempo de ejecución:
El programa calcula el tiempo total que tarda en ejecutarse desde el inicio hasta la obtención del resultado, midiendo la diferencia entre el tiempo al principio (start_ts) y al final (stop_ts).

## 7. Recursos utilizados:
Cola de mensajes (MQ): Se usa para la comunicación entre los procesos maestro y esclavos.
Memoria compartida no utilizada explícitamente: Todos los procesos utilizan el archivo entrada.txt para obtener el valor de x.

## 8. Limpieza de recursos:
Después de finalizar el cálculo, el maestro cierra la cola de mensajes y la elimina.
Los procesos esclavos también cierran la cola después de enviar sus resultados.

## 9. Archivo entrada.txt:
El programa lee el valor de x desde el archivo entrada.txt. Este archivo debe contener un único valor de tipo double.

##

El proceso maestro lee el valor de x desde entrada.txt y luego crea varios procesos esclavos para realizar el cálculo de la serie.
Cada proceso esclavo calcula una parte de la serie y envía su resultado al proceso maestro a través de la cola de mensajes.
El proceso maestro recibe los resultados, los suma y muestra el resultado final junto con el cálculo de ln(1 + x).
El tiempo de ejecución es medido y mostrado al final del programa.

Este enfoque usa colas de mensajes para la comunicación entre procesos, lo que permite una sincronización eficiente y la transmisión de los resultados sin compartir directamente memoria.



