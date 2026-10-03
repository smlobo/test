#include <iostream>
#include <string>
#include <format>
#include <utility>

int main(int argc, char *argv[]) {
    // Long string to force std::string.data() on the heap
    // From argv[1] appears to always be on the heap (though the copy for 
    // a small string is on the stack)
    std::string x = "xxxyyyzzzsfgggggggggggs";
    if (argc == 2) {
        x = argv[1];
    }

    std::cout << std::format("Original x: {} [{:#x} -> {:#x}]\n", x, 
        reinterpret_cast<unsigned long>(&x), 
        reinterpret_cast<unsigned long>(x.data()));

    std::string xCopy = x;
    std::cout << std::format("xCopy: {} [{:#x} -> {:#x}]\n", xCopy, 
        reinterpret_cast<unsigned long>(&xCopy), 
        reinterpret_cast<unsigned long>(xCopy.data()));

    std::string xMove = std::move(x);
    std::cout << std::format("xMove: {} [{:#x} -> {:#x}]\n", xMove, 
        reinterpret_cast<unsigned long>(&xMove), 
        reinterpret_cast<unsigned long>(xMove.data()));
}
