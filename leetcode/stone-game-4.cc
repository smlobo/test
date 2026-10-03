/*
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes 
a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game 
otherwise return false, assuming both players play optimally.
*/

#include <iostream>
#include <stack>

using namespace std;

class Solution {
public:
    bool winnerSquareGame(int n) {
        // Get valid plays until n
        stack<int> valids;
        int count = 1;
        int square = 1;
        while (square <= n) {
            valids.push(square);
            count++;
            square = count*count;
        }

        // Play until 0
        bool winner = false;
        int currentValid = valids.top();
        valids.pop();
        while (n > 0) {
            // current valid too bug
            if (currentValid > n) {
                currentValid = valids.top();
                valids.pop();
            }

            n -= currentValid;
            winner = !winner;
        }

        return winner;
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
    cout << "25 -> " << s.winnerSquareGame(25) << endl;
    cout << "26 -> " << s.winnerSquareGame(26) << endl;
    cout << "13 -> " << s.winnerSquareGame(13) << endl;
    cout << "14 -> " << s.winnerSquareGame(14) << endl;
    cout << "38 -> " << s.winnerSquareGame(38) << endl;
    cout << "39 -> " << s.winnerSquareGame(39) << endl;

}