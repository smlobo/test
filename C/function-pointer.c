#include <stdio.h>

typedef int (*mathfptr)(int, float);

int adder(int x, float y) {
    return x + (int)y;
}

int suber(int x, float y) {
    return x - (int)y;
}

int main(int argc, char** argv) {
    mathfptr myFunc = adder;
    if (argc >= 2)
        myFunc = suber;
    printf("10 ? 5.0 = %d\n", myFunc(10, 5.0));
}