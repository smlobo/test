/*
You are given two strings s and sub. You are also given a 2D character array 
mappings where mappings[i] = [oldi, newi] indicates that you may perform the 
following operation any number of times:

Replace a character oldi of sub with newi.
Each character in sub cannot be replaced more than once.

Return true if it is possible to make sub a substring of s by replacing zero or 
more characters according to mappings. Otherwise, return false.

A substring is a contiguous non-empty sequence of characters within a string.
*/

#include <iostream>
#include <unordered_map>
#include <unordered_set>
#include <string>

using namespace std;

ostream& operator<<(ostream& ostrm, unordered_map<char,unordered_map<char,bool>>& m) {
    for (const auto& ele : m) {
        ostrm << "{'" << ele.first << "':";
        for (const auto& cb : ele.second) {
            ostrm << "'" << cb.first << "',";
        }
        ostrm << "}";
    }
    return ostrm;
}

ostream& operator<<(ostream& ostrm, vector<vector<char>>& v) {
    for (vector<char> vv : v) {
        ostrm << "{'" << vv[0] << "','" << vv[1] << "'},";
    }
    return ostrm;
}

ostream& operator<<(ostream& ostrm, unordered_set<string>& v) {
    for (const string& s : v) {
        cout << s << ",";
    }
    return ostrm;
}

class Solution {
public:
    unordered_map<char,unordered_map<char,bool>> mmap;

    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {

        // map of mappings
        for (vector<char> mapping : mappings) {
            char key = mapping[0];
            char val = mapping[1];
            mmap[key][val] = true;
        }
        // cout << mmap << endl;

        // Check if match occurs
        for (int i = 0; i < s.length(); i++) {
            if (checkMatch(s, i, sub))
                return true;
        }
        return false;
    }

    bool checkMatch(string& s, int pos, string& sub) {
        int k = sub.length();

        // Too small
        if ((pos + k) > s.length())
            return false;

        for (int i = 0; i < k; i++) {
            char m = s[pos+i];
            char p = sub[i];
            // cout << pos << ", " << i << ", " << m << ", " << p << endl;
            if (m != p && !mmap[p][m])
                return false;
        }
        return true;
    }
};

int main() {
    Solution s;
    cout << boolalpha;

    // string t = "fool3e7bar";
    // string sub = "leet";
    // vector<vector<char>> mappings = {{'e','3'},{'t','7'},{'t','8'}};
    // cout << t << " : " << sub << " + " << mappings << " =\n";
    // cout << s.matchReplacement(t, sub, mappings) << endl;

    // string t = "fooleetbar";
    // string sub = "f00l";
    // vector<vector<char>> mappings = {{'o','0'}};
    // cout << t << " : " << sub << " + " << mappings << " =\n";
    // cout << s.matchReplacement(t, sub, mappings) << endl;

    string t = "Fool33tbaR";
    string sub = "leetd";
    vector<vector<char>> mappings = {{'e','3'},{'t','7'},{'t','8'},{'d','b'},{'p','b'}};
    cout << t << " : " << sub << " + " << mappings << " =\n";
    cout << s.matchReplacement(t, sub, mappings) << endl;
}
