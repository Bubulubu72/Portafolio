# Archivos fuente
SOURCES = Practica4.c

# Compilador y banderas
CC = gcc
CFLAGS = -lm

# Nombre del ejecutable
EXECUTABLE = prj4

# Archivo ZIP
ZIPFILE = Practica3.zip

# Regla principal: compila el programa
all: $(EXECUTABLE)

# Regla para compilar el programa principal
$(EXECUTABLE): $(SOURCES)
	$(CC) $(CFLAGS) -o $(EXECUTABLE) $(SOURCES)

# Regla para limpiar los archivos generados
clean:
	rm -f $(EXECUTABLE)

# Regla para crear el archivo ZIP
zip: $(SOURCES)
	zip $(ZIPFILE) $(SOURCES)

# Regla para ejecutar el programa
run: $(EXECUTABLE)
	./$(EXECUTABLE)
