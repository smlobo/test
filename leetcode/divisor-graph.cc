/*
We have n cities labeled from 1 to n. Two different cities with labels x and y 
are directly connected by a bidirectional road if and only if x and y share a 
common divisor strictly greater than some threshold. More formally, cities with 
labels x and y have a road between them if there exists an integer z such that 
all of the following are true:

x % z == 0,
y % z == 0, and
z > threshold.
Given the two integers, n and threshold, and an array of queries, you must 
determine for each queries[i] = [ai, bi] if cities ai and bi are connected 
directly or indirectly. (i.e. there is some path between them).

Return an array answer, where answer.length == queries.length and answer[i] is 
true if for the ith query, there is a path between ai and bi, or answer[i] is 
false if there is no path.
*/

#include <iostream>
#include <vector>

using namespace std;

ostream& operator<<(ostream& ostrm, vector<vector<int>>& x) {
    for (const vector<int>& y : x) {
        ostrm << "{" << y[0] << "," << y[1] << "},";
    }
    return ostrm;
}

ostream& operator<<(ostream& ostrm, vector<bool>& x) {
    ostrm << boolalpha;
    for (const bool& y : x) {
        ostrm << y << ",";
    }
    return ostrm;
}

ostream& operator<<(ostream& ostrm, vector<unordered_map<int,bool>>& x) {
    for (int i = 0; i < x.size(); i++) {
        ostrm << i << " : ";
        for (const auto& y : x[i]) {
            ostrm << y.first << ",";
        }
        ostrm << endl;
    }
    return ostrm;
}

class Solution {
public:
    vector<int> uf;
    vector<int> sz;

    int root(int c) {
        while (uf[c] != c) {
            uf[c] = uf[uf[c]];
            c = uf[c];
        }
        return c;
    }

    bool connected(int p, int q) {
        return root(p) == root(q);
    }

    void connect(int p, int q) {
        // cout << "C: " << p << " ~ " << q << endl;
        // uf[root(p)] = root(q);
        int rp = root(p);
        int rq = root(q);
        if (rp == rq)
            return;
        if (sz[rp] < sz[rq]) {
            uf[rp] = rq;
            sz[rq] += sz[rp];
        } else {
            uf[rq] = rp;
            sz[rp] += sz[rq];
        }
    }

    vector<bool> areConnected(int n, int threshold, vector<vector<int>>& queries) {
        // Initialize Union Find
        uf = vector<int>(n+1);
        for (int i = 1; i <= n; i++)
            uf[i] = i;
        sz = vector<int>(n+1, 1);

        // Opt - handle threshold = 0
        if (threshold == 0) {
            vector<bool> ans(queries.size(), true);
            return ans;
        }

        // Get divisors
        vector<unordered_map<int,bool>> divisors(n+1);
        for (int i = threshold+1; i <= n; i++) {
            // divisors[i][1] = true; // Opt above - do not bother
            divisors[i][i] = true;
            for (int j = threshold+1; j <= i/2; j++) {
                if (i%j == 0)
                    divisors[i][j] = true;
            }
        }
        // cout << divisors;

        // Connect
        for (int i = threshold+1; i <= n; i++) {
            // Divisors
            for (const auto& iDivEle : divisors[i]) {
                if (!iDivEle.second)
                    continue;
                int iDiv = iDivEle.first;

                // if (iDiv <= threshold)
                //     continue;
                // cout << "D: " << i << " ! " << iDiv << " ? " << iDivEle.second << endl;

                // Iterate over remaining cells checking for common divisor
                for (int j = i+1; j <= n; j++) {
                    if (divisors[j][iDiv])
                        connect(i, j);
                }
            }
        }

        // Answer queries
        vector<bool> ans(queries.size());
        for (int i = 0; i < queries.size(); i++) {
            if (connected(queries[i][0], queries[i][1]))
                ans[i] = true;
        }
        return ans;
    }
};

int main() {
    Solution s;

    int n = 6;
    int threshold = 2;
    vector<vector<int>> queries = {{1,4},{2,5},{3,6}};
    cout << n << "," << threshold << "+" << queries << endl;
    vector<bool> ans = s.areConnected(n, threshold, queries);
    cout << ans << endl;

    // int n = 6;
    // int threshold = 0;
    // vector<vector<int>> queries = {{4,5},{3,4},{3,2},{2,6},{1,3}};
    // cout << n << "," << threshold << "+" << queries << endl;
    // vector<bool> ans = s.areConnected(n, threshold, queries);
    // cout << ans << endl;

    // int n = 5;
    // int threshold = 1;
    // vector<vector<int>> queries = {{4,5},{4,5},{3,2},{2,3},{3,4}};
    // cout << n << "," << threshold << "+" << queries << endl;
    // vector<bool> ans = s.areConnected(n, threshold, queries);
    // cout << ans << endl;
}
