#include <iostream>
#include <forward_list>

using namespace std;

int main() {
    forward_list<int> sList;
    for (int i = 0; i < 10; i += 2)
        sList.push_front(i);

    cout << "List: ";
    for (int& n : sList)
        cout << n << ",";
    cout << endl;
}