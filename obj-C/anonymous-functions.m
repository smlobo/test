#include <stdio.h>

int main() {
    int x = -11;

    void (^myBlock)() = ^{
        printf("Block captured value: %d\n", x);
        // x++;     // ERROR (missing __block type specifier)
    };

    // Calling the block
    x = 10;
    printf("Value outside block: %d\n", x);
    myBlock();
    x = 20;
    printf("Value outside block: %d\n", x);
    myBlock();
}
