// Implement atoi
#include <stdio.h>

int myatoi(char *pStr) {
  int x = 0;

  if (!pStr)
    return x;

  while (*pStr != '\0') {
    if ((*pStr < '0') || (*pStr > '9'))
      break;

    x = 10*x + *pStr - '0';

    pStr++;
  }

  return x;
}

int main(int argc, char **argv) {
  if (argc != 2) {
    printf("Error:\n");
    return 1;
  }

  printf("Int value = %d\n", myatoi(argv[1]));

  return 0;
}

