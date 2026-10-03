#include <iostream>

typedef struct A {
    char x;
    int y;
    short z;
} A;

int main() {
    struct A a = {'a', 1000, 11};
    std::cout << "sizeof(struct A) == " << sizeof(struct A) << "\n";
    std::cout << "alignof(struct A) == " << alignof(struct A) << "\n";
    std::cout << "offsetof(A, x) == " << offsetof(A, x) << "\n";
    std::cout << "offsetof(A, y) == " << offsetof(A, y) << "\n";
    std::cout << "offsetof(A, z) == " << offsetof(A, z) << "\n";
    std::cout << "a.x, a.y, a.z = (" << a.x << a.y << a.z << ")\n";
    return 0;
}
