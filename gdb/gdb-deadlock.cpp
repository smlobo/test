#include <chrono>
#include <iostream>
#include <mutex>
#include <thread>

std::mutex mutexA;
std::mutex mutexB;

void worker1()
{
    std::cout << "worker1: locking A\n";
    mutexA.lock();

    std::this_thread::sleep_for(std::chrono::milliseconds(200));

    std::cout << "worker1: locking B\n";
    mutexB.lock();

    std::cout << "worker1: doing work\n";

    mutexB.unlock();
    mutexA.unlock();
}

void worker2()
{
    std::cout << "worker2: locking B\n";
    mutexB.lock();

    std::this_thread::sleep_for(std::chrono::milliseconds(200));

    std::cout << "worker2: locking A\n";
    mutexA.lock();

    std::cout << "worker2: doing work\n";

    mutexA.unlock();
    mutexB.unlock();
}

int main()
{
    std::thread t1(worker1);
    std::thread t2(worker2);

    t1.join();
    t2.join();

    std::cout << "done\n";
}

