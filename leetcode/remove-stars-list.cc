/*
You are given a string s, which contains stars *.

In one operation, you can:

Choose a star in s.
Remove the closest non-star character to its left, as well as remove the star itself.
Return the string after all stars have been removed.

Note:

The input will be generated such that the operation is always possible.
It can be shown that the resulting string will always be unique.

*/

#include <iostream>
#include <string>
#include <list>

using namespace std;

class Solution {
public:
    string removeStars(string s) {
        // list of valid chars
        list<char> valids;

        // Scan and update valids
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == '*') {
                valids.pop_back();
            }
            else {
                valids.push_back(s[i]);
            }
        }

        string retString(valids.size(), '\0');
        int j = 0;
        for (char& c : valids) {
            retString[j++] = c;
        }

        return retString;
    }
};

int main() {
    Solution s;

    // test 1
    string t = "leet**cod*e";
    cout << t << " -> " << s.removeStars(t) << endl;

    // test 1
    t = "erase*****";
    cout << t << " -> " << s.removeStars(t) << endl;

    // test 3
    t = "x*f*zt*a**t*i*rs*ggw*yb*j*y";
    cout << t << " -> " << s.removeStars(t) << endl;

    // test 4
    t = "x*f*zt*a**t*i*rs*ggw*yb*j*y****";
    cout << t << " -> " << s.removeStars(t) << endl;

    // test 4
    t = "x*f*zt*a**t*i*rs*gg*w**yb**j**y";
    cout << t << " -> " << s.removeStars(t) << endl;
}