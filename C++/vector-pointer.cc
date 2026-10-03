#include <iostream>
#include <vector>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

int main(int argc, char** argv) {
    srand(time(nullptr));

    vector<Cartesian> cVector;

    cout << "sizeof(Cartesian) = " << sizeof(Cartesian) << "\n";

    // Add 5 entries
    for (int i = 0; i < 5; i++) {
        cVector.emplace_back(randomD(), randomD());
    }

    cout << "Vector initialized:\n";
    cout << "  " << cVector << endl;

    // Print with object address
    cout << "Vector with object pointers:\n";
    for (int i = 0; i < cVector.size(); i++) {
        Cartesian *cPtr = &cVector[i];
        cout << "  [" << i << "] {" << hex << cPtr << "} ";
        cout << *cPtr << "\n";
    }

    // Print with foreach loop
    cout << "Vector (foreach) object pointers:\n";
    for (Cartesian &cRef : cVector) {
        Cartesian *cPtr = &cRef;
        cout << "  {" << hex << cPtr << "} ";
        cout << cRef << "\n";
    }
}
