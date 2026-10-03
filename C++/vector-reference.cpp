#include <iostream>
#include <vector>

using namespace std;

vector<int> ints1 = {0, 1, 2};
vector<int> ints2 = {4, 5, 6};

void foo(int a) {
    vector<int> &intsref = ints1;
    cout << "  ref should be 1: ";
    for (auto &p : intsref) {
        cout << p << ", ";
    }
    cout << "\n";

    if (a > 0) {
        intsref = ints2;
    }
    cout << "  after condition: ";
    for (auto &p : intsref) {
        cout << p << ", ";
    }
    cout << "\n";
}

int main(int argc, char* argv[]) {
    // int x = stoi(argv[1]);
    cout << "foo() called\n";
    foo(1);
    cout << "foo() again\n";
    foo(-1);

    return 0;
}
