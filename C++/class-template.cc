#include <iostream>
#include <string>

using namespace std;

template<class X, class Y>
class Calculator {
public:
    X x;
    Y y;
    Calculator(X _x, Y _y) : 
        x(_x), y(_y) {}
    X add() {
        return x + y;
    }
};

int main() {
    Calculator<string, string> pss("foo", "bar");
    cout << pss.x << " + " << pss.y << " = " << pss.add() << endl;

    Calculator<int, float> pif(1, 2.2);
    cout << pif.x << " + " << pif.y << " = " << pif.add() << endl;

    Calculator<float, int> pfi(2.14, 1);
    cout << pfi.x << " + " << pfi.y << " = " << pfi.add() << endl;

}