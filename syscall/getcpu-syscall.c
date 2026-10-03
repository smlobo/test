#include <unistd.h>
#include <stdio.h>
#include <sys/syscall.h>

int main()
{
#ifdef SYS_getcpu
  int cpu, status, node;
  status = syscall(SYS_getcpu, &cpu, &node, NULL);
  printf("getcpu syscall: cpu = %d, node = %d, return = %d\n", cpu, node, 
    status);
#else
  printf("getcpu syscall unavailable\n");
#endif
  
  return 0;
}
