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

using namespace std;

class Solution {
public:
    string removeStars(string s) {
        // cout << endl;

        // Scan & mark
        int lastNonStar = 0;
        int newLength = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s[i] == '*') {
                s[i] = 'X';
                s[lastNonStar] = 'X';
                newLength -= 2;

                // Reverse scan to find the last non star
                for (int j = lastNonStar-1; j >= 0; j--) {
                    if (s[j] != 'X') {
                        lastNonStar = j;
                        break;
                    }
                }

                // cout << i << " ~ " << lastNonStar << endl;
            }
            else {
                lastNonStar = i;
            }
        }

        // Optimizations
        if (newLength == 0)
            return "";
        else if (newLength == 1) {
            return string(1, s[lastNonStar]);
        }

        string retString(newLength, '\0');
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s[i] != 'X')
                retString[j++] = s[i];
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