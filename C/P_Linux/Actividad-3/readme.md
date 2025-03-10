# Actividad 3

Primer programa:
Este programa realiza un cálculo de números primos en una matriz de 2000x2000 utilizando múltiples hilos (threads). Cada hilo procesa una fila de la matriz y reemplaza los números que no son primos por ceros.

- Inicialización de la matriz: La matriz se llena con números aleatorios entre 900,000,000 y 1,000,000,000.
- Cálculo de primos: Cada hilo se encarga de una fila de la matriz, y verifica si cada número es primo usando la función isprime(). Si no lo es, se reemplaza por un cero.
- Manejo de tiempo: El programa mide el tiempo total de ejecución utilizando la función gettimeofday() antes y después de procesar las filas de la matriz.
- Resultados: Después de que todos los hilos terminan, imprime todos los números diferentes de cero en la matriz y muestra el tiempo total de ejecución.

Segundo programa:
Este programa utiliza hilos para calcular una aproximación del logaritmo natural de 
1+𝑥 mediante una serie de potencias. El cálculo se distribuye entre varios hilos para paralelizar el proceso.

- Entrada del valor de x: El programa pide al usuario que ingrese el valor de 𝑥
- División del trabajo: El trabajo se divide entre los hilos. Cada hilo calcula una parte de la serie de potencias que representa el logaritmo natural de 1 + 𝑥.
- Cálculo de la serie: Cada hilo ejecuta la función compute_series(), que suma los términos de la serie según la fórmula:

![image](https://github.com/user-attachments/assets/f7b0e918-864d-48be-bd16-47a0022afae2)

- Manejo de tiempo: El tiempo de ejecución se mide de manera similar al primer programa, usando gettimeofday().
- Resultados: Una vez que todos los hilos han terminado, el resultado final se imprime junto con el tiempo total de ejecución.

Ambos programas demuestran el uso de hilos (threads) para paralelizar tareas intensivas en cómputo, como el procesamiento de grandes matrices o el cálculo de series numéricas. Los tiempos de ejecución se miden para analizar la eficiencia de la paralelización.
