#include <stdio.h>
#include <dlfcn.h>
#include <stdlib.h>

typedef int (*puts_fptr_t)(const char *);

int main() {
  //puts_fptr_t libc_puts = (puts_fptr_t) dlsym(RTLD_DEFAULT, "puts");
  puts_fptr_t libc_puts = (puts_fptr_t) dlsym(0, "puts");

  printf("&puts() = %#lx\n", libc_puts);
  (*libc_puts)("hello\n");

  return 0;
}

