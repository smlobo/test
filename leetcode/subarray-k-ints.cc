/*
Given an integer array nums and an integer k, return the number of good subarrays 
of nums.

A good array is an array where the number of different integers in that array is 
exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.
*/

#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

class Solution {
public:
    int subarraysWithKDistinct(vector<int>& nums, int k) {
        // COunter of good sets
        int count = 0;

        // Create set for each index
        int l = nums.size() - k + 1;
        vector<unordered_set<int>> sets(nums.size());
        for (int i = 0; i < nums.size(); i++) {
            sets[i].insert(nums[i]);
        }

        // k == 1 case
        if (k == 1)
            count += nums.size();

        // Iterate from level 1 (2 index) to level nums.size() - 1
        for (int i = 1; i < nums.size(); i++) {
            // Merge sets to this level
            for (int j = 0; j < (sets.size() - i); j++) {
                sets[j].insert(nums[j+i]);

                // Count distinct if k or higher
                if (i >= (k-1)) {
                    if (sets[j].size() == k)
                        count++;
                }
            }
        }

        return count;
    }
};

string vector2String(vector<int>& v) {
    char buf[100];
    int i = 0;
    i += sprintf(buf, "{");
    for (int& x : v) {
        i += sprintf(buf+i, "%d,", x);
    }
    i += sprintf(buf+i, "}");
    return buf;
}

int main() {
    Solution s;

    vector<int> t = {1,2,1,2,3};
    int k = 2;
    cout << vector2String(t) << ", " << k << " : " << s.subarraysWithKDistinct(t, k)
        << endl;

    t = {1,2,1,3,4};
    k = 3;
    cout << vector2String(t) << ", " << k << " : " << s.subarraysWithKDistinct(t, k)
        << endl;

    t = {1,2};
    k = 1;
    cout << vector2String(t) << ", " << k << " : " << s.subarraysWithKDistinct(t, k)
        << endl;
}