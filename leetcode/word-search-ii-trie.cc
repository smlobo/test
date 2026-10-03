/*
Given an m x n board of characters and a list of strings words, return all words 
on the board.

Each word must be constructed from letters of sequentially adjacent cells, where 
adjacent cells are horizontally or vertically neighboring. The same letter cell 
may not be used more than once in a word.
*/

#include <iostream>
#include <vector>
#include <string>

using namespace std;

ostream& operator<<(ostream& strm, vector<string>& v) {
    for (const string& s : v) {
        strm << s << ",";
    }
    return strm;
}

ostream& operator<<(ostream& strm, unordered_map<char,vector<int>>& m) {
    for (const auto& ele : m) {
        strm << ele.first << ":";
        for (const int& i: ele.second) {
            strm << i << ",";
        }
        strm << " ";
    }
    strm << endl;
    return strm;
}

class Solution {
public:
    int bRow;
    int bCol;
    vector<vector<bool>> marked;

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        // Initialize
        bRow = board.size();
        bCol = board[0].size();

        return ans;
    }
};

int main() {
    Solution s;

    // vector<vector<char>> board = {
    //     {'o','a','a','n'},
    //     {'e','t','a','e'},
    //     {'i','h','k','r'},
    //     {'i','f','l','v'}
    // };
    // vector<string> words = {"oath","pea","eat","rain"};
    // vector<string> ans = s.findWords(board, words);
    // cout << words << " : " << ans << endl;

    // vector<vector<char>> board = {
    //     {'a','b'},
    //     {'c','d'}
    // };
    // vector<string> words = {"abcd"};
    // vector<string> ans = s.findWords(board, words);
    // cout << words << " : " << ans << endl;

    // vector<vector<char>> board = {
    //     {'a','a'}
    // };
    // vector<string> words = {"aaa"};
    // vector<string> ans = s.findWords(board, words);
    // cout << words << " : " << ans << endl;

    vector<vector<char>> board = {
        {'a','b','c','e'},
        {'x','x','c','d'},
        {'x','x','b','a'}
    };
    vector<string> words = {"abc","abcd"};
    vector<string> ans = s.findWords(board, words);
    cout << words << " : " << ans << endl;

    // vector<vector<char>> board = {
    //     {'a','b'},
    //     {'a','a'}
    // };
    // vector<string> words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
    // // vector<string> words = {"aaba"};
    // vector<string> ans = s.findWords(board, words);
    // cout << words << " : " << ans << endl;

    // vector<vector<char>> board = {
    //     {'d','c','b'},
    //     {'a','a','a'}
    // };
    // vector<string> words = {"aabcda"};
    // vector<string> ans = s.findWords(board, words);
    // cout << words << " : " << ans << endl;
}

