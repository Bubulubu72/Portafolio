#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

#define NTHREADS 2
#define ITERS 100
#define BUFFER_SIZE 10

int m,n=0;

pthread_mutex_t s;   
pthread_mutex_t retraso;
pthread_cond_t buffer_not_full;
pthread_cond_t buffer_not_empty;

typedef struct {
    char buffer[BUFFER_SIZE];
    int in;
    int out;
} BUFFER;

BUFFER buff;

#define END 0

char produce()
{
    static char c=-1; 
    c++;
    return c%26+65;  
}   

void buffer_put(BUFFER *b,char c)
{
    b->buffer[b->in++ % BUFFER_SIZE] = c;
}

char buffer_get(BUFFER *b)
{
    return(b->buffer[b->out++ % BUFFER_SIZE]);
}

void buffer_init(BUFFER *b)
{
    b->in = 0;
    b->out = 0;
}

void *productor(void *args)
{
    int iters=ITERS;
    char c;

    while(iters--)
    {
        c = produce();
        if(iters>0)
            printf("Productor produce %c\n",c);

        pthread_mutex_lock(&s);

        while(n == BUFFER_SIZE) // Espera hasta que haya espacio en el buffer
            pthread_cond_wait(&buffer_not_full, &s);

        if(iters>0)
            buffer_put(&buff,c);
        else
            buffer_put(&buff,END);

        n++;
        pthread_cond_signal(&buffer_not_empty); // Notifica al consumidor que el buffer no está vacío

        pthread_mutex_unlock(&s);

        usleep(rand()%500000);
    }
}

void *consumidor(void *args)
{
    char c;

    while(1) 
    {
        pthread_mutex_lock(&s);

        while(n == 0) // Espera hasta que haya elementos en el buffer
            pthread_cond_wait(&buffer_not_empty, &s);

        c=buffer_get(&buff);

        n--;
        pthread_cond_signal(&buffer_not_full); // Notifica al productor que el buffer no está lleno

        pthread_mutex_unlock(&s);

        if(c != END)
            printf("\t\t\tConsumidor consume %c\n",c);
        else
            break;

        usleep(rand()%800000);
    }
}

int main()
{
    pthread_t tid[NTHREADS];
    int i;

    srand(getpid());

    buffer_init(&buff);

    pthread_mutex_init(&s, NULL);
    pthread_mutex_init(&retraso, NULL);
    pthread_cond_init(&buffer_not_full, NULL);
    pthread_cond_init(&buffer_not_empty, NULL);

    pthread_create(&tid[0],NULL,productor,NULL);
    pthread_create(&tid[1],NULL,consumidor,NULL);

    for(i=0;i<NTHREADS;i++)
        pthread_join(tid[i],NULL);

    pthread_mutex_destroy(&s);
    pthread_mutex_destroy(&retraso);
    pthread_cond_destroy(&buffer_not_full);
    pthread_cond_destroy(&buffer_not_empty);
}