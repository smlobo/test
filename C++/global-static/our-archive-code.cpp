#include <iostream>

void llvm_code_B();

void our_archive() {
    std::cout << "In our archive\n";
    llvm_code_B();
}
