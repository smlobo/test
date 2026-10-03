#include <cassert>
#include <iostream>
#include <map>

class Registry {
public:
    static std::map<char, int>& items() {
        static std::map<char, int> m;
        return m;
    }
};

struct Registrar {
    Registrar(char c, int i) {
        if (Registry::items().count(c)) {
            std::cout << "Item: '" << c << "', already registered\n";
            assert(false);
        }
        Registry::items().insert({c, i});
    }
};

static Registrar r1Sheldon('x', 10);

void llvm_code_A() {
    std::cout << "LLVM code A : " << Registry::items().size() << "\n";
}

void llvm_code_B() {
    std::cout << "LLVM code B : " << Registry::items().size() << "\n";
}
