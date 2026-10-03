#include <iostream>
#include <vector>
#include <string>

using namespace std;


ostream& operator<<(ostream& strm, vector<int>& v) {
    for (int i : v)
        strm << i << ", ";
    return strm;
}

class Solution {
public:
    vector<int> movesToStamp(string stamp, string target) {
        vector<int> result;

        return result;
    }
};

int main() {
    Solution s;

    string stamp = "abc";
    string t = "ababc";
    vector<int> r = s.movesToStamp(stamp, t);
    cout << stamp << " - " << t << " : " << r << endl;
}
