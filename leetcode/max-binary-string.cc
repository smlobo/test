#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    string maximumBinaryString(string binary) {
        string result = binary;
        int previousZeroIndex = -1;

        for (int i = 0; i < result.length(); i++) {
            if (result[i] == '0') {
                if (previousZeroIndex != -1) {
                    result[previousZeroIndex] = '1';
                    result[previousZeroIndex+1] = '0';
                    result[i] = '1';
                    previousZeroIndex++;
                }
                else if (i+1 < result.length() && result[i+1] == '0') {
                    result[i] = '1';
                }
                else {
                    previousZeroIndex = i;
                }
            }
        }
        return result;
    }
};

int main() {
    Solution s;

    // test 1
    string t = "000110";
    cout << t << " -> " << s.maximumBinaryString(t) << endl;
}