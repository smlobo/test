#include <iostream>
#include <sstream>

using namespace std;

int main() {
    string x = "some string";
    istringstream xStream(x);

    cout << "Original: x: " << x << endl;
    string x0, x1;
    xStream >> x0 >> x1;

    cout << "parsed: x0: " << x0 << "; x1: " << x1 << endl;
    cout << "Replay original: x: " << x << endl;

    string y = "other ascii";
    istringstream yStream(std::move(y));

    cout << "Original: y: " << y << endl;
    string y0, y1;
    yStream >> y0 >> y1;

    cout << "parsed: y0: " << y0 << "; y1: " << y1 << endl;
    cout << "Replay original: y: " << y << endl;

}