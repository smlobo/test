#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

ostream& operator<<(ostream& strm, vector<int>& v) {
    for (const int& i : v)
        strm << i << ",";
    return strm;
}

int main() {
    int n;
    cin >> n;
    // cout << " Got int: " << n << endl;

    int x;
    vector<int> numbers;
    for (int i = 0; i < n; i++) {
        cin >> x;
        numbers.push_back(x);
    }
    // cout << "List: " << numbers << endl;

    int q;
    cin >> q;

    for (int i = 0; i < q; i++) {
        cin >> x;

        auto iter = lower_bound(numbers.begin(), numbers.end(), x);
        int index = distance(numbers.begin(), iter);
        if (numbers[index] == x)
            cout << "Yes ";
        else 
            cout << "No ";
        index++;
        cout << index << endl;
    }
}