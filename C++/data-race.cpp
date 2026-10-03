#include <iostream>
#include <thread>
#include <cassert>

int main() {
    int x = 10;
    std::thread t1{([&x]() -> void {
        x += 1;
    })};
    x += 100;
    t1.join();
    std::cout << "x = " << x << "\n";
    assert(x == 111);
    return 0;
}
