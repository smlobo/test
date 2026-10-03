#include <iostream>
#include <string>
#include <format>
#include <utility>

constexpr unsigned padSize = 0x1000;

// Default copy & move constructor generated
class MyString {
public:
    int pad[padSize];
    std::string x;

    MyString(int p, std::string x)
            : x(x) {
        for (int i = 0; i < padSize; i++)
            pad[i] = i + p;
    }
};

MyString makeMyString(std::string x) {
    MyString ms = MyString{1000, x + "-something"};
    int temp = -1;
    std::cout << std::format("makeMyString() [{:#x}]:\n", 
        reinterpret_cast<unsigned long>(&temp));
    std::cout << std::format("\t{} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad[0], ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));
    return ms;
}

void takeMyString(MyString&& ms) {
    int temp = -1;
    std::cout << std::format("takeMyString() [{:#x}]:\n", 
        reinterpret_cast<unsigned long>(&temp));
    std::cout << std::format("\t{} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad[0], ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));
}

int main(int argc, char *argv[]) {
    std::string x = "xxxyyyzzzsfgggggggggggs";
    if (argc == 2) {
        x = argv[1];
    }
    std::cout << std::format("main() [{:#x}]:\n", 
        reinterpret_cast<unsigned long>(&x));

    // Universal/Forwarding reference
    auto&& ms = makeMyString(x);
    ms.pad[0]++;
    int temp = -1;
    std::cout << std::format("main() [{:#x} {:#x}]:\n", 
        reinterpret_cast<unsigned long>(&x),
        reinterpret_cast<unsigned long>(&temp));
    std::cout << std::format("\t{} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad[0], ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));

    // Forward
    takeMyString(std::forward<MyString>(ms));
}
