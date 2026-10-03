// Same program compiled with C prints C, C== prints C++
#include <stdio.h>

int main() {
  #ifdef __cplusplus
    printf("C++\n");
  #else
    printf("C\n");
  #endif
  
  return 0;
}

