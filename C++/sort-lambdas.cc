#include <iostream>
#include <random>
#include <chrono>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

int main(int argc, char **argv) {
    int n = stoi(argv[1]);
    cout << "Container size: " << n << endl;

    default_random_engine engine;
    engine.seed(chrono::system_clock::now().time_since_epoch().count());

    // Create vector
    vector<Cartesian> cartesians;
    for (int i = 0; i < n; i++)
        cartesians.push_back(Cartesian(randomD(), randomD()));
    cout << "Created vector:\n  ";
    cout << cartesians << endl;

    // Sort vector
    sort(cartesians.begin(), cartesians.end(), CartesianComparator());
    cout << "Sorted vector (default x axis):\n  ";
    cout << cartesians << endl;

    // Shuffle
    shuffle(cartesians.begin(), cartesians.end(), engine);
    cout << "Shuffled vector:\n  ";
    cout << cartesians << endl;

    // Lambda sort by y axis
    auto compareByYAxis = [](Cartesian& a, Cartesian& b) -> bool {
        return (a.y != b.y) ? (a.y < b.y) : (a.x < b.x);
    };
    sort(cartesians.begin(), cartesians.end(), compareByYAxis);
    cout << "Sorted vector (lambda y axis):\n  ";
    cout << cartesians << endl;

    // Shuffle
    shuffle(cartesians.begin(), cartesians.end(), engine);
    cout << "Shuffled vector:\n  ";
    cout << cartesians << endl;

    // Sort vector 2nd half
    sort(&cartesians[cartesians.size()/2], &cartesians[cartesians.size()], 
        CartesianComparator());
    cout << "Sort 2nd half vector:\n  ";
    cout << cartesians << endl;
}
