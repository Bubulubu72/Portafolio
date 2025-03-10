#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <semaphore.h>
#include <pthread.h>

sem_t sem1, sem2;

void print(char *s)
{
    usleep(rand()%1000);
    printf("%s\n",s);
    usleep(rand()%1000);
}

void P()
{
    print("A");
    sem_post(&sem1); // Señala que A ha sido impreso
    sem_wait(&sem2); // Espera hasta que B se imprima antes de continuar
    print("B");
    sem_post(&sem1); // Señala que B ha sido impreso
    print("C");
    exit(0);
}

void Q()
{
    sem_wait(&sem1); // Espera a que A sea impreso
    sem_wait(&sem1); // Espera a que B sea impreso
    print("D");
    print("E");
    exit(0);
}

int main()
{
    srand(getpid());

    sem_init(&sem1, 0, 0);
    sem_init(&sem2, 0, 0);

    int p;

    p = fork();
    if(p == 0)
        P();

    p = fork();
    if(p == 0)
        Q();

    wait(NULL);
    wait(NULL);

    sem_destroy(&sem1);
    sem_destroy(&sem2);
}