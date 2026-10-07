#include "counter.h"
#include "library.h"

int shared_counter_next(void)
{
    return counter_next();
}

const void *shared_counter_identity(void)
{
    return counter_identity();
}
