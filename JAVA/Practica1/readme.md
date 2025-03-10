# Practica 1

JAVA 8
Este repositorio contiene una implementación en Java de un sistema de empleados con diferentes tipos de pago. La estructura del proyecto sigue el paradigma de Programación Orientada a Objetos (POO) y demuestra el uso de herencia, polimorfismo e interfaces.

El objetivo de esta práctica es modelar un sistema de nómina donde se manejan distintos tipos de empleados con cálculos específicos de salario según su modalidad de pago.

##Clases Implementadas

### 1. Empleado (Clase Base)

- Representa un empleado genérico con atributos básicos como nombre, apellidos y número de seguro social.
- Define el método ingresos(), que es sobrescrito por las subclases.

### 2. EmpleadoAsalariado

- Extiende Empleado y representa a un empleado con un salario fijo semanal.
- Implementa la interfaz IFecha para obtener información de fecha.

### 3. EmpleadoPorHoras

- Extiende Empleado y representa a un empleado que recibe un pago basado en el número de horas trabajadas y una tarifa por hora.
- Implementa la interfaz IFecha.

### 4. EmpleadoPorComision

- Extiende Empleado y representa a un empleado cuyo salario depende de un porcentaje de sus ventas mensuales.
- Implementa la interfaz IFecha.

### 5. EmpeladoBaseComision (Nota: el nombre correcto debería ser EmpleadoBaseComision)

- Extiende EmpleadoPorComision y agrega un salario base fijo además de la comisión por ventas.

### 6. IFecha (Interfaz)

- Define constantes y métodos para obtener la fecha actual.
- Implementada en EmpleadoAsalariado, EmpleadoPorHoras y EmpleadoPorComision.

### 7. TestEmpleado

- Clase principal que crea instancias de los diferentes tipos de empleados.
-Muestra la información de cada empleado y calcula su salario semanal.

## Autor
Nicolas Alejandro Teran Yuen.
