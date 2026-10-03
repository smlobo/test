#include <sstream>
#include <vector>
#include <iostream>
using namespace std;

vector<int> parseInts(string str) {
    // Complete this function
    stringstream ss(str);
    vector<int> vec;
    
    char comma;
    int x;
    if (!(ss >> x))
        return vec;
    vec.push_back(x);
    while (ss >> comma) {
        ss >> x;
        vec.push_back(x);
    }
    
    return vec;
}

int main() {
    string str;
    cin >> str;
    vector<int> integers = parseInts(str);
    for(int i = 0; i < integers.size(); i++) {
        cout << integers[i] << "\n";
    }
    
    return 0;
}
