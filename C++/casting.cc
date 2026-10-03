#include <iostream>
// #include <format>
#include <cstdio>

using namespace std;

class Base {
public:
    void whoami() {
        cout << "    whoami? Base\n";
    }
    void unique() {
        cout << "    unique? Base\n";
    }
    virtual void virtualWhoami() {
        cout << "    virtual whoami? Base\n";
    }
};
class DerivedA : public Base {
public:
    void whoami() {
        cout << "    whoami? DerivedA\n";
    }
    virtual void virtualWhoami() {
        cout << "    virtual whoami? DerivedA\n";
    }
};
class DerivedB : public Base {
public:
    void whoami() {
        cout << "    whoami? DerivedB\n";
    }
    virtual void virtualWhoami() {
        cout << "    virtual whoami? DerivedB\n";
    }
};
class SomeOther {
public:
    void whoami() {
        cout << "    whoami? SomeOther\n";
    }
    virtual void virtualWhoami() {
        cout << "    virtual whoami? SomeOther\n";
    }
};

int main() {
    // Static cast
    printf("Static cast:\n");

    unsigned int x = 0xdeadbeef;
    float y = static_cast<float>(x);
    // cout << format("x = {:#0x}; y = {:10f}\n", x, y);
    printf("  int to float: \tx = %#x; y = %.6f\n", x, y);
    x = static_cast<unsigned int>(y);
    // cout << format("x = {:#0x}; y = {:10f}\n", x, y);
    printf("  float back to int: \tx = %#x; y = %.6f\n", x, y);

    x = 0xdeadbeef;
    char z = static_cast<char>(x);
    // cout << format("x = {:#0x}; z = {:#0x}/{:#0x}\n", x, z, (unsigned char)z);
    printf("  int to char: \t\tx = %#x; z = %#x / %#x\n", x, z, (unsigned char) z);
    x = static_cast<unsigned int>(z);
    // cout << format("x = {:#0x}; z = {:#0x}/{:#0x}\n", x, z, (unsigned char)z);
    printf("  char back to int: \tx = %#x; z = %#x / %#x\n", x, z, (unsigned char) z);

    // Reinterpret cast
    printf("\nReinterpret cast (ptr to unsigned long, then stack arithmetic):\n");
    int a = 10;
    int b = 20;
    int* iPtr = &a;
    printf("  a: [%p] %d; b: [%p] %d; *iPtr: [%p] %d\n", &a, a, &b, b, iPtr, *iPtr);
    unsigned long ptrUL = reinterpret_cast<unsigned long>(iPtr);
    ptrUL -= sizeof(int);
    printf("  iPtr: %p; ptrUL: %#lx\n", iPtr, ptrUL);
    iPtr = reinterpret_cast<int*>(ptrUL);
    printf("  a: [%p] %d; b: [%p] %d; *iPtr: [%p] %d\n", &a, a, &b, b, iPtr, *iPtr);

    // Reinterpret cast
    printf("\nReinterpret cast (instead of dynamic_cast):\n");
    SomeOther so2;
    printf("  Direct call to SomeOther:\n");
    so2.whoami();
    Base *basePtrSomeOther = reinterpret_cast<Base*>(&so2);
    if (!basePtrSomeOther) {
        printf("  reinterpret_cast<Base*> returned null\n");
    } else {
        basePtrSomeOther->whoami();
        basePtrSomeOther->unique();
    }

    // Const cast
    printf("\nConst cast:\n");
    const int p = 1000;
    int* const pPtr = const_cast<int*>(&p);
    printf("  Before: p = [%p] %d | *pPtr = [%p] %d\n", &p, p, pPtr, *pPtr);
    // p++;
    (*pPtr)++;
    printf("  After:  p = [%p] %d | *pPtr = [%p] %d\n", &p, p, pPtr, *pPtr);

    // Dynamic cast
    printf("\nDynamic cast:\n");
    DerivedA aa;
    printf("  Direct calls to derived:\n");
    aa.whoami();
    aa.virtualWhoami();
    Base &baseRef = aa;
    printf("  Calls from base reference:\n");
    baseRef.whoami();
    baseRef.virtualWhoami();
    DerivedA &derivedARef = dynamic_cast<DerivedA &>(baseRef);
    printf("  Calls from derived reference:\n");
    derivedARef.whoami();
    derivedARef.virtualWhoami();

    // Dynamic cast exception
    printf("\nDynamic cast throws exception:\n");
    SomeOther so;
    printf("  Direct calls to SomeOther:\n");
    so.whoami();
    so.virtualWhoami();
    try {
        DerivedB &bb = dynamic_cast<DerivedB &>(so);
    } catch (const std::bad_cast &e) {
        cout << "  Caught exception casting to DerivedB: " << e.what() << "\n";
    }
}