#include <stdio.h>
#include <dlfcn.h>

typedef double (*cos_fptr_t)(double);

int main() {
  void *libm_handle = dlopen("libm.so", RTLD_LAZY);
  cos_fptr_t libm_cos = (cos_fptr_t) dlsym(libm_handle, "cos");

  printf("&cos() = %#lx\n", libm_cos);
  printf("cos(2.0) = %.4f\n", (*libm_cos)(2.0));
  printf("cos(3.14) = %.4f\n", (*libm_cos)(3.14));

  return 0;
}

