#include <iostream>

class X {
public:
    X() {
        std::cout << "X constructor\n";
    }
};

int main() {
    for (int i = 0; i < 3; i++) {
        static const X x = X();
        // const X x = X();
    }
}
