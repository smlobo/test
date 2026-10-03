// number to Excel spread sheet
#include <stdio.h>

char excel[100];
int i = 0;

int convert(int n) {
  int x = 0;
  if (n > 26)
    x = convert(n/26);
  
  excel[i] = (char) (n - x*26 - 1 + 'A');
  i++;

  return n;
}

int main() {
  int n;
  scanf("%d", &n);
  printf("n = %d\n", n);
  excel[i] = '\0';

  int x = 0;
  if (n >= 26)
    x = convert(n/26);
  excel[i] = (char) (n - x*26 + 'A');
  excel[++i] = '\0';

  printf("Excel = %s\n", excel);

  return 0;
}
