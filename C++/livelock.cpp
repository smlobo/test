#include <barrier>
#include <iostream>
#include <mutex>
#include <thread>

std::mutex left_mutex;
std::mutex right_mutex;
std::mutex output_mutex;

// Keep both threads moving in lockstep so the livelock is reproducible.
std::barrier synchronization_point{2};

void log(const char* name, const char* message)
{
    std::lock_guard<std::mutex> lock{output_mutex};
    std::cout << name << message << '\n';
}

void worker(const char* name, std::mutex& first, std::mutex& second)
{
    // A real livelock could continue forever. Limit the attempts so this
    // demonstration eventually terminates.
    for (int attempt = 1; attempt <= 5; ++attempt) {
        first.lock();

        // Both threads now hold their first mutex.
        synchronization_point.arrive_and_wait();

        if (!second.try_lock()) {
            log(name, ": second mutex unavailable; backing off");

            first.unlock();

            // Both threads back off and retry at the same time.
            synchronization_point.arrive_and_wait();
            continue;
        }

        log(name, ": work completed");
        second.unlock();
        first.unlock();
        return;
    }

    log(name, ": gave up without making progress");
}

int main()
{
    std::thread first{
        worker,
        "worker 1",
        std::ref(left_mutex),
        std::ref(right_mutex)
    };

    std::thread second{
        worker,
        "worker 2",
        std::ref(right_mutex),
        std::ref(left_mutex)
    };

    first.join();
    second.join();
}
