#include <iostream>

enum class TokenType {
    X = 0b0001,
    Y = 0b0010,
    Z = 0b0100,
    XY = X | Y,
};

int main() {
    TokenType x = TokenType::X;
    int xx = (int) x;
    std::cout << "x = " << xx << "\n";
    if ((int)x & (int)TokenType::XY) {
        std::cout << "x = " << xx << " Is XY\n";
    }
}