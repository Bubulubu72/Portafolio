//*****************************************//
//Creacion de NODOS
//Carreas
MERGE(ISC:Carrera{nombre:'Ingenieria en Sistemas Computacionales'})
MERGE(IE:Carrera{nombre:'Ingenieria en Electronica'})

//Alumnos
MERGE(Nicolas:Alumno{nombre:'Nicolas Teran', edad: 24})
MERGE(Andres:Alumno{nombre:'Andres Lopez', edad: 24})
MERGE(Issac:Alumno{nombre:'Issac Torres', edad: 23})
MERGE(Enrique:Alumno{nombre:'Enrique Flores', edad: 23})

MERGE(Diego:Alumno{nombre:'Diego Carrillo', edad: 22})
MERGE(Francisco:Alumno{nombre:'Francisco Romero', edad: 24})
MERGE(Armando:Alumno{nombre:'Armando Casas', edad: 24})
MERGE(Fernando:Alumno{nombre:'Fernando Garcia', edad: 24})

//Materias
MERGE(IOT:Materia{nombre:'Internet de las Cosas', creditos: 8})
MERGE(FSD:Materia{nombre:'Fundamentos en Sistemas Operativos', creditos: 8})
MERGE(JAP:Materia{nombre:'Japones', creditos: 1})
MERGE(BDATD:Materia{nombre:'Bases de Datos para Apoyar la toma de decisiones', creditos: 8})

//Profesores
MERGE(Maria:Profesor{nombre:'Mta.Maria'})
MERGE(Luis:Profesor{nombre:'Mto.Luis'})
MERGE(Hector:Profesor{nombre:'Mto.Hector'})
MERGE(Leonardo:Profesor{nombre:'Mto.Leonardo'})

//*****************************************//
//Relaciones / Carrera-Alumnos
MERGE(ISC:Carrera{nombre:'Ingenieria en Sistemas Computacionales'})
MERGE(Nicolas:Alumno{nombre:'Nicolas Teran'})
MERGE(Nicolas)-[:ESTUDIA]->(ISC)

MERGE(Andres:Alumno{nombre:'Andres Lopez', edad: 24})
MERGE(Andres)-[:ESTUDIA]->(ISC)

MERGE(Issac:Alumno{nombre:'Issac Torres', edad: 23})
MERGE(Issac)-[:ESTUDIA]->(ISC)

MERGE(Enrique:Alumno{nombre:'Enrique Flores', edad: 23})
MERGE(Enrique)-[:ESTUDIA]->(ISC)

MERGE(IE:Carrera{nombre:'Ingenieria en Electronica'})
MERGE(Diego:Alumno{nombre:'Diego Carrillo', edad: 22})
MERGE(Diego)-[:ESTUDIA]->(IE)

MERGE(Francisco:Alumno{nombre:'Francisco Romero', edad: 24})
MERGE(Francisco)-[:ESTUDIA]->(IE)

MERGE(Armando:Alumno{nombre:'Armando Casas', edad: 24})
MERGE(Armando)-[:ESTUDIA]->(IE)

MERGE(Fernando:Alumno{nombre:'Fernando Garcia', edad: 24})
MERGE(Fernando)-[:ESTUDIA]->(IE)

//*****************************************//
//Relaciones / Alumno-Materia
MERGE(Nicolas:Alumno{nombre:'Nicolas Teran', edad: 24})
MERGE(Andres:Alumno{nombre:'Andres Lopez', edad: 24})
MERGE(Issac:Alumno{nombre:'Issac Torres', edad: 23})
MERGE(Enrique:Alumno{nombre:'Enrique Flores', edad: 23})
MERGE(Diego:Alumno{nombre:'Diego Carrillo', edad: 22})
MERGE(Francisco:Alumno{nombre:'Francisco Romero', edad: 24})
MERGE(Armando:Alumno{nombre:'Armando Casas', edad: 24})
MERGE(Fernando:Alumno{nombre:'Fernando Garcia', edad: 24})
MERGE(IOT:Materia{nombre:'Internet de las Cosas', creditos: 8})
MERGE(FSD:Materia{nombre:'Fundamentos en Sistemas Operativos', creditos: 8})
MERGE(JAP:Materia{nombre:'Japones', creditos: 1})
MERGE(BDATD:Materia{nombre:'Bases de Datos para Apoyar la toma de decisiones', creditos: 8})

MERGE(Nicolas)-[:ESTA_INSCRITO]->(IOT)
MERGE(Nicolas)-[:ESTA_INSCRITO]->(JAP)

MERGE(Andres)-[:ESTA_INSCRITO]->(BDATD)

MERGE(Issac)-[:ESTA_INSCRITO]->(FSD)
MERGE(Issac)-[:ESTA_INSCRITO]->(IOT)
MERGE(Issac)-[:ESTA_INSCRITO]->(BDATD)

