# Proyecto1 (2021)

Este proyecto es una aplicación orientada a objetos en Java que modela un sistema de gestión comercial, permitiendo la administración de clientes, productos, proveedores y transacciones mediante tickets. 
Se basa en la interacción con una base de datos SQLite.


##Estructura del Proyecto

El código está organizado en los siguientes paquetes:

### 1. Clases Principales (src/Clases/)

- Cliente.java: Representa a un cliente dentro del sistema.
- ComercioAPI.java: Maneja la lógica de negocio para la gestión de ventas y operaciones comerciales.
- DBConnection.java: Gestiona la conexión con una base de datos SQLite.
- Fecha.java: Proporciona funciones para manejar fechas dentro del sistema.
- Persona.java: Clase base que contiene atributos comunes a Cliente y Usuario.
- Producto.java: Representa los productos disponibles en el comercio.
- Proveedor.java: Administra la información de los proveedores.
- TablasDB.java: Define la estructura de las tablas dentro de la base de datos.
- Ticket.java: Modela una transacción o compra realizada.
- Usuario.java: Representa un usuario registrado en el sistema.

### 2. Pruebas (src/Test/)

- TestCliente.java, TestProducto.java, TestDB.java, etc.: Clases de prueba que validan el correcto funcionamiento de las entidades y operaciones.

### 3. Dependencias

sqlite-jdbc-3.34.0.jar: Librería utilizada para la conexión con la base de datos SQLite.

##

Este proyecto fue desarrollado como una proyecto Final de Programación Orientada a Objetos en Java, con énfasis en la aplicación de principios de herencia, encapsulamiento y modularidad.
