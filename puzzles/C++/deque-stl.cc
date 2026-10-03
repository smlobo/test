/*
Double ended queue or Deque(part of C++ STL) are sequence containers with 
dynamic sizes that can be expanded or contracted on both ends (either its front 
or its back).

Given a set of arrays of size N and an integer K, you have to find the maximum 
integer for each and every contiguous subarray of size K for each of the given 
arrays.

Input Format

First line of input will contain the number of test cases T. For each test case, 
you will be given the size of array N and the size of subarray to be used K. 
This will be followed by the elements of the array Ai.

Constraints 
1 <= T <= 1000
1 <= N <= 10000 
1 <= K <= N
1 <= Ai <= 10000, where Ai is the ith element in the array A

Output Format

For each of the contiguous subarrays of size K of each array, you have to print 
the maximum integer.

Sample Input

2
5 2
3 4 6 3 4
7 4
3 4 5 8 1 4 10

Sample Output

4 6 6 4
8 8 8 10

Explanation

For the first case, the contiguous subarrays of size 2 are {3,4},{4,6},{6,3} and 
{3,4}. The 4 maximum elements of subarray of size 2 are: 4 6 6 4. 

For the second case,the contiguous subarrays of size 4 are {3,4,5,8},{4,5,8,1},
{5,8,1,4} and {8,1,4,10}. The 4 maximum element of subarray of size 4 are: 
8 8 8 10.
*/

#include <iostream>
#include <deque>
#include <algorithm>

using namespace std;

void printDeque(deque<int> &x) {
    cout << endl << "DQ: ";
    for (deque<int>::iterator it = x.begin(); it != x.end(); it++) {
        cout << *it << ", ";
    }
    cout << endl;
}

// Global pointer to the array - for the sort comparison function
int *garr;

// Sort comparison function:
// * higher is first
// * if equal, higher index earlier
bool compareDQ(int p, int q) {
    if (garr[p] < garr[q])
        return false;
    else if (garr[p] > garr[q])
        return true;
    else
        return (p > q);
}

void printKMax(int arr[], int n, int k) {
    // Write your code here.

    // Holds max at first element. Sorted when max goes out of range
    deque<int> lazySortIndices;

    // Point global array to the input array
    garr = arr;

    // Fill up the deque
    lazySortIndices.push_back(0);
    for (int i = 1; i < k; i++) {
        if (arr[i] >= arr[lazySortIndices.front()])
            lazySortIndices.push_front(i);
        else
            lazySortIndices.push_back(i);
    }
    //printDeque(lazySortIndices);

    // The first elemet is the max - print it.
    // Then find the new max.
    for (int i = k; i < n; i++) {
        cout << arr[lazySortIndices.front()] << " ";

        // New max - nothing more to do!
        if (arr[lazySortIndices.front()] <= arr[i]) {
            lazySortIndices.push_front(i);
        }

        else {
            // Add current index to the end
            lazySortIndices.push_back(i);

            // Current max out of range
            if (lazySortIndices.front() <= (i-k)) {
                // Remove the front
                lazySortIndices.pop_front();

                // Remove other out-of-range indices
                while (lazySortIndices.front() <= (i-k))
                    lazySortIndices.pop_front();

                // Sort the dequeue
                sort(lazySortIndices.begin(), lazySortIndices.end(), compareDQ);

                // Remove out-of-range max
                while (lazySortIndices.front() <= (i-k))
                    lazySortIndices.pop_front();
            }
        }
        //printDeque(lazySortIndices);
    }

    // Print the last max
    cout << arr[lazySortIndices.front()] << endl;
}

int main() {
	int t;
	cin >> t;

	while (t > 0) {
		int n,k;
    	cin >> n >> k;
    	int i;
    	int arr[n];
    	for (i = 0; i < n; i++)
      		cin >> arr[i];
    	printKMax(arr, n, k);
    	t--;
  	}
  	return 0;
}

