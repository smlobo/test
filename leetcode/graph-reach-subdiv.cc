#include <iostream>
#include <vector>
#include <unordered_map>
#include <stack>
#include <cassert>

using namespace std;

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

        // Stack for nodes to visit
        stack<vector<int>> bfsStack;
        bfsStack.push({0,maxMoves});

        // Run BFS - calculating nodes reachable after subdivision
        int count = 0;
        while (!bfsStack.empty()) {
            vector<int> nDTuple = bfsStack.top();
            bfsStack.pop();

            int i = nDTuple[0];
            int dist = nDTuple[1];

            // Previously visited
            if (visited[i])
                continue;

            // Node visited
            count++;
            cout << "[" << i << "," << dist << "] " << count << endl;
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
                    cout << "  F [" << target.first << "] " << count << endl;

                    bfsStack.push({target.first, dist-(target.second+1)});

                    // Mark used nodes
                    adj[i][target.first] = 0;
                    adj[target.first][i] = 0;
                }
                // Not reachable
                else {
                    // Subset of subdivs visited
                    count += dist;
                    cout << "  P [" << target.first << "] " << count << endl;

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

    // vector<vector<int>> e = {{0,1,10},{0,2,1},{1,2,2}};
    // int mm = 6;
    // int n = 3;
    // int a = s.reachableNodes(e, mm, n);
    // cout << mm << "; " << n << " : " << a << endl;
    // assert(a == 13);

    // vector<vector<int>> e = {{0,1,4},{1,2,6},{0,2,8},{1,3,1}};
    // int mm = 10;
    // int n = 4;
    // int a = s.reachableNodes(e, mm, n);
    // cout << mm << "; " << n << " : " << a << endl;
    // assert(a == 23);

    // vector<vector<int>> e = {{1,2,4},{1,4,5},{1,3,1},{2,3,4},{3,4,5}};
    // int mm = 17;
    // int n = 5;
    // int a = s.reachableNodes(e, mm, n);
    // cout << mm << "; " << n << " : " << a << endl;
    // assert(a == 1);

    vector<vector<int>> e = {{0,2,3},{0,4,4},{2,3,8},{1,3,5},{0,3,9},{3,4,6},{0,1,5},{2,4,6},{1,2,3},{1,4,1}};
    int mm = 8;
    int n = 5;
    int a = s.reachableNodes(e, mm, n);
    cout << mm << "; " << n << " : " << a << endl;
    assert(a == 43);

}
