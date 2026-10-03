#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

template <class T>
string vectorString(vector<T>& a) {
    // cout << "size: " << a.size() << endl;
    string retStr = "{";
    for (T s : a)
        retStr += s + ",";
    return retStr + "}";
}

template<>
string vectorString(vector<int>& a) {
    cout << "int size: " << a.size() << endl;
    string retStr = "{";
    for (int s : a)
        retStr += to_string(s) + ",";
    return retStr + "}";
}

string binaryString(int x, int chars) {
    string retStr = "";

    while (x != 0) {
        if (x & 1)
            retStr.insert(0, "1");
        else
            retStr.insert(0, "0");
        x >>= 1;
    }

    while (retStr.size() < chars) {
        retStr.insert(0, "0");
    }

    return retStr;
}

class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        // Convert to integer
        vector<int> intNums = {};
        for (string s : nums) {
            int n = stoi(s, nullptr, 2);
            intNums.push_back(n);
        }
        // cout << vectorString<int>(intNums) << endl;

        // Sort
        sort(intNums.begin(), intNums.end());

        // Find missing
        int counter = 0;
        for (int existing : intNums) {
            if (counter != existing)
                break;
            counter++;
        }

        return binaryString(counter, nums.size());
    }
};

int main() {
    Solution *s = new Solution();

    // test 1
    // vector<string> x = {"01", "10"};
    // string answer = s->findDifferentBinaryString(x);
    // cout << vectorString<string>(x) << " : " << answer << endl;

    // test 2
    // vector<string> x = {"00", "01"};
    // string answer = s->findDifferentBinaryString(x);
    // cout << vectorString<string>(x) << " : " << answer << endl;

    // test 3
    vector<string> x = {"111", "011", "001"};
    string answer = s->findDifferentBinaryString(x);
    cout << vectorString<string>(x) << " : " << answer << endl;
}