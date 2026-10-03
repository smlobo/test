#include <stdio.h>
#include <stdlib.h>

void print_array(int **a, int x, int y);
void print_array_addresses(int **a, int x, int y);

int main(int argc, char **argv) {
    if (argc != 2) {
        printf("Usage: array-2d <m>x<n>\n");
        return 1;
    }

    char *dimension = argv[1];
    dimension[1] = '\0';
    int m = atoi(dimension);
    int n = atoi(dimension+2);

    printf("Creating a %dx%d array of ints\n", m, n);

    // Allocate the array
    int **array = malloc(m * sizeof(int*));
    for (int i = 0; i < m; i++) {
        array[i] = malloc(n * sizeof(int));
    }

    // Initialize array
    int count = 10;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            array[i][j] = count++;
        }
    }

    print_array(array, m, n);
    print_array_addresses(array, m, n);

    // Free the array
    for (int i = 0; i < m; i++) {
        free(array[i]);
    }
    free(array);

    return 0;
}

void print_array(int **a, int x, int y) {
    for (int i = 0; i < x; i++) {
        for (int j = 0; j < y; j++) {
            printf("%d, ", a[i][j]);
        }
        printf("\n");
    }
}

void print_array_addresses(int **a, int x, int y) {
    printf("Address of parameters: &a=%#lx, &x=%#lx, &y=%#lx\n", (unsigned long) &a, 
        (unsigned long) &x, (unsigned long) &y);
    for (int i = 0; i < x; i++) {
        printf("[%d] %#lx -> %#lx {", i, (unsigned long) a+i, (unsigned long) *(a+i));
        for (int j = 0; j < y; j++) {
            printf("%d, ", a[i][j]);
        }
        printf("} \n");
    }
}
