#include <iostream>
#include <vector>
#include <forward_list>
#include <string>

using namespace std;

class Graph {
private:
    int vertices;
    vector<forward_list<int>> adj;

public:
    Graph(int n, vector<vector<int>>& edges) {
        vertices = n;

        // Build the adjacency vector for each vertex
        adj = vector<forward_list<int>>(n, forward_list<int>());

        // Iterate over edges creating the adj vector
        for (int i = 0; i < edges.size(); i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            addEdge(v1, v2);
        }
    }

    void addEdge(int v1, int v2) {
        adj[v1].push_front(v2);
        adj[v2].push_front(v1);
    }

    int getVertices() {
        return vertices;
    }

    forward_list<int>& getAdjacents(int v) {
        return adj[v];
    }

    string to_string() {
        string retString = "{" + ::to_string(vertices) + ",";
        for (int i = 0; i < adj.size(); i++) {
            retString += "[" + ::to_string(i) + ":";
            for (int& n : adj[i])
                retString += ::to_string(n) + ",";
            retString += "]";
        }
        return retString + "}";
    }
};

class Solution {
private:
    vector<bool> marked;
    vector<int> id;
    int count;

public:
    long long countPairs(int n, vector<vector<int>>& edges) {
        // Build the graph
        Graph *g = new Graph(n, edges);
        cout << g->to_string() << endl;

        // Get connected components
        marked = vector<bool>(n, false);
        id = vector<int>(n, -1);
        count = 0;
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
        // Cleanup
        delete(g);

        cout << "# of CC groups: " << count << endl;

        // Count not connected vertices
        // long long counter = 0;
        // for (int i = 0; i < n; i++) {
        //     for (int j = i+1; j < n; j++) {
        //         if (!connected(i, j))
        //             counter++;
        //     }
        // }

        // # of vertices in each CC
        vector<int> cSize(count, 0);
        for (int i = 0; i < n; i++)
            cSize[id[i]]++;

        // Count unreachable pairs
        long long counter = 0;
        for (int i = 0; i < count; i++) {
            for (int j = i+1; j < count; j++) {
                counter += cSize[i] * cSize[j];
            }
            cout << i << " - " << cSize[i] << endl;
        }

        return counter;
    }

    void dfs(Graph *g, int src) {
        marked[src] = true;
        id[src] = count;

        for (int& dest : g->getAdjacents(src)) {
            if (!marked[dest])
                dfs(g, dest);
        }
    }

    bool connected(int v1, int v2) {
        return (id[v1] == id[v2]);
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
    int n = 3;
    vector<vector<int>> e = {
        {0, 1},
        {0, 2},
        {2, 1}
    };
    // printEdges(e);
    cout << n << " : " << s.countPairs(n, e) << endl;

    // test 2
    /*int*/ n = 7;
    /*vector<vector<int>>*/ e = {
        {0, 2}, {0, 5}, {2,4}, {1,6}, {5,4}
    };
    // printEdges(e);
    cout << n << " : " << s.countPairs(n, e) << endl;
}

// class Solution {
// public:
//     long long countPairs(int n, vector<vector<int>>& edges) {
//         // 2d vector of bools
//         vector<vector<bool>> connections(n, vector<bool> (n, false));

//         // iterate over edges marking them in the 2d matrix
//         for (int i = 0; i < edges.size(); i++) {
//             int r = edges[i][0];
//             int c = edges[i][1];
//             connections[r][c] = true;
//             connections[c][r] = true;
//         }

//         // mark derived connections
//         for (int i = 0; i < n; i++) {
//             for (int j = i+1; j < n; j++) {
//                 if (!connections[i][j])
//                     continue;

//                 for (int k = j+1; k < n; k++) {
//                     connections[i][k] = true;
//                 }
//             }
//         }
//         // count no connections
//         long long counter = 0;
//         for (int i = 0; i < n; i++) {
//             for (int j = i+1; j < n; j++) {
//                 if (!connections[i][j])
//                     counter++;
//             }
//         }

//         return counter;
//     }
// };
