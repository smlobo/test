#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>

struct S {
    int x;
    float y;
    char z[3];
};

int main() {
    printf("sizeof(char) = %lu\n", sizeof(char));
    printf("sizeof(short) = %lu\n", sizeof(short));
    printf("sizeof(int) = %lu\n", sizeof(int));
    printf("sizeof(long) = %lu\n", sizeof(long));
    printf("sizeof(size_t) = %lu\n", sizeof(size_t));
    printf("sizeof(uint32_t) = %lu\n", sizeof(uint32_t));
    printf("sizeof(bool) = %lu\n", sizeof(bool));
    printf("sizeof(struct S) = %lu\n", sizeof(struct S));

    int x[10];
    printf("sizeof(int x[10]) = %lu\n", sizeof(x));

    char y[10][5];
    printf("sizeof(char x[10][5]) = %lu\n", sizeof(y));
}