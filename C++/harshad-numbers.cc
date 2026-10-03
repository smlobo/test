// A harshad number is an integer that is divisible by the sum of its digits

#include <iostream>
#include <vector>

#include "divisors.h"

using namespace std;

uint64_t digitSum(uint64_t x) {
    uint64_t sum = 0;
    while (x > 0) {
        uint64_t digit = x % 10;
        sum += digit;
        x /= 10;
    }
    return sum;
}

bool isHarshad(uint64_t sum, vector<uint64_t>& divisors) {
    for (auto divisor : divisors) {
        if (divisor == sum)
            return true;
    }
    return false;
}

int testHarshad(int argc, char* argv[]) {
    // Arguments
    if (argc != 2) {
        cout << "Usage: " << argv[0] << " <n>" << endl;
        return 1;
    }

    uint64_t n = stol(argv[1]);
    cout << "Generating the first `" << n << "` Harshad numbers (> 10)\n";

    uint64_t c = 11;
    while (n > 0) {
        // Get sum of the digits
        uint64_t sum = digitSum(c);

        // Get divisors
        vector<uint64_t> divisors;
        Divisors::divisors(c, divisors);

        // Is harshad
        if (isHarshad(sum, divisors)) {
            cout << c << " {" << sum << "} , ";
            n--;
        }

        c++;
    }
    cout << endl;

    return 0;
}

int main(int argc, char* argv[]) {
    return testHarshad(argc, argv);
}
