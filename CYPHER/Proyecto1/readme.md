# Proyecto de Gestión Académica en Base de Datos con Cypher (Neo4j)

Este proyecto consiste en la creación de un modelo de base de datos utilizando **Cypher Query Language (CQL)** en Neo4j, que representa la relación entre **Carreras**, **Alumnos**, **Materias**, **Profesores** y **Universidades**. La base de datos incluye la creación de nodos, relaciones y consultas que permiten obtener información relevante sobre el sistema académico.

## Descripción

En este repositorio se define un grafo de relaciones académicas en el que se registran carreras, alumnos, materias y profesores, y se representan las relaciones entre estos nodos. El modelo de datos se organiza de la siguiente manera:

- **Carreras**: Cada carrera tiene un nombre.
- **Alumnos**: Los alumnos están asociados a una carrera y a diversas materias en las que están inscritos.
- **Materias**: Las materias están asociadas a profesores y tienen un número de créditos.
- **Profesores**: Los profesores imparten materias específicas.
- **Universidad**: Una universidad, en este caso "ITESO", donde los profesores trabajan.

El modelo también permite realizar consultas que proporcionan información sobre los alumnos y sus interacciones con las materias, así como sobre los profesores que imparten dichas materias.

## Estructura del Código

### 1. Creación de Nodos

El código comienza creando los siguientes nodos:

- **Carreras**: Se crean nodos para las carreras de *Ingeniería en Sistemas Computacionales* y *Ingeniería en Electrónica*.
- **Alumnos**: Se crean nodos para varios alumnos con su nombre y edad.
- **Materias**: Se crean nodos para las materias *Internet de las Cosas*, *Fundamentos en Sistemas Operativos*, *Japonés* y *Bases de Datos para Apoyar la toma de decisiones*.
- **Profesores**: Se crean nodos para los profesores que imparten las materias, como *Mta. Maria*, *Mto. Luis*, *Mto. Hector* y *Mto. Leonardo*.
- **Universidad**: Se crea un nodo para la universidad *ITESO*.

### 2. Creación de Relaciones

Se establecen relaciones entre los nodos de la siguiente manera:

- **Carrera - Alumno**: Los alumnos están asociados a una carrera mediante la relación `[:ESTUDIA]`.
- **Alumno - Materia**: Los alumnos están inscritos en las materias a través de la relación `[:ESTA_INSCRITO]`.
- **Materia - Profesor**: Los profesores imparten las materias mediante la relación `[:IMPARTE]`.
- **Profesor - Universidad**: Los profesores están asociados a la universidad mediante la relación `[:TRABAJAN_EN]`.

### 3. Consultas

El código incluye diversas consultas que permiten extraer información del grafo, tales como:

- Mostrar las materias en las que coinciden los alumnos de la carrera de *Ingeniería en Sistemas Computacionales*.
- Mostrar todos los alumnos que están inscritos en una materia específica, como *Bases de Datos para Apoyar la toma de decisiones*.
- Contar el número de alumnos inscritos en cada materia.
- Obtener todos los alumnos que no toman las mismas materias que un alumno específico, como *Nicolas Teran*.
- Mostrar todos los profesores que trabajan en la universidad *ITESO*.

### 4. Uso de Cypher

Se utiliza **Cypher**, el lenguaje de consultas de Neo4j, para interactuar con el grafo y realizar las operaciones de creación de nodos, relaciones y consultas. Las operaciones están diseñadas para ser fácilmente entendibles y adaptables a otros contextos académicos.
