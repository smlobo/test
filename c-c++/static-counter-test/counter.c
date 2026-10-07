#include "counter.h"

static int counter = 0;

int counter_next(void)
{
    return ++counter;
}

const void *counter_identity(void)
{
    return &counter;
}
