/*
You are given an undirected graph (the "original graph") with n nodes labeled 
from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of 
nodes, with the number of new nodes varying between each edge.

The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] 
indicates that there is an edge between nodes ui and vi in the original graph, 
and cnti is the total number of new nodes that you will subdivide the edge into. 
Note that cnti == 0 means you will not subdivide the edge.

To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti 
new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], 
[x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].

In this new graph, you want to know how many nodes are reachable from the node 
0, where a node is reachable if the distance is maxMoves or less.

Given the original graph and maxMoves, return the number of nodes that are 
reachable from node 0 in the new graph.
*/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>
#include <utility>
#include <cassert>

using namespace std;

class PQArrayComparator {
public:
    bool operator()(const pair<int,int>& a, const pair<int,int>& b) {
        return a.second < b.second;
    }
};

class Solution {    
public:
    int reachableNodes(vector<vector<int>>& edges, int maxMoves, int n) {
        // Build the graph
        vector<unordered_map<int,int>> adj(edges.size());
        for (vector<int> edge : edges) {
            int source = edge[0];
            int target = edge[1];
            int weight = edge[2];
            adj[source].insert({target, weight});
            adj[target].insert({source, weight});
        }

        // Mark for BFS
        vector<bool> visited(n, false);

        // PQ for nodes to visit
        priority_queue<pair<int,int>,vector<pair<int,int>>,PQArrayComparator> bfsQueue;
        bfsQueue.push({0,maxMoves});

        // Run BFS - calculating nodes reachable after subdivision
        int count = 0;
        while (!bfsQueue.empty()) {
            pair<int,int> nDPair = bfsQueue.top();
            bfsQueue.pop();

            int i = nDPair.first;
            int dist = nDPair.second;

            // Previously visited
            if (visited[i])
                continue;

            // Node visited
            count++;
            // cout << "[" << i << "," << dist << "] " << count << endl;
            visited[i] = true;

            // No point
            if (dist == 0)
                continue;

            // Iterate over all reachable nodes
            for (const auto& target : adj[i]) {
                // Target is reachable
                if (target.second < dist) {
                    // Subdivs visited
                    count += target.second;
                    // cout << "  F [" << target.first << "] " << count << endl;

                    bfsQueue.push({target.first, dist-(target.second+1)});

                    // Mark used nodes
                    adj[i][target.first] = 0;
                    adj[target.first][i] = 0;
                }
                // Not reachable
                else {
                    // Subset of subdivs visited
                    count += dist;
                    // cout << "  P [" << target.first << "] " << count << endl;

                    // Mark used nodes
                    int unused = target.second - dist;
                    adj[i][target.first] = unused;
                    adj[target.first][i] = unused;
                }
            }

        }

        return count;
    }
};

int main() {
    Solution s;
    vector<vector<int>> e;
    int mm, n, a;

    e = {{0,1,10},{0,2,1},{1,2,2}};
    mm = 6;
    n = 3;
    a = s.reachableNodes(e, mm, n);
    cout << mm << "; " << n << " : " << a << endl;
    assert(a == 13);

    e = {{0,1,4},{1,2,6},{0,2,8},{1,3,1}};
    mm = 10;
    n = 4;
    a = s.reachableNodes(e, mm, n);
    cout << mm << "; " << n << " : " << a << endl;
    assert(a == 23);

    e = {{1,2,4},{1,4,5},{1,3,1},{2,3,4},{3,4,5}};
    mm = 17;
    n = 5;
    a = s.reachableNodes(e, mm, n);
    cout << mm << "; " << n << " : " << a << endl;
    assert(a == 1);

    e = {{0,2,3},{0,4,4},{2,3,8},{1,3,5},{0,3,9},{3,4,6},{0,1,5},{2,4,6},{1,2,3},{1,4,1}};
    mm = 8;
    n = 5;
    a = s.reachableNodes(e, mm, n);
    cout << mm << "; " << n << " : " << a << endl;
    assert(a == 43);

}
