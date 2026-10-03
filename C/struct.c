#include <stdio.h>
#include <stdint.h>

typedef struct {
    int x;
    int* y;
} X;

int main() {
    X myX;
    myX.x = 10;
    myX.y = &myX.x;

    printf("[%p] %d %p\n", &myX, myX.x, myX.y);
    printf("{%p} {%p}\n", &myX.x, &myX.y);
}