#include <iostream>
#include <string>
#include <set>

using namespace std;

class Solution {
public:
    int partitionString(string s) {
        set<char> charSet;
        int substrCount = 1;

        for (int i = 0; i < s.length(); i++) {
            char x = s[i];

            if (charSet.count(x) > 0) {
                substrCount++;
                charSet.clear();
            }
            charSet.insert(x);
        }

        return substrCount;
    }
};

int main() {
    Solution s;

    // test 1
    string t = "abacaba";
    cout << t << " partioned into: " << s.partitionString(t) << endl;

    // test 2
    t = "ssssss";
    cout << t << " partioned into: " << s.partitionString(t) << endl;
}