#include <iostream>

using namespace std;

void simple() {
  cout << "In function simple()" << endl;
  throw 1;
}

int main() {
  cout << "In function main()" << endl;
  try {
    simple();
  }
  catch(int e) {
    cout << "Caught exception: " << e << endl;
  }

  return 0;
}
  
