#include <iostream>
#include <vector>
#include <set>

using namespace std;

void printAncestors(vector<set<int>>& ansList);

class Solution {
public:
    vector<set<int>> aaList;
    vector<vector<int>> retList;
    vector<bool> marked;

    void modifiedDFS(int i) {
        if (marked[i])
            return;

        for (int adj : aaList[i]) {
            modifiedDFS(adj);

            // Copy its ancestors to current
            for (int ans : aaList[adj]) {
                aaList[i].insert(ans);
            }
        }

        // sort(aaList[i].begin(), aaList[i].end());
        marked[i] = true;
    }

    vector<vector<int>>& getAncestors(int n, vector<vector<int>>& edges) {
        // Build REVERSE Graph
        aaList = vector<set<int>>(n, set<int>());
        for (vector<int>& edge : edges) {
            aaList[edge[1]].insert(edge[0]);
        }
        // printAncestors(aaList);

        // Run modified DFS which changes adj to ancestor
        marked = vector<bool>(n, false);
        for (int i = 0; i < n; i++) {
            modifiedDFS(i);
        }

        // Set to vector
        retList = vector<vector<int>>(n, vector<int>());
        for (int i = 0; i < aaList.size(); i++) {
            for (int x : aaList[i])
                retList[i].push_back(x);
        }

        return retList;
    }
};

void printAncestors(vector<set<int>>& ansList) {
    cout << "Size: " << ansList.size() << endl;
    for (int i = 0; i < ansList.size(); i++) {
        cout << i << " {";
        for (int x : ansList[i])
            cout << x << ", ";
        cout << "}\n";
    }
}

void printAncestors(vector<vector<int>>& ansList) {
    cout << "Size: " << ansList.size() << endl;
    for (int i = 0; i < ansList.size(); i++) {
        cout << i << " {";
        for (int j = 0; j < ansList[i].size(); j++)
            cout << ansList[i][j] << ", ";
        cout << "}\n";
    }
}

int main() {
    Solution s;

    // test 1
    int n = 8;
    vector<vector<int>> e = {
        {0, 3},
        {0, 4},
        {1, 3},
        {2, 4},
        {2, 7},
        {3, 5},
        {3, 6},
        {3, 7},
        {4, 6}
    };
    // s.getAncestors(n, e);
    printAncestors(s.getAncestors(n, e));

    // test 2
    n = 5;
    e = {
        {0, 1},
        {0, 2},
        {0, 3},
        {0, 4},
        {1, 2},
        {1, 3},
        {1, 4},
        {2, 3},
        {2, 4},
        {3, 4}
    };
    // s.getAncestors(n, e);
    printAncestors(s.getAncestors(n, e));

}