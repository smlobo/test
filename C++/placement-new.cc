#include <iostream>

class MyClass {
public:
    MyClass(int value) : data(value) {
        std::cout << "Constructor called: " << data << std::endl;
    }

    ~MyClass() {
        std::cout << "Destructor called: " << data << std::endl;
    }

private:
    int data;
};

int main() {
    void* memory = operator new(sizeof(MyClass));  // Allocate memory

    MyClass* obj = new (memory) MyClass(42);  // Construct object at memory address

    obj->~MyClass();  // Explicitly call the destructor

    operator delete(memory);  // Deallocate memory

    return 0;
}
