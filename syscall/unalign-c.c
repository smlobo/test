#include <stdio.h>
#include <sys/prctl.h>
#include <errno.h>

int main()
{
  int status = prctl(PR_SET_UNALIGN, PR_UNALIGN_NOPRINT);
  printf("prctl(PR_SET_UNALIGN, *) = %d, {errno: %d}\n", status, errno);
  return 0;
}
