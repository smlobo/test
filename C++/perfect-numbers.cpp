// Print the first <n> perfect numbers (sum of the divisors (excluding the 
// number itself) are equal to the number)

#include <cassert>
#include <chrono>
#include <iostream>
#include <cmath>
#include <numeric>
#include <vector>

#include "utilities.h"
#include "check-prime.h"

namespace perfect {
    static std::vector<unsigned long> populateDivisors(unsigned long n) {
        std::vector<unsigned long> divisors = {1};
        for (unsigned long i = 2; i <= std::sqrt(n); i++) {
            if (n%i == 0) {
                divisors.push_back(i);
                if (i != n/i) {
                    divisors.push_back(n/i);
                }
            }
        }
        return divisors;
    }

    static bool isPerfect(unsigned long n) {
        if (n == 1) {
            return false;
        }
        std::vector<unsigned long> divisors = populateDivisors(n);
        unsigned long sum = std::accumulate(divisors.begin(), divisors.end(), 0UL);
        // unsigned long sum = 0;
        // for (const unsigned long divisor : divisors)
        //     sum += divisor;
        // std::cout << "Potential perfect sum: " << sum << "\n";
        if (sum == n) {
            return true;
        }
        return false;
    }

    std::vector<unsigned long> getPerfects(unsigned long n) {
        std::vector<unsigned long> perfects = {};
        unsigned long check = 1;
        while (n > perfects.size()) {
            if (isPerfect(check)) {
                perfects.push_back(check);
            }
            check++;
        }
        return perfects;
    }

    std::vector<unsigned long> getPerfectsMT(int n) {
        std::vector<unsigned long> perfects = {};
        unsigned long check = 1;
        while (n > perfects.size()) {
            if (isPerfect(check)) {
                perfects.push_back(check);
            }
            check++;
        }
        return perfects;
    }

    // Euclid algorithm { perfect = 2^x * (2^(x+1)-1) }
    std::vector<unsigned long> getPerfectEuclid(int n) {
        std::vector<unsigned long> perfects = {};
        unsigned long target = 1;
        unsigned long firstSq = 2;
        while (n > perfects.size()) {
            // 2^x
            unsigned long secondSq = firstSq*2;

            // Euclid (x+1) should be prime
            if (!prime::checkPrime(target+1)) {
                target++;
                firstSq = secondSq;
                continue;
            }

            // Even more efficient Euclid - (2^(x+1)-1) should be prime
            if (!prime::checkPrime(secondSq-1)) {
                target++;
                firstSq = secondSq;
                continue;
            }

            // Potential perfect
            unsigned long perfect = firstSq * (secondSq-1);
            std::cout << "Perfect: " << perfect << " { " << 
                populateDivisors(perfect) << "} \n";
            // if (isPerfect(perfect)) {
                perfects.push_back(perfect);
            // }

            target++;
            firstSq = secondSq;
        }
        return perfects;
    }
}

int main(int argc, char* argv[]) {
    // Argument
    if (argc != 2) {
        std::cout << "Usage: " << argv[0] << " <num-perfects>\n";
        return 1;
    }

    int num = 0;
    try {
        num = std::stoi(argv[1]);
    } catch(...) {
        std::cerr << "Could not parse " << argv[1] << " to int\n";
        return 1;
    }
    std::cout << "Calculating the first " << num << " perfect numbers\n";

    auto beforeBrute = std::chrono::steady_clock::now();
    // std::vector<unsigned long> perfects = perfect::getPerfects(num);
    auto beforeEuclid = std::chrono::steady_clock::now();
    std::vector<unsigned long> ePerfects = perfect::getPerfectEuclid(num);
    auto after = std::chrono::steady_clock::now();

    // assert(perfects == ePerfects);
    std::cout << num << " perfect numbers: " << ePerfects << "\n";
    auto bruteTime = std::chrono::duration_cast<std::chrono::milliseconds>
        (beforeEuclid - beforeBrute);
    auto euclidTime = std::chrono::duration_cast<std::chrono::milliseconds>
        (after - beforeEuclid);
    std::cout << "Brute time: " << bruteTime.count() << "ms\n";
    std::cout << "Euclid time: " << euclidTime.count() << "ms\n";

    return 0;
}
