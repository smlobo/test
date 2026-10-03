#include <iostream>

class Solution {
public:
    bool buddyStrings(std::string s, std::string goal) {
        if (s.size() == 1 || (s.size() != goal.size())) {
            return false;
        }

        int charCount[26];
        for (int i = 0; i < 26; i++) {
            charCount[i] = 0;
        }

        char origChar = 'A';
        char goalChar = 'A';
        bool swapDone = false;

        for (int i = 0; i < s.size(); i++) {
            char sChar = s[i];
            char gChar = goal[i];

            int cIndex = sChar - 'a';
            charCount[cIndex] += 1;

            // No swap candidate
            if (origChar == 'A') {
                // Not equal - record index & goal char
                if (sChar != gChar) {
                    origChar = sChar;
                    goalChar = gChar;
                }
            } else {
                // swap complete
                if (swapDone) {
                    if (sChar != gChar) {
                        return false;
                    }
                } else {
                    if (sChar != gChar) {
                        if (sChar == goalChar && gChar == origChar) {
                            swapDone = true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        // Strings identical - swap possible if any count > 1
        if (origChar == 'A') {
            for (int i = 0; i < 26; i++) {
                if (charCount[i] > 1) {
                    return true;
                }
            }
            return false;
        }

        // Not identical - swap must be done
        return swapDone;
    }
};

int main() {
    Solution s;

    std::string s1 = "ab";
    std::string g1 = "ba";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "ab";
    g1 = "ab";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "aa";
    g1 = "aa";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "a";
    g1 = "ab";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "ab";
    g1 = "a";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "a";
    g1 = "a";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "a";
    g1 = "b";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "abcd";
    g1 = "acbd";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "abcdd";
    g1 = "abcdd";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    s1 = "abcd";
    g1 = "abcd";
    std::cout << s1 << " ~ " << g1 << " = " << std::boolalpha << s.buddyStrings(s1, g1) << "\n";

    return 0;
}
