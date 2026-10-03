#include <iostream>
#include <string>
#include <format>

using namespace std;

void capitalize(string& x) {
    for (int i = 0; i < x.length(); i++) {
        char c = x[i];
        if (c >= 'a' && c <= 'z') {
            x[i] = c - ('a' - 'A');
        }
    }
}

void reverse(string& x) {
    for (int i = 0; i < x.length()/2; i++) {
        swap(x[i], x[x.length()-1-i]);
    }
}

int main() {
    string a = "12345";
    cout << &a << " : " << a << endl;

    string b(3, 'a');
    cout << b << endl;

    int n = a.find("34");
    a.replace(n, 1, "abcd");
    cout << &a << " : " << a << endl;

    string c("a");
    char x = c.at(0);
    c.reserve(5);
    for (int i = 0; i < 5; i++) {
        x += 1;
        c.push_back(x);
        cout << c << endl;
    }

    capitalize(c);
    cout << c << endl;
    capitalize(a);
    cout << a << endl;

    reverse(c);
    cout << c << endl;

    string s = format("{:#06x} + {:10f}", 10, 20.2);
    cout << s << endl;

    // Copy
    string d = a;
    cout << "d - Copy of a: " << &d << " : " << d << endl;
    cout << " : " << &d[1] << endl;
    d.replace(3, 2, "XX");
    d.replace(1, 1, string(1, '@'));
    cout << "d - After replace: " << &d << " : " << d << endl;
    cout << " : " << &d[1] << endl;
    cout << "a - for sanity: " << &a << " : " << a << endl;

    string* e = new string("xyz");
    cout << e << " : " << *e << endl;
    delete e;

    return 0;
}

