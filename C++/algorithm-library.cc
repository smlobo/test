#include <iostream>
#include <algorithm>
#include <cassert>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;
 
int main(int argc, char** argv) {
    // replace X with Y
    string s("aaaXaaaXXaaXXXaXXXXaaa");
    replace(s.begin(), s.end(), 'X', 'Y');
    assert(s == "aaaYaaaYYaaYYYaYYYYaaa");
    cout << s << endl;
    replace(&s[5], &s[14], 'Y', 'Z');
    assert(s == "aaaYaaaZZaaZZZaYYYYaaa");
    cout << s << endl;

    // generate a random 100 int vector; search for user input in it
    srand(time(nullptr));
    vector<int> nList(100, 0);
    for (int i = 0; i < nList.size(); i++) {
        nList[i] = rand() % 100;
    }
    cout << "[before] Is sorted? " << is_sorted(nList.begin(), nList.end()) << endl;
    sort(nList.begin(), nList.end());
    // ranges::sort(nList);
    cout << "[after] Is sorted? " << is_sorted(nList.begin(), nList.end()) << endl;
    int sNum = stoi(argv[1]);
    cout << "Looking for: " << sNum << endl;
    if (binary_search(nList.begin(), nList.end(), sNum)) {
        int index =  distance(nList.begin(), 
            lower_bound(nList.begin(), nList.end(), sNum));
        cout << "Found! index = " << index << endl;
        int i = (index > 5) ? (index - 5) : 0;
        for (; i < index+5 && i < nList.size(); i++)
            cout << nList[i] << ", ";
        cout << endl;
    }
    else {
        int index =  distance(nList.begin(), 
            lower_bound(nList.begin(), nList.end(), sNum));
        cout << "NOT found. index = " << index << endl;
    }
}
