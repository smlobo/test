#include <iostream>

using namespace std;

class Foo {
public:
  int x;
  int y;
};

int g = 10;

int main() {
  Foo a;
  a.x = g+10;
  a.y = g+11;

  cout << "object Foo (a): " << a.x << ", " << a.y << endl;

  return 0;
}
