#include <iostream>
#include <typeinfo>

struct A {
    int x;
};
A a;
A* aPtr;

class B {
    double p, q;
};
B b;
B* bPtr;

decltype(aPtr->x) y;

int main() {
    int i = 10;
    decltype(i) j = 20;
    static_assert(std::is_same_v<decltype(i), decltype(j)>);
    static_assert(std::is_same_v<decltype(i), decltype(y)>);

    std::cout << "typeid(i) : " << typeid(i).name() << "\n";
    std::cout << "typeid(a) : " << typeid(a).name() << "\n";
    std::cout << "typeid(aPtr) : " << typeid(aPtr).name() << "\n";
    std::cout << "typeid(b) : " << typeid(b).name() << "\n";
    std::cout << "typeid(bPtr) : " << typeid(bPtr).name() << "\n";
}
