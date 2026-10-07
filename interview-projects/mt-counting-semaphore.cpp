#include <atomic>
#include <chrono>
#include <iostream>
#include <mutex>
#include <semaphore>
#include <string>
#include <thread>
#include <vector>

int main() {
    constexpr int permits = 2;
    constexpr int workerCount = 5;

    // Two workers may enter the limited-capacity section at once.
    std::counting_semaphore<permits> slots(permits);
    std::atomic<int> active{0};
    std::mutex outputMutex;
    std::vector<std::thread> workers;

    for (int id = 1; id <= workerCount; ++id) {
        workers.emplace_back([&, id] {
            slots.acquire();  // Wait until a permit is available.

            const int nowActive = active.fetch_add(1) + 1;
            {
                std::lock_guard<std::mutex> lock(outputMutex);
                std::cout << "worker " << id << " entered; active=" << nowActive << '\n';
            }

            std::this_thread::sleep_for(std::chrono::milliseconds(100));

            const int remaining = active.fetch_sub(1) - 1;
            {
                std::lock_guard<std::mutex> lock(outputMutex);
                std::cout << "worker " << id << " left; active=" << remaining << '\n';
            }
            slots.release();  // Let another worker enter.
        });
    }

    for (auto& worker : workers) {
        worker.join();
    }
}
