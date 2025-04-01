# Proyecto de Base de Datos Multidimensional

## Descripción
Este proyecto implementa una base de datos multidimensional para el análisis de información de una cafetería. Utiliza un esquema de dimensiones y hechos para facilitar consultas analíticas, optimizando el desempeño mediante técnicas de almacenamiento estructurado.

## Bases de Datos Multidimensionales y OLAP
Las bases de datos multidimensionales permiten organizar la información en **cubos OLAP (Online Analytical Processing)**, que ofrecen una estructura optimizada para el análisis de datos. Estos cubos contienen:
- **Dimensiones**: Representan categorías de análisis, como tiempo, producto o ubicación.
- **Hechos**: Son valores numéricos medibles, como ventas o ingresos.

Operaciones clave en OLAP:
- **Slice:** Extraer una "rebanada" del cubo fijando una dimensión.
- **Dice:** Extraer un subconjunto basado en múltiples dimensiones.
- **Drill-down:** Desglosar datos en niveles más detallados.
- **Roll-up:** Agrupar datos en niveles más generales.
- **Pivot:** Reorientar las dimensiones del cubo.

Este modelo mejora el desempeño en consultas analíticas y facilita la toma de decisiones basada en datos históricos y agregaciones.

## Esquema de la Base de Datos
La base de datos incluye las siguientes tablas:
- **DIM_ALIMENTO:** Contiene información sobre los alimentos vendidos.
- **DIM_CAJERO:** Almacena datos de los cajeros de la cafetería.
- **DIM_TIEMPO:** Registra fechas relevantes para análisis temporal.
- **FACT_VENTAS:** Contiene las transacciones realizadas, relacionando dimensiones para análisis de ventas.

## Instalación y Uso
1. Abrir un servidor de base de datos compatible con SQL Server.
2. Ejecutar el archivo `PROYECTOBD.sql` para crear las tablas y estructuras necesarias.
3. Utilizar herramientas de análisis OLAP o consultas SQL para obtener insights de los datos.

## Consultas de Ejemplo
Algunas consultas incluidas en el proyecto permiten analizar tendencias de ventas, productos más vendidos y desempeño de los cajeros. Se pueden modificar para necesidades específicas del negocio.

## Contribuciones
Si deseas mejorar este proyecto, puedes enviar un pull request con mejoras en la estructura, optimización de consultas o implementación de nuevas dimensiones de análisis.

