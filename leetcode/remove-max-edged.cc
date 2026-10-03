/*
Alice and Bob have an undirected graph of n nodes and three types of edges:

Type 1: Can be traversed by Alice only.
Type 2: Can be traversed by Bob only.
Type 3: Can be traversed by both Alice and Bob.
Given an array edges where edges{i} = {typei, ui, vi} represents a bidirectional 
edge of type typei between nodes ui and vi, find the maximum number of edges you 
can remove so that after removing the edges, the graph can still be fully traversed 
by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting 
from any node, they can reach all other nodes.

Return the maximum number of edges you can remove, or return -1 if Alice and Bob 
cannot fully traverse the graph.
*/


#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    int e2i(int e) {
        return e-1;
    }
    int i2e(int i) {
        return i+1;
    }

    vector<int> edgeTo;
    int edgeCount;
    int root(int i) {
        while (i != edgeTo[i]) {
            // Tree shortening
            edgeTo[i] = edgeTo[edgeTo[i]];
            i = edgeTo[i];
        }
        return i;
    }
    bool connected(int i, int j) {
        return root(i) == root(j);
    }
    void connect(int i, int j) {
        // TODO: balance, path shorten
        edgeTo[root(i)] = root(j);
        edgeCount++;
    }

    void connectEdgesOfType(vector<vector<int>>& edges, int type, int& count) {
        for (vector<int>& edge : edges) {
            if (edge[0] != type)
                continue;

            if (connected(e2i(edge[1]), e2i(edge[2]))) {
                count++;
                continue;
            }

            connect(e2i(edge[1]), e2i(edge[2]));
        }        
    }

    bool notFullyConnected() {
        // for (int i = 1; i < edgeTo.size(); i++) {
        //     if (!connected(0, i))
        //         return true;
        // }
        // return false;
        return (edgeCount < (edgeTo.size()-1));
    }

    int maxNumEdgesToRemove(int n, vector<vector<int>>& edges) {
        // Initialize edgeTo
        edgeTo = vector<int>(n);
        edgeCount = 0;
        for (int i = 0; i < n; i++)
            edgeTo[i] = i;

        // Delete counter
        int count = 0;

        // Iterate over T3 edges connecting or deleting them
        connectEdgesOfType(edges, 3, count);

        // Cache edgeTo
        vector<int> cacheEdgeTo = vector<int>(n);
        int cacheEdgeCount = edgeCount;
        for (int i = 0; i < n; i++)
            cacheEdgeTo[i] = edgeTo[i];

        // Iterate over T1 edges connecting or deleting them
        connectEdgesOfType(edges, 1, count);

        // T1 + T3 edges not connected
        if (notFullyConnected())
            return -1;

        // De-cache
        edgeCount = cacheEdgeCount;
        for (int i = 0; i < n; i++)
            edgeTo[i] = cacheEdgeTo[i];

        // Iterate over T2 edges connecting or deleting them
        connectEdgesOfType(edges, 2, count);

        // T2 + T3 edges not connected
        if (notFullyConnected())
            return -1;

        return count;
    }
};

string vector2String(vector<vector<int>>& e) {
    char buf[100];
    int i = 0;
    i += sprintf(buf+i, "{");
    for (vector<int>& x : e) {
        i += sprintf(buf+i, "{");
        for (int& y : x)
            i += sprintf(buf+i, "%d,", y);
        i += sprintf(buf+i, "},");
    }
    i += sprintf(buf+i, "}");
    return buf;
}

int main() {
    Solution s;

    vector<vector<int>> t = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
    int n = 4;
    cout << vector2String(t) << " : " << s.maxNumEdgesToRemove(n, t) << endl;

    t = {{3,1,2},{3,2,3},{1,1,4},{2,1,4}};
    n = 4;
    cout << vector2String(t) << " : " << s.maxNumEdgesToRemove(n, t) << endl;

    t = {{3,2,3},{1,1,2},{2,3,4}};
    n = 4;
    cout << vector2String(t) << " : " << s.maxNumEdgesToRemove(n, t) << endl;
}
