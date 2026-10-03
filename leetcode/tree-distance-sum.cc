/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and 
n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] 
indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances 
between the ith node in the tree and all other nodes.
*/

#include <iostream>
#include <vector>
#include <cassert>

using namespace std;

class Solution {
private:
    vector<vector<int>> adj;
public:

    int sumDistances(int n, int p, int d) {
        int sum = d;

        // Iterate over all children suming their depth
        for (const int& i : adj[n]) {
            if (i != p)
                sum += sumDistances(i, n, d+1);
        }

        return sum;
    }

    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        // Build the adj vector
        adj = vector<vector<int>>(n);
        for (const vector<int>& edge : edges) {
            adj[edge[0]].push_back(edge[1]);
            adj[edge[1]].push_back(edge[0]);
        }

        // Answer vector
        vector<int> a(n);

        // Iterate over all nodes. Treat each as the top of the tree
        for (int i = 0; i < n; i++)
            a[i] = sumDistances(i, -1, 0);

        return a;
    }
};

ostream& operator<<(ostream& strm, vector<int>& v) {
    for (const int& i : v)
        strm << i << ",";
    return strm;
}

int main() {
    Solution s;
    int n;
    vector<vector<int>> e;
    vector<int> a;

    n = 6;
    e = {{0,1},{0,2},{2,3},{2,4},{2,5}};
    a = s.sumOfDistancesInTree(n, e);
    cout << a << endl;
    assert(a == vector<int>({8,12,6,10,10,10}));

    n = 1;
    e = {};
    a = s.sumOfDistancesInTree(n, e);
    cout << a << endl;
    assert(a == vector<int>({0}));

    n = 2;
    e = {{1,0}};
    a = s.sumOfDistancesInTree(n, e);
    cout << a << endl;
    assert(a == vector<int>({1,1}));
}
