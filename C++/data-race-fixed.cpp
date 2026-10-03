#include <iostream>
#include <thread>
#include <cassert>
#include <mutex>

int main() {
    int x = 10;
    std::mutex x_mutex;

    std::thread t1{([&x, &x_mutex]() -> void {
        std::lock_guard<std::mutex> lock{x_mutex};
        x += 1;
    })};
    {
        std::lock_guard<std::mutex> lock{x_mutex};
        x += 100;
    }
    t1.join();
    std::cout << "x = " << x << "\n";
    assert(x == 111);
    return 0;
}
