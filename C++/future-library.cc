// C++ future

#include <iostream>
#include <future>

#include "utilities.h"

class FutureTest {
public:
	int doSomething() {
        int sleepTime = randomInt(0, 100);
        this_thread::sleep_for(chrono::milliseconds(sleepTime));
        std::cout << "[" << std::this_thread::get_id() << "] doSomething DONE {" 
        	<< sleepTime << "}\n";
        return sleepTime;
	}

	void parallelDo() {
		std::future<int> f1 = std::async(std::launch::async, 
			[this]() {
				return this->doSomething();
			});
		std::future<int> f2 = std::async(std::launch::async, 
			[this]() {
				return this->doSomething();
			});

		std::cout << "f1 : " << f1.get() << "\n";
		std::cout << "f2 : " << f2.get() << "\n";
	}
};

int main() {
	FutureTest ft;
	ft.doSomething();
	ft.parallelDo();

	return 0;
}