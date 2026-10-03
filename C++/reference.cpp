#include <iostream>

using namespace std;

void foo(int &ref) {
    cout << "ref: " << &ref << " = " << ref << "\n";
}

int main() {
    int x = 10;
    cout << "x: " << &x << " = " << x << "\n";

    foo(x);
    
    return 0;
}