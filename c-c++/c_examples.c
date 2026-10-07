#define _POSIX_C_SOURCE 200809L

#include <errno.h>
#include <fcntl.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdatomic.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

enum { ITERATIONS = 100000 };

static void check_thread(int error)
{
    if (error != 0) {
        fprintf(stderr, "pthread: %s\n", strerror(error));
        exit(EXIT_FAILURE);
    }
}

static void fail_system(const char *operation)
{
    perror(operation);
    exit(EXIT_FAILURE);
}

static pthread_mutex_t counter_mutex = PTHREAD_MUTEX_INITIALIZER;
static int protected_counter;

static void *increment_protected(void *unused)
{
    (void)unused;
    for (int iteration = 0; iteration < ITERATIONS; ++iteration) {
        check_thread(pthread_mutex_lock(&counter_mutex));
        ++protected_counter;
        check_thread(pthread_mutex_unlock(&counter_mutex));
    }
    return NULL;
}

static void mutex_demo(void)
{
    pthread_t workers[2];
    check_thread(pthread_create(&workers[0], NULL, increment_protected, NULL));
    check_thread(pthread_create(&workers[1], NULL, increment_protected, NULL));
    check_thread(pthread_join(workers[0], NULL));
    check_thread(pthread_join(workers[1], NULL));
    printf("mutex: counter = %d (expected %d)\n", protected_counter, 2 * ITERATIONS);
    check_thread(pthread_mutex_destroy(&counter_mutex));
}

static sem_t *tokens;

static void *consume_tokens(void *unused)
{
    (void)unused;
    for (int token = 0; token < 3; ++token) {
        while (sem_wait(tokens) == -1) {
            if (errno != EINTR) {
                fail_system("sem_wait");
            }
        }
    }
    return NULL;
}

static void semaphore_demo(void)
{
    char name[32];
    snprintf(name, sizeof(name), "/c_sync_%ld", (long)getpid());
    tokens = sem_open(name, O_CREAT | O_EXCL, 0600, 0);
    if (tokens == SEM_FAILED) {
        fail_system("sem_open");
    }
    if (sem_unlink(name) == -1) {
        fail_system("sem_unlink");
    }
    for (int token = 0; token < 3; ++token) {
        if (sem_post(tokens) == -1) {
            fail_system("sem_post");
        }
    }
    pthread_t consumer;
    check_thread(pthread_create(&consumer, NULL, consume_tokens, NULL));
    check_thread(pthread_join(consumer, NULL));
    puts("semaphore: consumed 3 permits posted before the consumer started");
    if (sem_close(tokens) == -1) {
        fail_system("sem_close");
    }
}

static pthread_mutex_t ready_mutex = PTHREAD_MUTEX_INITIALIZER;
static pthread_cond_t ready_condition = PTHREAD_COND_INITIALIZER;
static bool ready;
static int condition_payload;

static void *consume_condition(void *unused)
{
    (void)unused;
    check_thread(pthread_mutex_lock(&ready_mutex));
    while (!ready) {
        check_thread(pthread_cond_wait(&ready_condition, &ready_mutex));
    }
    int observed = condition_payload;
    check_thread(pthread_mutex_unlock(&ready_mutex));
    printf("condition variable: payload = %d (expected 42)\n", observed);
    return NULL;
}

static void condition_demo(void)
{
    pthread_t consumer;
    check_thread(pthread_create(&consumer, NULL, consume_condition, NULL));
    check_thread(pthread_mutex_lock(&ready_mutex));
    condition_payload = 42;
    ready = true;
    check_thread(pthread_mutex_unlock(&ready_mutex));
    check_thread(pthread_cond_signal(&ready_condition));
    check_thread(pthread_join(consumer, NULL));
    check_thread(pthread_cond_destroy(&ready_condition));
    check_thread(pthread_mutex_destroy(&ready_mutex));
}

static atomic_int atomic_counter = ATOMIC_VAR_INIT(0);
static atomic_bool published = ATOMIC_VAR_INIT(false);
static int atomic_payload;

static void *increment_atomic(void *unused)
{
    (void)unused;
    for (int iteration = 0; iteration < ITERATIONS; ++iteration) {
        atomic_fetch_add_explicit(&atomic_counter, 1, memory_order_relaxed);
    }
    return NULL;
}

static void *publish_payload(void *unused)
{
    (void)unused;
    atomic_payload = 42;
    atomic_store_explicit(&published, true, memory_order_release);
    return NULL;
}

static void atomic_demo(void)
{
    pthread_t workers[2];
    check_thread(pthread_create(&workers[0], NULL, increment_atomic, NULL));
    check_thread(pthread_create(&workers[1], NULL, increment_atomic, NULL));
    check_thread(pthread_join(workers[0], NULL));
    check_thread(pthread_join(workers[1], NULL));
    printf("atomic: counter = %d (expected %d)\n",
           atomic_load_explicit(&atomic_counter, memory_order_relaxed), 2 * ITERATIONS);

    pthread_t producer;
    check_thread(pthread_create(&producer, NULL, publish_payload, NULL));
    while (!atomic_load_explicit(&published, memory_order_acquire)) {
    }
    printf("atomic release/acquire: payload = %d (expected 42)\n", atomic_payload);
    check_thread(pthread_join(producer, NULL));
}

int main(void)
{
    mutex_demo();
    semaphore_demo();
    condition_demo();
    atomic_demo();
    return EXIT_SUCCESS;
}
