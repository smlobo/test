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

int *garr;

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
    deque<int> sortedIndex;

    garr = arr;

    // Fill up the deque
    sortedIndex.push_back(0);
    for (int i = 1; i < k; i++) {
        if (arr[i] >= arr[sortedIndex.front()])
            sortedIndex.push_front(i);
        else
            sortedIndex.push_back(i);
    }
    //printDeque(sortedIndex);

    // Print and insert the next index in the correct location
    for (int i = k; i < n; i++) {
        //cout << "i = " << i << ", arr val: " << arr[i] << endl;
        cout << arr[sortedIndex.front()] << " ";

        // Current max out of range
        if (sortedIndex.front() <= (i-k)) {
            // Remove the front
            sortedIndex.pop_front();

            // Add the new element
            sortedIndex.push_front(i);

            // Sort the dequeue
            sort(sortedIndex.begin(), sortedIndex.end(), compareDQ);

            // Remove the max until the first in range
            while (sortedIndex.front() <= (i-k))
                sortedIndex.pop_front();
        }

        // Compare against current max
        else {
            if (arr[sortedIndex.front()] < arr[i])
                sortedIndex.push_front(i);
            else
                sortedIndex.push_back(i);
        }
        //printDeque(sortedIndex);
    }

    // Print the last max
    cout << arr[sortedIndex.front()] << endl;
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

