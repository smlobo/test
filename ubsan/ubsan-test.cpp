#include <cstddef>
#include <iostream>
#include <limits>
#include <string_view>

void test_signed_overflow()
{
    volatile int maximum = std::numeric_limits<int>::max();
    const int result = maximum + 1;
    std::cout << "signed overflow result: " << result << '\n';
}

void test_invalid_shift()
{
    volatile unsigned int shift =
        std::numeric_limits<unsigned int>::digits;
    const unsigned int result = 1U << shift;
    std::cout << "invalid shift result: " << result << '\n';
}

[[gnu::noinline]] void store_int(void* address)
{
    *static_cast<int*>(address) = 42;
}

void test_misaligned_access()
{
    alignas(int) std::byte storage[sizeof(int) + 1]{};
    store_int(storage + 1);
    std::cout << "performed misaligned store\n";
}

int main(int argc, char* argv[])
{
    const std::string_view test = argc > 1 ? argv[1] : "all";

    if (test == "overflow" || test == "all") {
        test_signed_overflow();
    }

    if (test == "shift" || test == "all") {
        test_invalid_shift();
    }

    if (test == "alignment" || test == "all") {
        test_misaligned_access();
    }

    if (test != "overflow" && test != "shift" &&
        test != "alignment" && test != "all") {
        std::cerr << "usage: " << argv[0]
                  << " [overflow|shift|alignment|all]\n";
        return 1;
    }
}
