#include <iostream>
#include <string>

int main(int argc, char** argv) {
  std::string *x = new std::string("xxx");
  std::cout << "x = " << x << " : " << *x << std::endl;
  std::string y = "yyy";
  std::cout << "y = " << &y << " : " << y << std::endl;

  char cArray[10] = {'a', 'b', 'c', '\0'};
  std::string *z = new std::string(cArray);
  std::cout << "z = " << z << " : " << *z << std::endl;
}

