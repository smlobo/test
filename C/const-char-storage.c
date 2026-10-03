#include <stdio.h>

int main() {
    const char constArray[] = "ABCD";
    char nonConstArray[] = "XYZ";

    printf("[%p] %s\n", constArray, constArray);
    printf("[%p] %s\n", nonConstArray, nonConstArray);

    // constArray[1] = 'q';     ERROR
    nonConstArray[1] = 'p';

    printf("[%p] %s\n", constArray, constArray);
    printf("[%p] %s\n", nonConstArray, nonConstArray);
}