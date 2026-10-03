#include <stdint.h>

uint8_t addu1(uint8_t a, uint8_t b) {
    return a+b;
}

int adds4(int a, int b) {
    return a+b;
}

int derefInt(int *x) {
    return *x;
}

unsigned int derefUInt(unsigned int *x) {
    return *x;
}

typedef struct {
    int a;
    int b;
    int c;
} Dummy;

Dummy derefStruct(Dummy *d) {
    return *d;
}

int structMembers(Dummy *d) {
    return d->c + d->b;
}

int arrayStructMembers(Dummy* d, int n) {
    return d[n].b + d[n].c;
}

int controlFlow(int n, int m) {
    if (n > m)
        return n;
    else
        return m;
}
