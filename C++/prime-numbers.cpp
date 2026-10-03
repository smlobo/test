// Print the first <n> prime numbers

#include <iostream>

#include "utilities.h"
#include "check-prime.h"

std::array<int, 6> const INITIAL_PRIMES = {2, 3, 5, 7, 11, 13};

int main(int argc, char* argv[]) {
    // Argument
    if (argc != 2) {
        std::cout << "Usage: " << argv[0] << " <num-primes>\n";
        return 1;
    }

    int num = 0;
    try {
        num = std::stoi(argv[1]);
    } catch(...) {
        std::cerr << "Could not parse " << argv[1] << " to int\n";
        return 1;
    }
    std::cout << "Calculating the first " << num << " primes\n";

    std::vector<int> primes(INITIAL_PRIMES.begin(), 
        (num > INITIAL_PRIMES.size()) ? INITIAL_PRIMES.end() : 
        INITIAL_PRIMES.begin()+num);
    int check = INITIAL_PRIMES.back() + 1;
    while (num > primes.size()) {
        if (prime::checkPrime(check)) {
            primes.push_back(check);
        }
        check++;
    }

    std::cout << num << " primes: " << primes << "\n";

    return 0;
}
