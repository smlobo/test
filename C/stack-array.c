#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    printf("%s\n", argv[1]);
    int x = atoi(argv[1]);
    printf("x [%p] = %d\n", &x, x);
    int stackArray[x];
    int y = x + 1;
    printf("y [%p] = %d\n", &y, y);
    return 0;
}

