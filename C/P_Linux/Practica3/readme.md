# Practia 3 Calculo de serie

El programa divide el trabajo de calcular una serie matemática en varios procesos (esclavos), con un proceso maestro que recopila los resultados y calcula el valor final.

## 1. Estructura de datos compartidos (SHARED)
- sums[]: Un arreglo para almacenar las sumas parciales de cada proceso esclavo.
- proc_count: Cuenta la cantidad de procesos esclavos que han terminado su trabajo.
- x_val: El valor de x que se obtiene de un archivo de texto (entrada.txt).
- res: El resultado final de la suma de todos los valores calculados por los procesos esclavos.

## 2. Funciones principales:
- get_member(int n, double x): Esta función calcula el término n-ésimo de la serie matemática con base en la fórmula proporcionada. La serie es de tipo alternado, dependiendo de si n es par o impar.
- proc(int proc_num): Es ejecutada por cada proceso esclavo. Este proceso realiza el cálculo de una parte de la serie y luego notifica al proceso maestro que terminó su tarea. Utiliza semáforos para sincronizar la ejecución entre los procesos.
- master_proc(): El proceso maestro se encarga de obtener el valor de x desde el archivo entrada.txt, iniciar los procesos esclavos, esperar a que todos terminen, y luego sumar los resultados de cada proceso esclavo para obtener el resultado final de la serie.

## 3. Memoria compartida y semáforos:
Se usa memoria compartida para que todos los procesos (maestro y esclavos) puedan acceder a las mismas variables y almacenar los resultados.

Los semáforos controlan el acceso a ciertas secciones críticas, como la sincronización entre los procesos, evitando condiciones de carrera.

- sem_start: Semáforo que indica cuándo los procesos esclavos pueden comenzar a trabajar.
- sem_count: Semáforo para controlar el acceso a la variable proc_count (contador de procesos terminados).
- sem_master: Semáforo usado por los procesos esclavos para notificar al proceso maestro cuando han terminado.

## 4. Flujo general del programa:
- Creación de procesos: El proceso principal crea primero la memoria compartida y los semáforos. Luego, crea NPROCS (4 en este caso) procesos esclavos y un proceso maestro.
- Procesos esclavos: Cada proceso esclavo realiza el cálculo de una parte de la serie. Utilizan semáforos para sincronizarse y asegurar que no haya conflictos al actualizar variables compartidas.
- Proceso maestro: Después de que los procesos esclavos hayan calculado sus partes de la serie, el proceso maestro suma los resultados y muestra el resultado final. También mide el tiempo de ejecución total del programa.

## 5. Tiempo de ejecución y resultado:
El programa muestra el tiempo de ejecución total (en segundos) y el resultado final de la serie.
Además, se calcula el logaritmo natural de 1 + x utilizando la función log() de la librería estándar de C.

## 6. Archivo entrada.txt:
El programa lee un valor de x desde el archivo entrada.txt. Este valor se utiliza en los cálculos de la serie. El archivo debe contener un solo número de tipo double.

## 7. Limpieza de recursos:
Después de completar los cálculos, el programa limpia los recursos utilizados, como la memoria compartida y los semáforos.

## 

El proceso maestro lee el valor de x desde entrada.txt y luego inicializa y lanza los procesos esclavos.
Los procesos esclavos calculan porciones de la serie y almacenan sus resultados en la memoria compartida.
El proceso maestro espera a que todos los procesos esclavos terminen, luego suma sus resultados y calcula el valor final.
El programa muestra el tiempo de ejecución y el resultado de la serie junto con el valor del logaritmo natural de 1 + x.

Este enfoque distribuye la carga de trabajo entre varios procesos para aprovechar mejor el procesamiento paralelo.




