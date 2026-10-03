#include <unistd.h>
#include <sys/syscall.h>

int main(int argc, char *argv[])
{
  syscall(SYS_write, STDOUT_FILENO, "Hello World\n", 12);
  syscall(SYS_exit, 10);
}
