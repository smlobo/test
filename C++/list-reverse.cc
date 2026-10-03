#include <iostream>
#include <forward_list>

using namespace std;

ostream& operator<<(ostream& strm, forward_list<int>& fll) {
    for (int i : fll)
        strm << i << ", ";
    return strm;
}

void reverse(forward_list<int>& fll, forward_list<int>& rll) {
    for (int i : fll) {
        rll.push_front(i);
    }
}

int main() {
    forward_list<int> fll = {0, 1, 2, 3};
    cout << fll << endl;
    // fll.reverse();
    // cout << fll << endl;
    // fll.reverse();
    forward_list<int> rll;
    reverse(fll, rll);
    cout << rll << endl;
}
