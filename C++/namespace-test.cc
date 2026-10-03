#include <iostream>

namespace foo {
    void foo();
}

namespace bar {
    void bar();
}

int main() {
    std::cout << "In main\n";
    foo::foo();
    bar::bar();
    std::cout << "Done\n";    
}