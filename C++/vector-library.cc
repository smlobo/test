#include <iostream>
#include <vector>
#include <string>
#include <cassert>

#include "utilities.h"

using namespace std;

int main() {
    vector<string> strVector(4);
    cout << "Initialized to empty: " << strVector.size() << "\n";
    cout << strVector << endl;

    // Add
    strVector.push_back("XX");
    strVector.push_back("YY");

    // Replace
    strVector[1] = "AA";
    strVector[3] = "CC";
    cout << "push_back & replaced:\n";
    cout << strVector << endl;

    // Sort
    sort(strVector.begin(), strVector.end());
    assert(is_sorted(strVector.begin(), strVector.end()));
    cout << "Sorted:\n";
    cout << strVector << endl;

    // 2nd vector
    vector<string>* strVector2Ptr = new vector<string>(3, "P");
    cout << "2nd vector:\n";
    cout << *strVector2Ptr << endl;

    // Merge
    vector<string> cStrVec;
    cStrVec.reserve(strVector.size() + strVector2Ptr->size());
    cout << "Combined (before):\n";
    cout << cStrVec << endl;

    merge(strVector.begin(), strVector.end(), strVector2Ptr->begin(), 
        strVector2Ptr->end(), back_inserter(cStrVec));
    cout << "Combined (after):\n";
    cout << cStrVec << endl;

    delete strVector2Ptr;
}
