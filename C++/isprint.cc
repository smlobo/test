#include <iostream>

int main() {
    std::string origString = "é";
    std::string newString = "";
    for (unsigned char ch : origString) {
        if (!std::isprint(ch)) {
            char buffer[20];
            std::snprintf(buffer, sizeof(buffer), "\\u%04x", ch);
            newString.append(buffer);
        } else {
            newString.push_back(ch);
        }
    }
    std::cout << "Orig: " << origString << "; New: " << newString << "\n";
}
