#include <iostream>
#include <string>
#include <format>
#include <utility>

// Default copy & move constructor generated
class MyString {
public:
    int pad;
    std::string x;

    MyString(int p, std::string x)
        : pad(p), x(x) {}
};

// Since copy constructor defined, move constructor not generated
class MyStringCopy {
public:
    char pad;
    std::string x;

    MyStringCopy(char p, std::string x)
        : pad(p), x(x) {}
    MyStringCopy(const MyStringCopy& msc)
        : pad(msc.pad), x(msc.x) {}
};

// Both copy & move constructor defined
class MyStringMove {
public:
    float pad;
    std::string x;

    MyStringMove(float p, std::string x)
        : pad(p), x(x) {}
    MyStringMove(const MyStringMove& msm)
        : pad(msm.pad), x(msm.x) {}
    MyStringMove(MyStringMove&& msm)
        : pad(msm.pad), x(std::move(msm.x)) {}
};

int main(int argc, char *argv[]) {
    std::string x = "xxxyyyzzzsfgggggggggggs";
    if (argc == 2) {
        x = argv[1];
    }

    std::cout << "Default generated copy & move constructors:\n";
    MyString ms = MyString{10, x};
    std::cout << std::format("Original ms: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        ms.pad, ms.x, reinterpret_cast<unsigned long>(&ms), 
        reinterpret_cast<unsigned long>(&ms.x), 
        reinterpret_cast<unsigned long>(ms.x.data()));

    MyString msCopy = ms;
    std::cout << std::format("msCopy: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msCopy.pad, msCopy.x, reinterpret_cast<unsigned long>(&msCopy), 
        reinterpret_cast<unsigned long>(&msCopy.x), 
        reinterpret_cast<unsigned long>(msCopy.x.data()));

    MyString msMove = std::move(ms);
    std::cout << std::format("msMove: {} {} [{:#x} <{:#x} -> {:#x}>]\n\n", 
        msMove.pad, msMove.x, reinterpret_cast<unsigned long>(&msMove), 
        reinterpret_cast<unsigned long>(&msMove.x), 
        reinterpret_cast<unsigned long>(msMove.x.data()));

    //-------//

    std::cout << "Defined copy constructor, so no generated move constructor:\n";
    MyStringCopy msc = MyStringCopy{'X', x};
    std::cout << std::format("Original msc: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msc.pad, msc.x, reinterpret_cast<unsigned long>(&msc), 
        reinterpret_cast<unsigned long>(&msc.x), 
        reinterpret_cast<unsigned long>(msc.x.data()));

    MyStringCopy mscCopy = msc;
    std::cout << std::format("mscCopy: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        mscCopy.pad, mscCopy.x, reinterpret_cast<unsigned long>(&mscCopy), 
        reinterpret_cast<unsigned long>(&mscCopy.x), 
        reinterpret_cast<unsigned long>(mscCopy.x.data()));

    MyStringCopy mscMove = std::move(msc);
    std::cout << std::format("mscMove: {} {} [{:#x} <{:#x} -> {:#x}>]\n\n", 
        mscMove.pad, mscMove.x, reinterpret_cast<unsigned long>(&mscMove), 
        reinterpret_cast<unsigned long>(&mscMove.x), 
        reinterpret_cast<unsigned long>(mscMove.x.data()));

    //-------//

    std::cout << "Defined copy & move constructors:\n";
    MyStringMove msm = MyStringMove{1.0, x};
    std::cout << std::format("Original msm: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msm.pad, msm.x, reinterpret_cast<unsigned long>(&msm), 
        reinterpret_cast<unsigned long>(&msm.x), 
        reinterpret_cast<unsigned long>(msm.x.data()));

    MyStringMove msmCopy = msm;
    std::cout << std::format("msmCopy: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msmCopy.pad, msmCopy.x, reinterpret_cast<unsigned long>(&msmCopy), 
        reinterpret_cast<unsigned long>(&msmCopy.x), 
        reinterpret_cast<unsigned long>(msmCopy.x.data()));

    MyStringMove msmMove = std::move(msm);
    std::cout << std::format("msmMove: {} {} [{:#x} <{:#x} -> {:#x}>]\n", 
        msmMove.pad, msmMove.x, reinterpret_cast<unsigned long>(&msmMove), 
        reinterpret_cast<unsigned long>(&msmMove.x), 
        reinterpret_cast<unsigned long>(msmMove.x.data()));

}
