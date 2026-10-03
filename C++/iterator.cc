#include <iostream>

using namespace std;

bool isOdd(int& x) {
    return x%2;
}

int main() {
    for (int i : {-2, -1, 0, 1, 2, 3, 4, 5})
        cout << i << " odd? : " << isOdd(i) << endl;
}