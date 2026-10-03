#include <iostream>
#include <vector>
#include <string>

using namespace std;

/*
You want to water n plants in your garden with a watering can. The plants are 
arranged in a row and are labeled from 0 to n - 1 from left to right where the 
ith plant is located at x = i. There is a river at x = -1 that you can refill 
your watering can at.

Each plant needs a specific amount of water. You will water the plants in the 
following way:

* Water the plants in order from left to right.
* After watering the current plant, if you do not have enough water to 
  completely water the next plant, return to the river to fully refill the 
  watering can.
* You cannot refill the watering can early.

You are initially at the river (i.e., x = -1). It takes one step to move one 
unit on the x-axis.

Given a 0-indexed integer array plants of n integers, where plants[i] is the 
amount of water the ith plant needs, and an integer capacity representing the 
watering can capacity, return the number of steps needed to water all the plants.
*/

class Solution {
public:
    int wateringPlants(vector<int>& plants, int capacity) {
        int currentCapacity = capacity;
        int steps = 0;

        // Iterate over the array
        for (int i = 0; i < plants.size(); i++) {
            if (currentCapacity < plants[i]) {
                currentCapacity = capacity - plants[i];
                steps += i * 2 + 1;
            }
            else {
                currentCapacity -= plants[i];
                steps++;
            }
        }

        return steps;
    }
};

// Print out the vector
string vector_string(vector<int>& x) {
    string retStr = "{ ";
    retStr.reserve(100);
    for (int n : x)
        retStr += to_string(n) + ", ";
    retStr += "};";
    return retStr;
}

int main() {
    Solution *s = new Solution();

    // test 1
    // vector<int> plants = {2, 2, 3, 3};
    // int capacity = 5;
    // cout << vector_string(plants) << " : " << capacity << " = " << 
    //     s->wateringPlants(plants, capacity) << endl;

    // test 2
    // vector<int> plants = {1,1,1,4,2,3};
    // int capacity = 4;
    // cout << vector_string(plants) << " : " << capacity << " = " << 
    //     s->wateringPlants(plants, capacity) << endl;

    // test 2
    vector<int> plants = {7,7,7,7,7,7,7};
    int capacity = 8;
    cout << vector_string(plants) << " : " << capacity << " = " << 
        s->wateringPlants(plants, capacity) << endl;
}
