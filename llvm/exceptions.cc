#include <iostream>
#include <string>

#include "class-hierarchy.h"

using namespace std;

void doSomething(string& x) {
    if (x == "maple") {
        throw Maple();
    }
    else if (x == "int") {
        throw 100;
    }
    else if (x == "oak") {
        throw Oak();
    }
    else if (x == "tree") {
        throw Tree();
    }
    else if (x == "float") {
        throw 3.14;
    }
    cout << "No exception thrown\n";
}

int main(int argc, char** argv) {
    string s = argv[1];
    cout << "Got input: " << s << endl;

    try {
        doSomething(s);
    }
    catch(Maple& e) {
        cout << "Caught a Maple exception ... " << e.type() << endl;
    }
    catch(Tree& e) {
        cout << "Caught a Tree exception ... " << e.type() << endl;
    }
    catch(int e) {
        cout << "Caught an integer exception ... " << e << endl;
    }
    catch(...) {
        cout << "Caught a generic exception ..." << endl;
    }
}