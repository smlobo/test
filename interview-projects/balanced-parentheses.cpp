#include <ios>
#include <iostream>
#include <string>

bool isBalanced(const std::string& s) {
    int stack = 0;
    for (std::size_t i = 0; i < s.size(); i++) {
        if (s[i] == '(') {
            stack++;
        } else {
            if (stack == 0) {
                return false;
            }
            stack--;
        }
    }
    return stack == 0;
}

int main() {
    std::string i = "(())()";
    bool a = isBalanced(i);
    std::cout << std::boolalpha << i << " = " << a << "\n";

    i = "())(";
    a = isBalanced(i);
    std::cout << std::boolalpha << i << " = " << a << "\n";

    i = "(()";
    a = isBalanced(i);
    std::cout << std::boolalpha << i << " = " << a << "\n";

    i = "";
    a = isBalanced(i);
    std::cout << std::boolalpha << i << " = " << a << "\n";

}