#include <stdio.h>
#include <stdlib.h>

void foo(int foo_arg) {
    printf("foo_arg=%d; &foo_arg=%#p\n", foo_arg, &foo_arg);
    int foo_var = foo_arg + 10;
    printf("foo_var=%d; &foo_var=%#p\n", foo_var, &foo_var);
}

int main(int argc, char *argv[]) {
    printf("Arg count: %d\n", argc);
    for (int i = 0; i < argc; i++) {
        printf("[%d] %s\n", i, argv[i]);
    }

    int main_local = 10;
    printf("main_local=%d; &main_local=%#p\n", main_local, &main_local);
    foo(main_local);

    // Allocate 100 heap ints
    int **heap_array = (int **) malloc(10*sizeof(int *));
    printf("heap_array: %#p\n", heap_array);
    for (int i = 0; i < 10; i++) {
        heap_array[i] = malloc(sizeof(int));
        switch (i) {
            case 0:
            case 1:
                *heap_array[i] = 1;
                break;
            default:
                *heap_array[i] = *heap_array[i-1] + *heap_array[i-2];
        }
        printf("heap_array[%d]: %#p, *heap_array[%d]: %d\n", i, heap_array[i], 
            i, *heap_array[i]);
    }

    return 0;
}