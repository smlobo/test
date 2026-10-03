#include <stdio.h>
#include <elf.h>

int main(int argc, char* argv[], char* envp[])
{
  Elf64_auxv_t *auxv;
  while(*envp++ != NULL);

  for (auxv = (Elf64_auxv_t *)envp; auxv->a_type != AT_NULL; auxv++)
  {
    if( auxv->a_type == AT_HWCAP)
      printf("AT_HWCAP is: 0x%x\n", auxv->a_un.a_val);
  }

  return 0;
}
