// Implementation of check prime

#include <cmath>

namespace prime {
    bool checkPrime(unsigned long n) {
        for (unsigned long i = 2; i <= std::sqrt(n); i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }
}
