#include <iostream>

int main() {
    char x[] = {(char)255, (char)255};
    std::cout << (unsigned)(unsigned char)x[0] << "\n";
}
