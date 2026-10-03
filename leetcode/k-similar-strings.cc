/*
Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap 
the positions of two letters in s1 exactly k times so that the resulting string 
equals s2.

Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are 
k-similar.
*/

#include <iostream>
#include <string>
#include <queue>
#include <unordered_set>
#include <cassert>

using namespace std;

class Solution {
public:
    void sSwap(string& x, int a, int b) {
        char t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    int kSimilarity(string s1, string s2) {
        // Hash map of tried strings
        unordered_set<string> tried;

        // Queue of strings to try along with moves
        queue<pair<string,int>> bfs;

        // Initialize
        bfs.push({s1, 0});
        tried.insert(s1);

        // Iterate over BFS queue
        while (!bfs.empty()) {
            // Get oldest from queue
            pair<string,int> p = bfs.front();
            bfs.pop();

            string s = p.first;
            int k = p.second;

            // Match?
            if (s == s2)
                return k;

            // Generate all combinations
            for (int i = 0; i < s.length(); i++) {
                for (int j = i+1; j < s.length(); j++) {
                    sSwap(s, i, j);

                    // Not previously tried - push to bfs queue
                    if (tried.find(s) == tried.end())
                        bfs.push({s, k+1});

                    sSwap(s, i, j);
                }
            }
        }

        return -1;
    }
};

int main() {
    Solution s;
    string s1, s2;
    int k;

    s1 = "ab";
    s2 = "ba";
    k = s.kSimilarity(s1, s2);
    cout << k << endl;
    assert(k == 1);

    s1 = "abc";
    s2 = "bca";
    k = s.kSimilarity(s1, s2);
    cout << k << endl;
    assert(k == 2);

    s1 = "abccaacceecdeea";
    s2 = "bcaacceeccdeaae";
    k = s.kSimilarity(s1, s2);
    cout << k << endl;
    assert(k == 2);
}