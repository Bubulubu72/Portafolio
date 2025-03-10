# Practica 2 Simulacion de Shell con user

## 1. getty.c
  Objetivo: Este programa simula el proceso de autenticación y la gestión de sesiones de usuario. Se encarga de pedir un nombre de usuario y una contraseña, verificar su validez y, si son correctos, iniciar un shell interactivo.

Detalles:
- validate_user: Función que valida si el nombre de usuario y la contraseña ingresados coinciden con los registros guardados en el archivo passwd. Si encuentra una coincidencia, retorna 1 (usuario validado); si no, retorna 0.
- login_loop: Función que inicia un ciclo donde el sistema solicita continuamente un nombre de usuario y una contraseña hasta que el usuario se autentica correctamente. Cuando un usuario es autenticado, se crea un proceso hijo que ejecuta un shell (./sh).
- main: Llama a login_loop para iniciar el proceso de autenticación.

## 2. init.c
  Objetivo: Este programa actúa como el inicializador del sistema, creando múltiples instancias del proceso getty para gestionar las sesiones de usuario de manera concurrente.

Detalles:
- spawn_getty: Función que crea un proceso hijo para ejecutar el programa getty dentro de un terminal (xterm). Este proceso se repite para generar múltiples instancias de getty.
- monitor_gettys: Función que monitorea los procesos getty y, si alguno de ellos termina, lo reinicia automáticamente, asegurando que siempre haya procesos getty corriendo.
- main: Crea múltiples procesos getty y luego inicia la monitorización de estos.

## 3. sh.c
  Objetivo: Simula un shell simple que permite a los usuarios ejecutar comandos. También incluye comandos especiales como exit para salir del shell y shutdown para terminar todos los procesos.

Detalles:
- execute_command: Función que toma una cadena de texto con un comando y lo ejecuta utilizando execvp, que busca el comando en el sistema y lo ejecuta. Si el comando no se encuentra, muestra un error.
- main: Proporciona un prompt interactivo (sh >) para que el usuario ingrese comandos. Si el comando es exit, el shell se cierra. Si el comando es shutdown, se envía una señal de terminación a todos los procesos en ejecución. De lo contrario, ejecuta el comando ingresado.

##
 
- init.c: Inicia el sistema creando 6 procesos getty que se ejecutan en terminales xterm. Cada proceso getty se encarga de autenticar a los usuarios.
- getty.c: Solicita al usuario un nombre de usuario y contraseña, validando su autenticidad. Si son correctos, inicia un shell interactivo (sh.c).
- sh.c: Proporciona un shell simple donde los usuarios pueden ejecutar comandos. Permite comandos como exit y shutdown para gestionar el proceso.
  
Este sistema emula un entorno de login básico con autenticación de usuarios y un shell interactivo, ideal para ser utilizado en un entorno de pruebas o simulación.
