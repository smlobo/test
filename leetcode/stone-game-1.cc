/*
Alice and Bob play a game with piles of stones. There are an even number of piles 
arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of 
stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the 
entire pile of stones either from the beginning or from the end of the row. This 
continues until there are no more piles left, at which point the person with the 
most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or 
false if Bob wins.
*/

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:

    bool stoneGameDfs(vector<int>& v, int p, int q) {
        // End condition - adj piles
        if (p == (q-1))
            return true;

        // Go left
        bool leftResult = stoneGameDfs(v, p+1, q);
        if (!leftResult)
            return true;

        // Go right
        bool rightResult = stoneGameDfs(v, p, q-1);
        if (!rightResult)
            return true;

        return false;
    }

    bool stoneGame(vector<int>& piles) {
        return stoneGameDfs(piles, 0, piles.size()-1);
    }
};

char buf[100];

string vectorString(vector<int>& v) {
    int i = 0;
    i += sprintf(buf, "{");
    for (int& x : v) {
        i += sprintf(buf+i, "%d,", x);
    }
    i += sprintf(buf+i, "}");
    return buf;
}

int main() {
    Solution s;
    cout << boolalpha;

    vector<int> v = {5,3,4,5};
    cout << vectorString(v) << " : " << s.stoneGame(v) << endl;

    v = {3,7,2,3};
    cout << vectorString(v) << " : " << s.stoneGame(v) << endl;

    v = {3,4,3,5};
    cout << vectorString(v) << " : " << s.stoneGame(v) << endl;
}
