#include "counter.h"
#include "library.h"

#include <stdio.h>
#include <stdlib.h>

static void expect(const char *label, int actual, int expected)
{
    printf("%s: %d (expected %d)\n", label, actual, expected);
    if (actual != expected) {
        exit(EXIT_FAILURE);
    }
}

int main(void)
{
    const void *executable_counter = counter_identity();
    const void *library_counter = shared_counter_identity();

    printf("executable counter address: %p\n", executable_counter);
    printf("library counter address:    %p\n", library_counter);
    if (executable_counter == library_counter) {
        fputs("Expected distinct counter objects\n", stderr);
        return EXIT_FAILURE;
    }

    expect("executable first", counter_next(), 1);
    expect("library first", shared_counter_next(), 1);
    expect("executable second", counter_next(), 2);
    expect("library second", shared_counter_next(), 2);
    
    return EXIT_SUCCESS;
}
