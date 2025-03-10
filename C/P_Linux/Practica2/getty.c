#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>

#define PASSWD_FILE "passwd"

int validate_user(char *user, char *password) {
    FILE *file = fopen(PASSWD_FILE, "r");
    if (!file) {
        perror("Error al abrir passwd");
        return 0;
    }
    
    char line[256];
    while (fgets(line, sizeof(line), file)) {
        char *saved_user = strtok(line, ":");
        char *saved_password = strtok(NULL, "\n");
        
        if (strcmp(saved_user, user) == 0 && strcmp(saved_password, password) == 0) {
            fclose(file);
            return 1; // Usuario validado
        }
    }

    fclose(file);
    return 0; // Usuario no válido
}

void login_loop() {
    char user[50], password[50];
    
    while (1) {
        printf("Login: ");
        scanf("%s", user);
        printf("Password: ");
        scanf("%s", password);

        if (validate_user(user, password)) {
            printf("Bienvenido %s!\n", user);
            pid_t pid = fork();
            if (pid == 0) {
                // Proceso hijo: iniciar shell
                execlp("./sh", "sh", NULL);
                perror("Error al iniciar el shell");
                exit(1);
            } else if (pid > 0) {
                wait(NULL); // Esperar a que el shell termine
            }
        } else {
            printf("Usuario o contraseña incorrectos\n");
        }
    }
}

int main() {
    login_loop();
    return 0;
}
