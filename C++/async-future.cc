#include <future>
#include <thread>
#include <chrono>
#include <iostream>

int main() {
    using namespace std::chrono_literals;

    /* Run some task on new thread. The launch policy std::launch::async
       makes sure that the task is run asynchronously on a new thread. */
    auto future = std::async(std::launch::async, [] {
        std::this_thread::sleep_for(3s);
        return 8;
    });

    // Use wait_for() with zero milliseconds to check thread status.
    auto status = future.wait_for(0ms);

    // Check status.
    while (status != std::future_status::ready) {
        std::cout << "Thread still running" << std::endl;
        status = future.wait_for(100ms);
    }
    std::cout << "Thread finished 🌟" << std::endl;

    auto result = future.get(); // Get result.
    std::cout << "Result: " << result << "\n";
}
