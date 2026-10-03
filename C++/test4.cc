// Assignment operator grouping

#include <iostream.h>

int main() {
  int a = 5, b, c;

  c = b = --a;

  cout << "a = " << a;			// 4
  cout << "; b = " << b;		// 4
  cout << "; c = " << c << endl;	// 4

  return 0;
}

