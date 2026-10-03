#include <iostream>
#include <list>
#include <cstdlib>

using namespace std;

void printList(list<string> &l) {
    for (auto it = l.begin(); it != l.end(); it++)
    // for (string s : l)
        cout << *it << ", ";
    cout << endl;
}

int main(int argc, char** argv) {

    list<string> strList = {"aa", "bb", "cc"};

    list<string> b2List = {"b2x", "b2y", "b2z"};

    cout << "List initialized:\n";
    printList(strList);

    // Insert while iterating
    // for (list<string>::iterator it = strList.begin(); it != strList.end(); it++) {
    for (list<string>::iterator it = strList.begin(); it != strList.end();) {
        string s = *it;
        cout << "-> " << s << endl;
        if (s.compare("aa") == 0) {
            cout << "Erase " << s << endl;
            it = strList.erase(it);
            it = strList.insert(it, "a1");
        }
        else if (s.compare("bb") == 0) {
            it = strList.erase(it);
            it = strList.insert(it, "b3");
            it = strList.insert(it, "b1");
            it = strList.insert(it, "b2");
            cout << "Erase " << s << endl;
        }
        else if (s.compare("b2") == 0) {
            it = strList.erase(it);
            it = strList.insert(it, b2List.begin(), b2List.end());
            cout << "Erase " << s << endl;
        }
        else if (s.compare("cc") == 0) {
            it = strList.erase(it);
            it = strList.insert(it, "c3p0");
            cout << "Erase " << s << endl;
        }
        else {
            it++;
        }
    }

    cout << "List after:\n";
    printList(strList);

}
