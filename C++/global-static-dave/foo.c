#include "foo.h"
#include <stdio.h>

static int counter =
    0; // <-- duplicated when libfoo.a is embedded into multiple DSOs

void foo_inc_and_print(const char *tag) {
  counter++;
  printf("%s: counter=%d (addr=%p)\n", tag, counter, (void *)&counter);
}

