#include <unistd.h>

int main(int argc, char *argv[])
{
  write(1, "Hello World\n", 12);
  _exit(0);
}
