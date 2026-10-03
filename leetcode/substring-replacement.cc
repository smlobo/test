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

ostream& operator<<(ostream& ostrm, unordered_map<char,vector<char>>& m) {
    for (const auto& ele : m) {
        ostrm << "{'" << ele.first << "':";
        for (const char& c : ele.second) {
            ostrm << "'" << c << "',";
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
    unordered_map<char,vector<char>> mmap;
    unordered_set<string> subs;

    bool matchReplacement(string s, string sub, vector<vector<char>>& mappings) {

        // map of mappings
        for (vector<char> mapping : mappings) {
            char key = mapping[0];
            char val = mapping[1];
            vector<char>& replace = mmap[key];
            replace.push_back(val);
        }
        // cout << mmap << endl;

        // All possible substrings
        generateSubs(sub, 0);
        // cout << subs << endl;

        // Iterate over substrings looking for a match
        for (const string& nSub : subs) {
            if (s.find(nSub) != string::npos)
                return true;
        }
        return false;
    }

    void generateSubs(string& sub, int index) {
        // End of string
        if (index == sub.length()) {
            subs.insert(sub);
            return;
        }

        // Recurse for the original
        generateSubs(sub, index+1);

        // Alternates
        char curr = sub[index];

        // Iterate over all mappings
        if (mmap.find(curr) != mmap.end()) {
            vector<char>& replaces = mmap[curr];
            for (char& replace : replaces) {
                string rStr = generateReplace(sub, index, replace);
                generateSubs(rStr, index+1);
            }
        }
    }

    string generateReplace(string& sub, int index, char replace) {
        string replaceStr = sub;
        return replaceStr.replace(index, 1, string(1, replace));
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
