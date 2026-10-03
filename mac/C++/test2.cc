// Print integer only with putchar
#include <stdio.h>
#include <iostream.h>

void print_digit(int x) {
  if (x > 0) {
    print_digit(x/10);
    putchar(x%10 + '0');
  }
}

int main(int argv, char **argc) {
  if (argv != 2) {
    cout << "Error\n";
    return 0;
  }

  int x = atoi(argc[1]);
  int y = x;

  cout << "Reverse: ";
  while (x > 0) {
    putchar(x%10 + '0');
    x /= 10;
  }

  cout << "\nForward: ";
  print_digit(y);

  cout << "\n";
  return 0;
}
