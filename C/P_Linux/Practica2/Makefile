# Archivos fuente
SOURCES = init.c getty.c sh.c

# Compilador y banderas
CC = gcc
CFLAGS = -Wall

# Archivo ZIP
ZIPFILE = Practica2.zip

# Regla principal: compila todos los programas
all: $(TARGETS)

# Regla para compilar init
init: init.c
	$(CC) $(CFLAGS) -o init init.c

# Regla para compilar getty
getty: getty.c
	$(CC) $(CFLAGS) -o getty getty.c

# Regla para compilar sh
sh: sh.c
	$(CC) $(CFLAGS) -o sh sh.c

# Regla para limpiar los archivos generados
clean:
	rm -f $(TARGETS)

# Regla para crear el archivo ZIP
zip: $(SOURCES)
	zip $(ZIPFILE) $(SOURCES)

# Regla para ejecutar todos los programas
run: all
	./init && ./getty && ./sh

.PHONY: all clean zip run
