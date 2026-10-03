#include <unistd.h>
#include <stdio.h>
//#include <linux/getcpu.h>
#include <sched.h>

int main()
{
  int c = 0, s = 0, n = 0;

  //s = getcpu(&c, &n, NULL);
  //printf("getcpu: cpu = %d, node = %d, return = %d\n", c, n, s);

  printf("sched_getcpu(): %d\n", sched_getcpu());
  
  return 0;
}
