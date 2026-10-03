#include <iostream>
#include <string>
#include <format>
#include <utility>

// Default copy & move constructor generated
class MyString {
public:
    int pad;
    std::string x;

    MyString()
        : pad(-1), x("") {}
    MyString(int p, std::string x)
        : pad(p), x(x) {}
};

MyString msGlobal;

void foo(const MyString& ms) {
    std::cout << "foo() const ref:\n";
    std::cout << std::format("\t{} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad, ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));
}

void foo(MyString& ms) {
    std::cout << "foo() ref:\n";
    ms.pad += 1;
    std::cout << std::format("\t{} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad, ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));
}

void foo(MyString&& ms) {
    std::cout << "foo() move:\n";
    ms.pad += 1;
    msGlobal = std::move(ms);
    std::cout << std::format("\t(Global) {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msGlobal.pad, msGlobal.x, reinterpret_cast<unsigned long>(&msGlobal), 
        reinterpret_cast<unsigned long>(&msGlobal.x), 
        reinterpret_cast<unsigned long>(msGlobal.x.data()));
}

// Forwarding
template<typename T>
void callFoo(T&& t) {
    foo(std::forward<T>(t));
}

int main(int argc, char *argv[]) {
    std::string x = "xxxyyyzzzsfgggggggggggs";
    if (argc == 2) {
        x = argv[1];
    }

    std::cout << "const + non-const:\n";
    const MyString cms = MyString{10, x};
    std::cout << std::format("\tOriginal cms: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        cms.pad, cms.x, reinterpret_cast<unsigned long>(&cms), 
        reinterpret_cast<unsigned long>(&cms.x), 
        reinterpret_cast<unsigned long>(cms.x.data()));
    MyString ms = MyString{20, x};
    std::cout << std::format("\tOriginal ms: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad, ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));

    foo(cms);               // const ref
    foo(ms);                // ref
    foo(MyString{30, x});   // temp (std::move)
    foo(std::move(cms));    // try std::move w/ const, fallback to const ref
    foo(std::move(ms));     // std::move

    std::cout << "\nForward:\n";
    const MyString cms2 = MyString{100, x};
    std::cout << std::format("\tNew cms2: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        cms2.pad, cms.x, reinterpret_cast<unsigned long>(&cms2), 
        reinterpret_cast<unsigned long>(&cms2.x), 
        reinterpret_cast<unsigned long>(cms2.x.data()));
    ms = MyString{200, x};
    std::cout << std::format("\tNew ms: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad, ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));

    callFoo(cms2);
    callFoo(ms);
    callFoo(MyString{300, x});
    callFoo(std::move(cms2));
    callFoo(std::move(ms));
}