MERGE(Enrique)-[:ESTA_INSCRITO]->(JAP)
MERGE(Enrique)-[:ESTA_INSCRITO]->(FSD)

MERGE(Diego)-[:ESTA_INSCRITO]->(BDATD)
MERGE(Diego)-[:ESTA_INSCRITO]->(FSD)

MERGE(Francisco)-[:ESTA_INSCRITO]->(IOT)

MERGE(Armando)-[:ESTA_INSCRITO]->(FSD)
MERGE(Armando)-[:ESTA_INSCRITO]->(JAP)

MERGE(Fernando)-[:ESTA_INSCRITO]->(IOT)
MERGE(Fernando)-[:ESTA_INSCRITO]->(JAP)
MERGE(Fernando)-[:ESTA_INSCRITO]->(BDATD)

//*****************************************//
//Relaciones Materia-Profesor

MERGE(Maria:Profesor{nombre:'Mta.Maria'})
MERGE(Luis:Profesor{nombre:'Mto.Luis'})
MERGE(Hector:Profesor{nombre:'Mto.Hector'})
MERGE(Leonardo:Profesor{nombre:'Mto.Leonardo'})

MERGE(IOT:Materia{nombre:'Internet de las Cosas', creditos: 8})
MERGE(FSD:Materia{nombre:'Fundamentos en Sistemas Operativos', creditos: 8})
MERGE(JAP:Materia{nombre:'Japones', creditos: 1})
MERGE(BDATD:Materia{nombre:'Bases de Datos para Apoyar la toma de decisiones', creditos: 8})

MERGE(Maria)-[:IMPARTE]->(IOT)

MERGE(Luis)-[:IMPARTE]->(FSD)

MERGE(Hector)-[:IMPARTE]->(JAP)

MERGE(Leonardo)-[:IMPARTE]->(BDATD)


//*****************************************//
//Consultas

//-	Muestra una consulta de ti y tus compañeros de carrera, ¿en qué materia coinciden? 
MATCH(c:Carrera{nombre:'Ingenieria en Sistemas Computacionales'})<-[r1:ESTUDIA]-(a:Alumno)-[r2:ESTA_INSCRITO]->(m:Materia)
return c, r1, a, r2, m

//-	Todos los alumnos que toman la materia BDTD
MATCH(a:Alumno)-[r1:ESTA_INSCRITO]->(m:Materia{nombre:'Bases de Datos para Apoyar la toma de decisiones'})<-[r2:IMPARTE]-(p:Profesor)
RETURN a.nombre, p.nombre

//Grafo
MATCH(a:Alumno)-[r1:ESTA_INSCRITO]->(m:Materia{nombre:'Bases de Datos para Apoyar la toma de decisiones'})<-[r2:IMPARTE]-(p:Profesor)
RETURN a, r1, m, r2, p

//-	Por materia muestra el total de alumnos que está inscrito a cada una de ellas.
MATCH(a:Alumno)-[r1:ESTA_INSCRITO]->(m:Materia)
RETURN COUNT(a.nombre), m.nombre

MATCH(a:Alumno)-[r1:ESTA_INSCRITO]->(m:Materia)
RETURN  m.nombre, COUNT(a.nombre), COLLECT(a.nombre)

//-	Agrega el nodo universidad, etiqueta ITESO. Muestra el nodo en el esquema del grafo
MERGE(UNI:Universidad{nombre:'ITESO'})

//-	Relaciona a todos los profesores con el nodo universidad, 
//  la relación es TRABAJAN_EN dirección de profesor a universidad.

MERGE(Maria:Profesor{nombre:'Mta.Maria'})
MERGE(Luis:Profesor{nombre:'Mto.Luis'})
MERGE(Hector:Profesor{nombre:'Mto.Hector'})
MERGE(Leonardo:Profesor{nombre:'Mto.Leonardo'})

MERGE(UNI:Universidad{nombre:'ITESO'})

MERGE(UNI)-[:TRABAJAN_EN]->(Maria)
MERGE(UNI)-[:TRABAJAN_EN]->(Luis)
MERGE(UNI)-[:TRABAJAN_EN]->(Hector)
MERGE(UNI)-[:TRABAJAN_EN]->(Leonardo)

//-	Muestra el nombre de todos los profesores y su relación con ITESO en un grafo
MATCH(p:Profesor)<-[r1:TRABAJAN_EN]-(u:Universidad{nombre:'ITESO'})
RETURN p, r1, u

// -	Muestra otros alumnos que no tengan una relación directa contigo, es decir, 
//      aquellos que no toman las mismas materias que tú.
MATCH (yo:Alumno {nombre: 'Nicolas Teran'})-[:ESTA_INSCRITO]->(m:Materia)
<-[:ESTA_INSCRITO]-(compañero:Alumno)
WITH yo, COLLECT(DISTINCT compañero) AS conectados
MATCH (a:Alumno)
WHERE a <> yo AND NOT a IN conectados
RETURN a.nombre AS Alumno

