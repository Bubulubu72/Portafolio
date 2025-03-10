# Practica 1 Modelos de Hilos

1. ModeloHilos.h (Archivo de encabezado)
  Objetivo: Define las estructuras, constantes, y las funciones que serán utilizadas en la implementación.

Detalles:
- Se definen las constantes ITERATIONS (número de iteraciones) y HILOS (número de hilos).
- Se declara una variable externa total_sum para almacenar la suma total, que es compartida entre los hilos.
- Se declara un mutex para sincronizar el acceso a la variable total_sum.
- Se declaran dos funciones principales: calculoTrapecioSerial2 para el cálculo serial y calculoTrapecio para el cálculo paralelo con hilos.

2. ModeloHilos.c (Implementación de funciones)
  Objetivo: Implementa la lógica del cálculo de π (pi) usando el método del trapecio de forma serial y paralela.

Detalles:
- calculoTrapecioSerial2: Esta función implementa el cálculo serial de π. Se utiliza el método de integración por trapecios, donde la integral se aproxima sumando áreas de trapecios. La función calcula la suma de términos dentro de un intervalo determinado, asegurando que el número de divisiones sea par para obtener una mejor aproximación.
- calculoTrapecio: Esta es la función que se ejecuta en cada hilo. Divide el trabajo entre los hilos, donde cada hilo calcula una porción de la integral. Utiliza un mutex para sincronizar el acceso a la variable total_sum, que es compartida entre todos los hilos. Los hilos calculan en paralelo diferentes segmentos de la integral, y al final se suman los resultados parciales.

3. ModeloSerial.c (Programa principal)
  Objetivo: Controla la ejecución tanto del cálculo serial como del cálculo paralelo, midiendo el tiempo de ejecución de ambas.

Detalles:
- Cálculo Serial: Primero se calcula el valor de π de manera serial utilizando la función calculoTrapecioSerial2. Se mide el tiempo que tarda en ejecutarse este cálculo.
- Cálculo Paralelo con Hilos: Luego, se crea un conjunto de hilos (4 en este caso) que ejecutan la función calculoTrapecio para realizar el cálculo en paralelo. Los hilos dividen el trabajo, y al final se obtiene el resultado final de π.
- Medición de tiempos: Se utilizan las funciones gettimeofday para medir el tiempo de ejecución en ambos casos (serial y paralelo).
- Resultados: Al final, el programa imprime los valores calculados de π tanto en el caso serial como en el paralelo, junto con el tiempo que tardó cada uno.

El programa tiene como objetivo comparar el rendimiento de un cálculo serial de la aproximación de π usando el método del trapecio con una versión paralela utilizando hilos. 
El cálculo paralelo distribuye el trabajo entre varios hilos para mejorar la eficiencia y reducir el tiempo de ejecución.

