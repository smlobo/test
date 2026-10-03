/*
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes 
a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game 
otherwise return false, assuming both players play optimally.
*/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <cmath>

using namespace std;

class Solution {
public:
    vector<int> squares;
    unordered_map<int,bool> resultMap; 

    int getSquare(int i) {
        // TODO use cache
        return i*i;
    }

    bool winnerSquareGame(int n) {
        // end condition
        if (n == 1)
            return true;
        else if (n == 0)
            return false;
        else if (resultMap.find(n) != resultMap.end())
            return resultMap[n];

        // Iterate thru' all options
        // int sqRoot = (int) sqrt(n);
        //for (int i = 0; i <= n; i++) {
        int i = 1;
        while (true) {
            int sq = getSquare(i);
            if (sq > n)
                break;

            if (!winnerSquareGame(n-sq)) {
                resultMap[n] = true;
                return true;
            }

            i++;
        }

        // All paths are already winners, we lose
        resultMap[n] = false;
        return false;
    }
};

int main() {
    Solution s;
    cout << boolalpha;

    // test 1
    cout << "1 -> " << s.winnerSquareGame(1) << endl;

    // test 2
    cout << "2 -> " << s.winnerSquareGame(2) << endl;

    // test 3
    cout << "4 -> " << s.winnerSquareGame(4) << endl;

    cout << "3 -> " << s.winnerSquareGame(3) << endl;
    cout << "5 -> " << s.winnerSquareGame(5) << endl;
    cout << "6 -> " << s.winnerSquareGame(6) << endl;
    cout << "7 -> " << s.winnerSquareGame(7) << endl;
    cout << "8 -> " << s.winnerSquareGame(8) << endl;
    cout << "9 -> " << s.winnerSquareGame(9) << endl;
    cout << "10 -> " << s.winnerSquareGame(10) << endl;

    cout << "25 -> " << s.winnerSquareGame(25) << endl;
    cout << "26 -> " << s.winnerSquareGame(26) << endl;
    cout << "13 -> " << s.winnerSquareGame(13) << endl;
    cout << "14 -> " << s.winnerSquareGame(14) << endl;
    cout << "38 -> " << s.winnerSquareGame(38) << endl;
    cout << "39 -> " << s.winnerSquareGame(39) << endl;

}