#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> aaList;
    vector<bool> marked;

    void modifiedDFS(int i) {
        if (marked[i])
            return;

        // Sort before DFS for this vertex
        sort(aaList[i].begin(), aaList[i].end());

        // Copy of the original list
        vector<int> copy(aaList[i]);

        for (int adj : aaList[i]) {
            modifiedDFS(adj);

            // Copy its ancestors to current - no duplicates
            vector<int> result(copy.size() + aaList[adj].size());
            vector<int>::iterator rit = set_union(copy.begin(), copy.end(), 
                aaList[adj].begin(), aaList[adj].end(), result.begin());
            result.resize(rit - result.begin());
            copy = result;
        }

        // Update the original
        aaList[i] = copy;
        marked[i] = true;
    }

    vector<vector<int>>& getAncestors(int n, vector<vector<int>>& edges) {
        // Build REVERSE Graph
        aaList = vector<vector<int>>(n, vector<int>());
        for (vector<int>& edge : edges) {
            aaList[edge[1]].push_back(edge[0]);
        }

        // Run modified DFS which changes adj to ancestor
        marked = vector<bool>(n, false);
        for (int i = 0; i < n; i++) {
            modifiedDFS(i);
        }

        return aaList;
    }
};

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