#include <iostream>
#include <queue>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

static void addToPQRevY(
    priority_queue<Cartesian,vector<Cartesian>,ReverseYCartesianComparator>& cPQ, 
    Cartesian& c) {

    cPQ.push(c);

    if (cPQ.size() > 3) {
        cout << "  Remove: " << cPQ.top() << endl;
        cPQ.pop();
    }
}

static void addToPQ(
    priority_queue<Cartesian>& cPQ, 
    Cartesian& c) {

    cPQ.push(c);

    if (cPQ.size() > 3) {
        cout << "  Remove: " << cPQ.top() << endl;
        cPQ.pop();
    }
}

int main() {
    // Top 3 of 10 Cartesian (by y coord)
    priority_queue<Cartesian,vector<Cartesian>,ReverseYCartesianComparator> cPQ;

    cout << "Top 3 (highest y coord):\n";
    // Generate 10 Cartesians
    for (int i = 0; i < 10; i++) {
        Cartesian c(randomD(), randomD());
        addToPQRevY(cPQ, c);
    }

    // Print the 3 highest
    cout << "Result (highest y coord):";
    while (!cPQ.empty()) {
        cout << cPQ.top() << ", ";
        cPQ.pop();
    }
    cout << endl;

    // Top 3 of 10 Cartesian (by x coord (default))
    priority_queue<Cartesian> cPQ2;

    cout << "Top 3 (lowest x coord):\n";
    // Generate 10 Cartesians
    for (int i = 0; i < 10; i++) {
        Cartesian c(randomD(), randomD());
        addToPQ(cPQ2, c);
    }

    // Print the 3 highest
    cout << "Result (lowest x coord): ";
    while (!cPQ2.empty()) {
        cout << cPQ2.top() << ", ";
        cPQ2.pop();
    }
    cout << endl;
}
