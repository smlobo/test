#include <iostream>
#include <list>
#include <cstdlib>
#include <ctime>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

int main(int argc, char** argv) {
    srand(time(nullptr));

    Cartesian c(0.0, 0.0);

    // DLL
    list<Cartesian> ccList = {c};

    // Add 5 entries
    for (int i = 0; i < 5; i++) {
        c = Cartesian(randomD(), randomD());
        ccList.push_back(c);
    }

    cout << "List initialized:\n";
    cout << "  " << ccList << endl;

    // BAD!!! Since no indexing, cannot use std::sort; use std::list's sort
    // sort(ccList.begin(), ccList.end());
    ccList.sort(CartesianComparator());
    cout << "List sorted:\n";
    cout << "  " << ccList << endl;
}
