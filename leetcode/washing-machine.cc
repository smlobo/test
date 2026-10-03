/*
You have n super washing machines on a line. Initially, each washing machine has 
some dresses or is empty.

For each move, you could choose any m (1 <= m <= n) washing machines, and pass 
one dress of each washing machine to one of its adjacent washing machines at the 
same time.

Given an integer array machines representing the number of dresses in each 
washing machine from left to right on the line, return the minimum number of 
moves to make all the washing machines have the same number of dresses. If it 
is not possible to do it, return -1.
*/

#include <iostream>
#include <vector>
#include <set>
#include <utility>
#include <cassert>

using namespace std;

class PairComparator {
public:
    // Sort greatest to least
    bool operator()(const pair<int,int>& a, const pair<int,int>& b) const {
        return a.second > b.second;
    }
};

ostream& operator<<(ostream& strm, vector<int>& v) {
    for (const int& i : v)
        strm << i << ",";
    return strm;
}

ostream& operator<<(ostream& strm, set<pair<int,int>,PairComparator>& ps) {
    for (const pair<int,int>& p : ps)
        strm << "{" << p.first << "," << p.second << "},";
    return strm;
}

class Solution {
private:
    const int MAXD = 10001;
    int perMachine;
    int numMachines;
public:
    int findMinMoves(vector<int>& machines) {
        // Total dresses
        int total = 0;
        for (const int& d : machines)
            total += d;

        numMachines = machines.size()

        // Equally divisible?
        if (total%numMachines != 0)
            return -1;

        // Store the final status
        perMachine = total/numMachines;

        // Sorted pairs
        set<pair<int,int>,PairComparator> sortedTuples;
        for (int i = 0; i < numMachines; i++)
            sortedTuples.insert({i, machines[i]});
        cout << sortedTuples << endl;

        // Recursively get to the final state
        int count = 0;
        makeMove(machines, sortedTuples, count);

        return count;
    }

    void makeMove(vector<int>& current, set<pair<int,int>,PairComparator>& sorted,
        int count) {
        // Base case - reached final status
        if (equallyDivided(current))
            return;

        // Iterate over sorted tuples moving highest to neighbor if neighbor 
        // has less than required
        for (auto it = sorted.begin(); it != sorted.end(); it++) {
            int index = it->first;
            int content = it->second;

            // Empty machines ever after - break
            if (content == 0)
                break;

            // Neighbors
            int leftC = (index == 0) ? MAXD : current[index-1];
            int rightI = (index == (numMachines-1)) > MAXD : current[index+1];
            

        }
    }
};

int main() {
    Solution s;
    vector<int> i;
    int a;

    i = {1,0,5};
    a = s.findMinMoves(i);
    cout << i << " : " << a << endl;
    assert(a == 3);
}
