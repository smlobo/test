#include <iostream>
#include <format>

using namespace std;

void printArray(int x[10]) {
    for (int i = 0; i < 10; i++)
        cout << x[i] << ", ";
    cout << endl;
    cout << "2[x] == " << 2[x] << "\n";
}

void printStrArray(string* x) {
    for (int i = 0; i <5; i++)
        cout << x[i] << ", ";
    cout << endl;
}

void printFloatArray(array<float,4>& x) {
    for (int i = 0; i < x.size(); i++)
        cout << x[i] << ", ";
    cout << endl;
}

int main() {
    const int size = 10;
    int intArray[size];
    for (int i = 0; i < size; i++)
        intArray[i] = i;
    printArray(intArray);

    string* dynStrArray = new string[5];
    for (int i = 0; i < 5; i++)
        dynStrArray[i] = format("~~{}~~", i);
    printStrArray(dynStrArray);
    delete[] dynStrArray;

    array<float,4> floatArray;
    for (int i = 0; i < floatArray.size(); i++) {
        floatArray[i] = ((float) i)/10 + (float) i;
    }
    printFloatArray(floatArray);
}