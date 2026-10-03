#include <stdio.h>
#include <sys/utsname.h>

int main() {
  struct utsname utsn;
  
  if (uname(&utsn) == -1) {
    printf("uname() failed\n");
  }
  else {
    printf("sysname: \t%s\n", utsn.sysname);
    printf("nodename: \t%s\n", utsn.nodename);
    printf("release: \t%s\n", utsn.release);
    printf("version: \t%s\n", utsn.version);
    printf("machine: \t%s\n", utsn.machine);
  }

  return 0;
}
