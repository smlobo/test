// function template
#include <iostream>
#include <iomanip>
// #include <format>

using namespace std;

template<class X>
void swapper(X &x, X &y) {
    X temp = x;
    x = y;
    y = temp;
}

int main() {
    int x = 10;
    int y = 20;

    cout << "x = " << x << ", y = " << y << endl;
    swapper<int>(x, y);
    cout << "x = " << x << ", y = " << y << endl;

    float a = 10.149;
    float b = 20.249;

    cout << fixed     // fix the number of decimal digits
        << setprecision(2);
    cout << "a = " << a << ", b = " << b << endl;
    swapper(a, b);
    cout << "a = " << a << ", b = " << b << endl;

    bool p = true;
    bool q = false;
    cout << boolalpha;
    cout << "p = " << p << ", q = " << q << endl;
    swapper(p, q);
    cout << "p = " << p << ", q = " << q << endl;

    return 0;
}
