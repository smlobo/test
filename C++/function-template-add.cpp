#include <iostream>

template<typename T>
T add(T a, T b) {
    return a + b;
}

int main() {
    std::cout << "10 + 20 = " << add<int>(10, 20) << "\n";
    return 0;
}

