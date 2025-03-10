# Definir el compilador y las opciones de compilación
CC = gcc
CFLAGS = -Wall -g -pthread

# Definir los archivos objeto y el ejecutable
SRCS = ModeloSerial.c ModeloHilos.c
OBJS = $(SRCS:.c=.o)
TARGET = Practica1_Serial

# Regla por defecto: construir el ejecutable
all: $(TARGET)

# Regla para descomprimir el archivo ZIP
$(EXTRACTED_DIR):
	mkdir -p $(EXTRACTED_DIR)
	unzip $(ZIPFILE) -d $(EXTRACTED_DIR)

# Regla para construir el ejecutable a partir de los archivos objeto
$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $@ $(OBJS)

# Regla para compilar los archivos .c en .o
$(EXTRACTED_DIR)/%.o: $(EXTRACTED_DIR)/%.c | $(EXTRACTED_DIR)
	$(CC) $(CFLAGS) -c $< -o $@

# Regla para limpiar los archivos generados
clean:
	rm -f $(TARGET) $(OBJS)
	rm -rf $(EXTRACTED_DIR)

# Regla para crear el archivo ZIP
zip:
	zip -r $(ZIPFILE) $(EXTRACTED_DIR)