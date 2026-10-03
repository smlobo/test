/*
Given a data stream input of non-negative integers a1, a2, ..., an, summarize 
the numbers seen so far as a list of disjoint intervals.

Implement the SummaryRanges class:

SummaryRanges() Initializes the object with an empty stream.
void addNum(int value) Adds the integer value to the stream.
int[][] getIntervals() Returns a summary of the integers in the stream currently 
as a list of disjoint intervals [starti, endi]. The answer should be sorted by 
starti.
*/

#include <iostream>
#include <vector>
#include <set>
#include <string>
#include <utility>
#include <cassert>

using namespace std;

class SummaryRanges {
private:
    set<pair<int,int>> intervalTree;

public:
    SummaryRanges() {
        intervalTree.clear();
    }
    
    void addNum(int value) {
        // This interval
        pair<int,int> interval = {value, value};

        auto lb = intervalTree.lower_bound(interval);
        auto ub = intervalTree.upper_bound(interval);

        // Adjust lb to the previous interval if necessary
        if (lb != intervalTree.end() && (*lb).first != value) {
            if (lb == intervalTree.begin())
                lb = intervalTree.end();
            else
                lb--;
        }
        // No lb & not empty - point to the last one
        else if (lb == intervalTree.end() && !intervalTree.empty()) {
            lb = intervalTree.end();
            lb--;
        }

        // Upper and lower
        if (lb != intervalTree.end() && ub != intervalTree.end()) {
            // Joins ln & ub
            if (((*lb).second + 1) == value && ((*ub).first - 1) == value) {
                interval.first = (*lb).first;
                interval.second = (*ub).second;
                // intervalTree.extract(lb);
                // intervalTree.extract(ub);
                intervalTree.erase(lb);
                intervalTree.erase(ub);
                intervalTree.insert(interval);
            }
            // Adds to lb
            else if (((*lb).second + 1) == value) {
                interval.first = (*lb).first;
                // intervalTree.extract(lb);
                intervalTree.erase(lb);
                intervalTree.insert(interval);
            }
            // Adds to ub
            else if (((*ub).first - 1) == value) {
                interval.second = (*ub).second;
                // intervalTree.extract(ub);
                intervalTree.erase(ub);
                intervalTree.insert(interval);
            }
            // New interval
            else if ((*lb).second < value) {
                intervalTree.insert(interval);
            }
        }
        // Only <= found
        else if (lb != intervalTree.end()) {
            // Adds to interval
            if (((*lb).second + 1) == value) {
                interval.first = (*lb).first;
                // intervalTree.extract(lb);
                intervalTree.erase(lb);
                intervalTree.insert(interval);
            }
            else if ((*lb).second < value) {
                intervalTree.insert(interval);
            }
        }
        // Only > found
        else if (ub != intervalTree.end()) {
            // Adds to interval
            if (((*ub).first - 1) == value) {
                interval.second = (*ub).second;
                // intervalTree.extract(ub);
                intervalTree.erase(ub);
                intervalTree.insert(interval);
            }
            else {
                intervalTree.insert(interval);
            }
        }
        // First element!
        else {
            intervalTree.insert(interval);
        }
    }
    
    vector<vector<int>> getIntervals() {
        vector<vector<int>> retVec;
        retVec.reserve(intervalTree.size());

        for (const pair<int,int>& interval : intervalTree)
            retVec.push_back({interval.first, interval.second});

        return retVec;
    }
};

ostream& operator<<(ostream& strm, vector<vector<int>>& its) {
    for (const vector<int>& it : its)
        strm << "{" << it[0] << "," << it[1] << "}, ";
    return strm;
}

void execute(vector<string>& ops, vector<vector<int>> vals, SummaryRanges& sr) {
    assert(ops.size() == vals.size());

    for (int i = 0; i < ops.size(); i++) {
        if (ops[i] == "SummaryRanges")
            sr = SummaryRanges();
        else if (ops[i] == "addNum")
            sr.addNum(vals[i][0]);
        else if (ops[i] == "getIntervals") {
            vector<vector<int>> intervals = sr.getIntervals();
            cout << intervals << endl;
        }
    }
}

int main() {
    SummaryRanges sr;
    vector<string> ops;
    vector<vector<int>> vals;

    cout << "--- Test 1 ---" << endl;
    ops = {"SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", 
        "addNum", "getIntervals", "addNum", "getIntervals", "addNum", 
        "getIntervals"};
    vals = {{}, {1}, {}, {3}, {}, {7}, {}, {2}, {}, {6}, {}};
    execute(ops, vals, sr);

    cout << "--- Test 2 ---" << endl;
    ops = {"SummaryRanges","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals","addNum","getIntervals"};
    vals = {{},{6},{},{6},{},{0},{},{4},{},{8},{},{7},{},{6},{},{4},{},{7},{},{5},{}};
    execute(ops, vals, sr);

    cout << "--- Test 3 ---" << endl;
    ops = {"SummaryRanges","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals","addNum","getIntervals","addNum","getIntervals",
        "addNum","getIntervals"};
    vals = {{},{1},{},{3},{},{7},{},{2},{},{6},{},{9},{},{4},{},{10},{},{5},{}};
    execute(ops, vals, sr);

}
