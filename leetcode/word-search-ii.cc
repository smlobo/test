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

    bool findWord(vector<vector<char>>& board, const string& word, int index, 
        int i, int j) {

        cout << index << " : " << i << "," << j << endl;
        // End
        if (index == word.length())
            return true;

        // Visited
        if (marked[i][j])
            return false;

        // No match
        if (word[index] != board[i][j])
            return false;

        marked[i][j] = true;

        // Left
        if (j > 0) {
            if (findWord(board, word, index+1, i, j-1))
                return true;
        }
        // Right
        if (j < bCol-1) {
            if (findWord(board, word, index+1, i, j+1))
                return true;
        }
        // Top
        if (i > 0) {
            if (findWord(board, word, index+1, i-1, j))
                return true;
        }
        // Bottom
        if (i < bRow-1) {
            if (findWord(board, word, index+1, i+1, j))
                return true;
        }

        if (index+1 == word.length())
            return true;

        // Undo 
        marked[i][j] = false;
        return false;
    }

    void resetMarked() {
        for (int i = 0; i < bRow; i++) {
            for (int j = 0; j < bCol; j++) {
                marked[i][j] = false;
            }
        }
    }

    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        // Initialize
        bRow = board.size();
        bCol = board[0].size();
        for (int i = 0; i < bRow; i++) {
            marked.push_back(vector<bool>(bCol));
        }

        // Map of char to positions
        unordered_map<char,vector<int>> cMap;
        for (int i = 0; i < bRow; i++) {
            for (int j = 0; j < bCol; j++) {
                int pos = i*bCol + j;
                char c = board[i][j];
                vector<int>& cVec = cMap[c];
                cVec.push_back(pos);
            }
        }
        // cout << cMap;

        // Iterate over words searching for a match in board
        vector<string> ans;
        for (const string& word : words) {

            cout << word << endl;

            // Get starting position
            if (cMap.find(word[0]) == cMap.end()) {
                // cout << "no starting: " << word[0] << endl;
                continue;
            }

            // for (const int& start : cMap[word[0]]) {
            vector<int> starts = cMap[word[0]];
            for (const int& start : starts) {
                resetMarked();
                cout << "start: " << start << endl;
                // Board pos
                int i = start/bCol;
                int j = start%bCol;
                if (findWord(board, word, 0, i, j)) {
                    ans.push_back(word);
                    break;
                }
            }
        }

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

