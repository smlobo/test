#include <stdio.h>
#include <stdlib.h>

int *allocate_int() {
    return (int *) malloc(sizeof(int));
}

void free_int(int *x) {
    free(x);
}

void access_freed(int *x) {
    printf("[%#lx] freed location value: %d\n", (unsigned long) x, *x);
}

void read_after_free() {
    int *i = allocate_int();
    *i = 10;
    printf("[%#lx] %d\n", (unsigned long) i, *i);

    free_int(i);
    printf("[%#lx] freed\n", (unsigned long) i);

    access_freed(i);
    printf("[%#lx] accessed\n", (unsigned long) i);
}
