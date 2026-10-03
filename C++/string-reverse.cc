#include <iostream>
#include <string>

using namespace std;

string reverse(const string& x) {
    string r = x;

    for (int i = 0; i < r.length()/2; i++) {
        r[i] = x[r.length()-i-1];
        r[r.length()-i-1] = x[i];
    }

    return r;
}

int main() {
    string s = "IAmFoo";
    cout << s << " <-> " << reverse(s) << endl;
}
