#include <cassert>
#include <iostream>
#include <memory>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

unique_ptr<Cartesian> addRef(unique_ptr<Cartesian> &x, unique_ptr<Cartesian> &y) {
    auto result = make_unique<Cartesian>(*x + *y);
    return result;
}

unique_ptr<Cartesian> addOwn(unique_ptr<Cartesian> x, unique_ptr<Cartesian> y) {
    auto result = make_unique<Cartesian>(*x + *y);
    return result;
}

int main(int argc, char** argv) {
    // Not needed - utilities.cc PreMain
    // srand(time(nullptr));

    unique_ptr<Cartesian> owner1 = make_unique<Cartesian>(randomD(), randomD());
    auto owner2 = make_unique<Cartesian>(randomD(), randomD());

    cout << "Created 2 unique Cartesian objects:\n";
    cout << "  " << *owner1 << "\n";
    cout << "  " << *owner2 << "\n";

    auto addOwner1 = addRef(owner1, owner2);
    cout << "Result of reference addition:\n";
    cout << "  " << *owner1 << " + " << *owner2 << " = " << *addOwner1 << "\n";

    auto addOwner2 = addOwn(std::move(owner1), std::move(owner2));
    assert(owner1.get() == nullptr);
    assert(owner2.get() == nullptr);
    cout << "Result of ownership addition:\n";
    cout << "  = " << *addOwner2 << "\n";

    // Array
    auto ownerArray = make_unique<Cartesian[]>(5);
    for (int i = 0; i < 5; i++) {
        ownerArray[i] = Cartesian(randomD(), randomD());
    }
    cout << "Array of 5 unique Cartesians:\n  ";
    for (int i = 0; i < 5; i++) {
        cout << ownerArray[i] << ", ";
    }
    cout << endl;
    // sort(ownerArray.get(), ownerArray.get()+5, CartesianComparator());
    sort(&ownerArray[0], &ownerArray[5], CartesianComparator());
    cout << "Sorted:\n  ";
    for (int i = 0; i < 5; i++) {
        cout << ownerArray[i] << ", ";
    }
    cout << endl;
}
