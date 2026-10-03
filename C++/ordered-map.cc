#include <iostream>
#include <map>
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

    // Map
    // map<Cartesian,Color,CartesianComparator> ccMap;
    map<Cartesian,int,CartesianComparator> ccMap;

    // Add 5 entries
    for (int i = 0; i < 5; i++) {
        Cartesian c(randomD(), randomD());
        // cout << "Adding: " << c << endl;
        auto [it, success] = ccMap.insert({c, i});
        assert(success);
    }
    cout << "Map initialized:\n";
    cout << "  " << ccMap << endl;

    // Add 3 more entries
    for (int i = 10; i < 13; i++)
        ccMap[Cartesian(randomD(), randomD())] = i;
    cout << "Added 3 more:\n";
    cout << "  " << ccMap << endl;

    // -ve x coords
    cout << "Coords left of y-axis:\n  ";
    for (auto it = ccMap.begin(); it != ccMap.lower_bound(Cartesian(0.0, 0.0)); it++)
        cout << "{" << it->first << ":" << it->second << "}, ";
    cout << endl;

    // -0.5 to +0.5
    cout << "-.5 to +.5:\n  ";
    for (auto it = ccMap.lower_bound(Cartesian(-0.5, 0.0)); 
        it != ccMap.upper_bound(Cartesian(0.5, 0.0)); it++)
        cout << "{" << it->first << ":" << it->second << "}, ";
    cout << endl;
}
