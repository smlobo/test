#include <iostream>
#include <vector>

using namespace std;

ostream& operator<<(ostream& strm, vector<int>& v) {
    for (const int& i : v)
        strm << i << ",";
    return strm;
}

int main() {
    // Read int from stdin
    int n;
    cin >> n;
    cout << " Got int: " << n << endl;

    // Read ints on same line
    int x;
    vector<int> numbers;
    for (int i = 0; i < n; i++) {
        cin >> x;
        numbers.push_back(x);
    }
    cout << "List: " << numbers << endl;

    // Read string & int on a line
    string s;
    for (int i = 0; i < n; i++) {
        cin >> s >> x;
        cout << "string + int: " << s << " + " << x << endl;
    }
}