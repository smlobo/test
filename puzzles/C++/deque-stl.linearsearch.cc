#include <iostream>
#include <deque>

using namespace std;

void printDeque(deque<int> &x) {
    cout << endl << "DQ: ";
    for (deque<int>::iterator it = x.begin(); it != x.end(); it++) {
        cout << *it << ", ";
    }
    cout << endl;
}
void printKMax(int arr[], int n, int k) {
    // Write your code here.
    deque<int> sortedIndex;

    // Fill up the deque
    sortedIndex.push_back(0);
    for (int i = 1; i < k; i++) {
        if (arr[i] >= arr[sortedIndex.front()])
            sortedIndex.push_front(i);
        else if (arr[i] < arr[sortedIndex.back()])
            sortedIndex.push_back(i);
        else {
            for (deque<int>::iterator it = sortedIndex.begin(); 
                it != sortedIndex.end(); it++) {
                if (arr[i] >= arr[*it]) {
                    sortedIndex.insert(it, i);
                    break;
                }
            }
        }
    }
    //printDeque(sortedIndex);

    // Print and insert the next index in the correct location
    for (int i = k; i < n; i++) {
        //cout << "i = " << i << ", arr val: " << arr[i] << endl;
        cout << arr[sortedIndex.front()] << " ";

        for (deque<int>::iterator it = sortedIndex.begin(); 
            it != sortedIndex.end();) {
            //cout << endl << "Insert in DQ: " << *it << endl;
            // Insert in sorted order and break
            if (arr[i] >= arr[*it]) {
                sortedIndex.insert(it, i);
                break;
            }

            // Remove out of scope indexes
            if (*it <= (i-k)) {
                //cout << "Erasing: " << *it << endl;
                it = sortedIndex.erase(it);
                //it--;
            }
            else {
                it++;
            }
        }

        // Corner case - removed last index and did not add the new
        if (sortedIndex.size() < n)
            sortedIndex.push_back(i);

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

