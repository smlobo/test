#include <iostream>
#include <iomanip>

using namespace std;

int main() {
    int x = 0xa;
    cout << "(Decimal): " << x << endl;
    cout << "Hex: " << hex << x << endl;
    cout << "Showbase: " << showbase << x << endl;
    cout << "Oct (Showbase): " << oct << x << endl;

    float y = 3.1415921782563;
    cout << "Float: " << y << endl;
    cout << "Fixed fill b: " << fixed << setw(10) << setprecision(2) << y << endl;
    cout << "Fixed fill 0: " << fixed << setw(10) << setprecision(2) << 
        setfill('0') << y << endl;
    cout << "Scientific: " << scientific << y << endl;
}