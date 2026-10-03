#include <stdio.h>

#define XSTR(s) STR(s)
#define STR(s) #s

#define TEST_FUNCTION putsp

#pragma weak putsp

extern int TEST_FUNCTION(const char *s);

int main() {
  int (*fp)(const char *) = TEST_FUNCTION;
  if (fp)
    printf(XSTR(TEST_FUNCTION) " exists: %#lx\n", fp);
  else
    printf(XSTR(TEST_FUNCTION) " does not exist\n");
  return 0;
}
