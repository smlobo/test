#include <iostream>

using namespace std;

class Foo {
public:
    static int sInt;
    int objInt;
    int *ptrInt;
};

int Foo::sInt = 100;

int main() {
    static int sInt = 10;
    int lInt = 20;
    int *hInt = new int;
    *hInt = 30;

    cout << "Static: [" << &sInt << "] " << sInt << endl;
    cout << "Local: [" << &lInt << "] " << lInt << endl;
    cout << "Dynamic: [" << hInt << "] " << *hInt << endl;

    Foo f;
    f.objInt = 200;
    f.ptrInt = new int;
    *(f.ptrInt) = 300;

    cout << "Class : [" << &f << "] " << endl;
    cout << "Static class member: [" << &Foo::sInt << "] " << Foo::sInt << endl;
    cout << "Obj member: [" << &f.objInt << "] " << f.objInt << endl;
    cout << "Obj ptr: [" << f.ptrInt << "] " << *(f.ptrInt) << endl;

    delete hInt;
}
