#include <iostream>

class MyClass {
public:
    int field;

    MyClass(int f) : field(f) {}

    MyClass& operator=(const MyClass& other) {
        // Check for self-assignment
        if (this == &other) {
            std::cout << "Self assignment detected\n";
            return *this;  // Early return to avoid unnecessary work
        }

        // Perform the actual assignment
        field = other.field;
        field++;
        std::cout << "NO self assignment\n";

        return *this;
    }
};

int main() {
    MyClass obj1(10);
    MyClass obj2(20);
    obj2 = obj1;  // Copy assignment

    std::cout << "obj1 [" << &obj1 << "] " << obj1.field << "\n";
    std::cout << "obj2 [" << &obj2 << "] " << obj2.field << "\n";

    obj1 = obj1;  // Self-assignment, should be avoided

    return 0;
}
