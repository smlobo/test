#include <iostream>

void llvm_code_A();

void our_shared_library() {
    std::cout << "In our shared library\n";
    llvm_code_A();
}
