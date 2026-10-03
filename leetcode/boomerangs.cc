/*
You are given n points in the plane that are all distinct, where points[i] = 
[xi, yi]. A boomerang is a tuple of points (i, j, k) such that the distance between 
i and j equals the distance between i and k (the order of the tuple matters).

Return the number of boomerangs.
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <cassert>

using namespace std;

class Solution {
public:
    int distance(vector<int>& a, vector<int>& b) {
        int xDelta = a[0] - b[0];
        int yDelta = a[1] - b[1];
        return xDelta*xDelta + yDelta*yDelta;
    }

    int nP2(int x) {
        assert(x > 1);
        return x * (x-1);
    }

    int numberOfBoomerangs(vector<vector<int>>& points) {
        vector<int> distSq(points.size());
        int counter = 0;

        // Iterate over points
        for (int i = 0; i < points.size(); i++) {
            vector<int> origin = points[i];

            // Iterate over all other points calculating distances
            for (int j = 0; j < points.size(); j++) {
                distSq[j] = distance(origin, points[j]);
                // cout << i << j << " ~ " << distSq[j] << endl;
            }

            // Sort
            sort(distSq.begin(), distSq.end());

            // Count # that are the same
            int same = 0;

            for (int j = 2; j < points.size(); j++) {
                // == , keep counting
                if (distSq[j] == distSq[j-1]) {
                    same++;
                }
                // != , and previous were equal
                else if (same != 0) {
                    counter += nP2(same+1);
                    same = 0;
                }
            }

            if (same != 0)
                counter += nP2(same+1);

            // cout << i << " : " << counter << endl;

        }

        return counter;
    }
};

void printVector(vector<vector<int>>& pts) {
    for (vector<int>& x : pts) {
        cout << "{";
        for (int& y : x)
            cout << y << ",";
        cout << "}";
    }
    cout << " = ";
}

int main() {
    Solution s;

    // test 1
    vector<vector<int>> pts = {{0,0},{1,0},{2,0}};
    // printVector(pts);
    // cout << s.numberOfBoomerangs(pts) << endl;

    // test 2
    pts = {{1,1},{2,2},{3,3}};
    // printVector(pts);
    // cout << s.numberOfBoomerangs(pts) << endl;

    // test 3
    pts = {{1,1}};
    // printVector(pts);
    // cout << s.numberOfBoomerangs(pts) << endl;

    // test 3
    pts = {{0,0},{1,0},{-1,0},{0,1},{0,-1}};
    // printVector(pts);
    // cout << s.numberOfBoomerangs(pts) << endl;
    
    // test 4
    pts = {{5,5},{4,7},{6,5},{6,9},{3,7},{4,5},{2,5},{4,4},{3,0}};
    printVector(pts);
    cout << s.numberOfBoomerangs(pts) << endl;
    
}
