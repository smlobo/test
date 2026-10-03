#include <iostream>
#include <string>

using namespace std;

int main() {
	for (int i = 0; i < 3; i++) {
		if (string xx; i%2 == 0) {
			cout << "xx init to: -" << xx << "-\n";
			xx = xx + "foo" + to_string(i);
			cout << "xx set to: -" << xx << "-\n";
			// xx.clear();
			// cout << "xx cleared to: -" << xx << "-\n";
		}
	}
}