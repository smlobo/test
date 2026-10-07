#include <chrono>
#include <iostream>
#include <mutex>
#include <shared_mutex>
#include <thread>
#include <vector>

class SharedValue {
public:
    int read() const {
        // Multiple readers may hold a shared lock at the same time.
        std::shared_lock<std::shared_mutex> lock(mutex_);
        return value_;
    }

    void write(int value) {
        // A writer needs exclusive access: no reader or other writer may hold it.
        std::unique_lock<std::shared_mutex> lock(mutex_);
        value_ = value;
    }

private:
    mutable std::shared_mutex mutex_;
    int value_ = 0;
};

int main() {
    SharedValue shared;
    std::mutex outputMutex;
    std::vector<std::thread> readers;

    for (int id = 1; id <= 3; ++id) {
        readers.emplace_back([&, id] {
            for (int i = 0; i < 5; ++i) {
                const int value = shared.read();
                {
                    std::lock_guard<std::mutex> lock(outputMutex);
                    std::cout << "reader " << id << " saw " << value << '\n';
                }
                std::this_thread::sleep_for(std::chrono::milliseconds(10));
            }
        });
    }

    std::thread writer([&] {
        for (int value = 1; value <= 5; ++value) {
            shared.write(value);
            {
                std::lock_guard<std::mutex> lock(outputMutex);
                std::cout << "writer set " << value << '\n';
            }
            std::this_thread::sleep_for(std::chrono::milliseconds(12));
        }
    });

    for (auto& reader : readers) {
        reader.join();
    }
    writer.join();
    std::cout << "final value: " << shared.read() << '\n';
}
