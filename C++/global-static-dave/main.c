#include <stdio.h>

void a_call(void);
void b_call(void);

int main(void) {
  puts("First round:");
  a_call();
  b_call();

  puts("Second round:");
  a_call();
  b_call();

  return 0;
}

