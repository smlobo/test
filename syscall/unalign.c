#include <stdio.h>
#include <asm/unistd_64.h>
//#include <asm-generic/unistd.h>
#include <sys/prctl.h>

int main() {
  printf("prctl syscall\n");
  long status = 0;
  asm volatile ( "movq %1, %%rax\n\t"
                 "movq %2, %%rdi\n\t"
                 "syscall\n\t"
                 "movq %%rax, %0\n\t"
                 : "=r" (status)
                 : "i" (__NR_prctl), "i" (PR_SET_ENDIAN));
  printf("prctl PR_GET_UNALIGN status: = %d\n", status);

  return 0;
}
