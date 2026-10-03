// Same program print C for C compiler, C++ for C++ compiler
#include <stdio.h>

int main() {
  if (sizeof('a') == sizeof(int))
    printf("C\n");
  else if (sizeof('a') == sizeof(char))
    printf("C++\n");
  return 0;
}

