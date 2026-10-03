// file io
#include<stdio.h>
#include<stdlib.h>

int main(int argc, char **argv) {
  if (argc < 2 || argc > 3)
    return 1;

  FILE *in = fopen(argv[1], "r");

  char c;
  int w = 0;
  int s = 0;
  int a = 0;
  while ((c = getc(in)) != EOF) {
    if (c == ' ')
      w++;
    else if (c == '.')
      s++;
    else
      a++;
  }

  printf("w = %d\n", w);
  printf("s = %d\n", s);
  printf("a = %d\n", a);
  fclose(in);
  return 0;
}
