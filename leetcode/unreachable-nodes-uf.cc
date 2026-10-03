#include <iostream>
#include <vector>

using namespace std;

class Solution {
private:
    vector<int> id;
    int root(int v) {
        while (id[v] != v) {
            id[v] = id[id[v]];
            v = id[v];
        }
        return v;
    }

public:
    void unite(int p, int q) {
        id[root(p)] = root(q);
    }

    bool connected(int p, int q) {
        return root(p) == root(q);
    }

    long long countPairs(int n, vector<vector<int>>& edges) {
        // Union find
        id = vector<int>(n);
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }

        // Iterate over edges, adding them
        for (vector<int>& pair : edges) {
            unite(pair[0], pair[1]);
            // cout << pair[0] << " : " << pair[1] << endl;
        }

        // Count not connected vertices
        long long counter = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (!connected(i, j))
                    counter++;
            }
        }


        return counter;
    }
};

void printEdges(vector<vector<int>>& e) {
    for (int i = 0; i < e.size(); i++) {
        cout << "{";
        for (int j = 0; j < e[i].size(); j++) {
            cout << e[i][j] << ",";
        }
        cout << "},";
        if (i != 0 && (i%8) == 0) {
            cout << "\n";
        }
    }
    cout << "\n";
}

int main() {
    Solution s;

    // test 1
    // int n = 3;
    // vector<vector<int>> e = {
    //     {0, 1},
    //     {0, 2},
    //     {2, 1}
    // };
    // // printEdges(e);
    // cout << n << " : " << s.countPairs(n, e) << endl;

    // test 2
    int n = 7;
    vector<vector<int>> e = {
        {0, 2}, {0, 5}, {2,4}, {1,6}, {5,4}
    };
    // printEdges(e);
    cout << n << " : " << s.countPairs(n, e) << endl;
}