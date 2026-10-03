// const and mutable
#include<stdio.h>

class T {
  int a;
  mutable int b;

  public:
  T(int x, int y) : 
    a(x), b(y) {}

  void set_a(int x) const { /* a = x; */ }
  void set_b(int y) const { b = y; }
  
  void print() const {
    printf("a = %d, b = %d\n", a, b);
  }
};

int main() {
  const T t(10, 20);

  t.print();
  t.set_b(200);
  t.print();

  return 0;
}
