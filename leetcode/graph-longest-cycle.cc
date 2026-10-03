/*
You are given a directed graph of n nodes numbered from 0 to n - 1, where each 
node has at most one outgoing edge.

The graph is represented with a given 0-indexed array edges of size n, 
indicating that there is a directed edge from node i to node edges[i]. If there 
is no outgoing edge from node i, then edges[i] == -1.

Return the length of the longest cycle in the graph. If no cycle exists, return 
-1.

A cycle is a path that starts and ends at the same node.
*/

#include <iostream>
#include <vector>
#include <cassert>

using namespace std;

class Solution {
public:
    int visit(int n, int length, vector<int>& edges, vector<int>& counter) {
        // Dead end
        if (edges[n] == -1)
            return -1;

        // Cycle?
        if (counter[n] >= 0)
            return length - counter[n];

        // Visited, No cycle
        if (counter[n] == -1)
            return -1;

        // Set current node length
        counter[n] = length;

        // DFS
        int cycleLength = visit(edges[n], length+1, edges, counter);

        // Reset to visited
        counter[n] = -1;

        return cycleLength;
    }

    int longestCycle(vector<int>& edges) {
        // Visit / cycle count
        vector<int> counter(edges.size(), -2);

        // Iterate over all un-visited nodes
        int maxCycle = -1;
        for (int src = 0; src < edges.size(); src++) {
            // Skip visited
            if (counter[src] >= -1)
                continue;

            int cycle = visit(src, 0, edges, counter);

            maxCycle = max(maxCycle, cycle);
        }

        return maxCycle;
    }
};

int main() {
    Solution s;
    vector<int> e;
    int a;

    e = {3,3,4,2,3};
    a = s.longestCycle(e);
    cout << a << endl;
    assert(a == 3);

    e = {2,-1,3,1};
    a = s.longestCycle(e);
    cout << a << endl;
    assert(a == -1);

    e = {1,0};
    a = s.longestCycle(e);
    cout << a << endl;
    assert(a == 2);

    e = {0};
    a = s.longestCycle(e);
    cout << a << endl;
    assert(a == 1);

    e = {0,2,1};
    a = s.longestCycle(e);
    cout << a << endl;
    assert(a == 2);

}