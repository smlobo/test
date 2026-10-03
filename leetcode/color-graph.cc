/*
There is a directed graph of n colored nodes and m edges. The nodes are numbered 
from 0 to n - 1.

You are given a string colors where colors[i] is a lowercase English letter 
representing the color of the ith node in this graph (0-indexed). You are also 
given a 2D array edges where edges[j] = [aj, bj] indicates that there is a 
directed edge from node aj to node bj.

A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk 
such that there is a directed edge from xi to xi+1 for every 1 <= i < k. The 
color value of the path is the number of nodes that are colored the most 
frequently occurring color along that path.

Return the largest color value of any valid path in the given graph, or -1 if 
the graph contains a cycle.
*/

#include <iostream>
#include <vector>
#include <string>
#include <cassert>

using namespace std;

class Solution {
private:
    vector<vector<int>> adj;
    vector<bool> visited;
    vector<int> max;
    vector<int[26]> colorMax;
    string colors;

public:
    void visit(int i) {
        // Cycle - abort
        if (visited[i]) {
            max[i] = -1;
            return;
        }

        // Visited on some other path - do not recalculate
        if (max[i] != 0) {
            return;
        }

        // Mark
        visited[i] = true;

        // Track current node
        for (int j = 0; j < 26; j++)
            colorMax[i][j] = 0;

        // DFS first
        for (int target : adj[i]) {
            visit(target);

            // Cycle
            if (max[target] == -1) {
                max[i] = -1;
                return;
            }

            // merge color vector
            for (int j = 0; j < 26; j++) {
                if (colorMax[i][j] < colorMax[target][j])
                    colorMax[i][j] = colorMax[target][j];
            }
        }

        // Add current color
        int cIndex = colors[i] - 'a';
        colorMax[i][cIndex] += 1;

        // Calculate max
        for (int j = 0; j < 26; j++) {
            if (colorMax[i][j] > max[i])
                max[i] = colorMax[i][j];
        }

        // Unmark
        visited[i] = false;
    }

    int largestPathValue(string colors, vector<vector<int>>& edges) {
        // Build adj vector
        adj = vector<vector<int>>(colors.length());
        for (vector<int> edge : edges)
            adj[edge[0]].push_back(edge[1]);

        // Max vector holds max color of path originating at index
        max = vector<int>(adj.size(), 0);

        // Color max holds freq of each color for path originalting at index
        colorMax = vector<int[26]>(adj.size());

        // Ref to colors
        this->colors = colors;

        // node visited vector
        visited = vector<bool>(adj.size(), false);

        // Visit every node
        int pathMax = 0;
        for (int i = 0; i < adj.size(); i++) {
            visit(i);

            // Cycle - abort
            if (max[i] == -1) {
                pathMax = -1;
                break;
            }

            if (max[i] > pathMax)
                pathMax = max[i];
        }

        return pathMax;
    }
};

int main() {
    Solution s;
    string c;
    vector<vector<int>> e;
    int a;

    c = "abaca";
    e = {{0,1},{0,2},{2,3},{3,4}};
    a = s.largestPathValue(c, e);
    cout << c << " = " << a << endl;
    assert(a == 3);

    c = "a";
    e = {{0,0}};
    a = s.largestPathValue(c, e);
    cout << c << " = " << a << endl;
    assert(a == -1);

    c = "rbrgbg";
    e = {{0,1},{0,2},{1,3},{1,4},{2,4},{4,5}};
    a = s.largestPathValue(c, e);
    cout << c << " = " << a << endl;
    assert(a == 2);

    c = "rbbgbg";
    e = {{0,1},{0,2},{1,3},{1,4},{2,4},{4,5}};
    a = s.largestPathValue(c, e);
    cout << c << " = " << a << endl;
    assert(a == 2);

    c = "rbrgbb";
    e = {{0,1},{0,2},{1,3},{1,4},{2,4},{4,5}};
    a = s.largestPathValue(c, e);
    cout << c << " = " << a << endl;
    assert(a == 3);

}
