#include <iostream>
#include <unordered_map>
#include <cassert>
#include <cstdlib>
#include <ctime>

#include "cartesian.h"
// #include "color.h"
#include "utilities.h"

using namespace std;

int main(int argc, char** argv) {
    // Random seed based on time
    srand(time(nullptr));

    Cartesian c(0.0, 0.0);

    // Map
    // map<Cartesian,Color,CartesianComparator> ccMap;
    unordered_map<Cartesian,int,CartesianHash,CartesianEquals> ccMap = {{c, 0}};

    // Add 5 entries
    for (int i = 1; i < 5; i++) {
        c = Cartesian(randomD(), randomD());
        // cout << "Adding: " << c << endl;
        auto [it, success] = ccMap.insert({c, i});
        assert(success);
    }
    // Add bonus
    ccMap[Cartesian(randomD(), randomD())] = 88;
    cout << "Map initialized:\n";
    cout << "  " << ccMap << endl;

    // Lookup 1st item in the map
    Cartesian c1 = ccMap.begin()->first;
    int val = ccMap[c1];
    cout << "Lookup for: " << c1 << ", found: " << val << endl;

    // Lookup random
    Cartesian cX(randomD(), randomD());
    if (ccMap.find(cX) == ccMap.end())
        cout << "Did not find: " << cX << endl;
    val = ccMap[cX];
    cout << "Lookup for: " << cX << ", found: " << val << endl;
    if (ccMap.find(cX) == ccMap.end())
        cout << "Did not find: " << cX << endl;
    else
        cout << "Now found: " << cX << endl;

    // Lookup 2nd item
    Cartesian c2 = (ccMap.begin()++)->first;
    val = ccMap[c2];
    cout << "Lookup for: " << c2 << ", found: " << val << endl;
    int& valRef = ccMap[c2];
    cout << "Lookup for: " << c2 << ", val ref: " << valRef << endl;
    valRef = 1111;
    val = ccMap[c2];
    cout << "After set lookup for: " << c2 << ", now gets : " << val << endl;

    cout << "Map modified:\n";
    cout << "  " << ccMap << endl;

    // Cannot sort
    // sort(ccMap.begin(), ccMap.end(), CartesianComparator());
}
