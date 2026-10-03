#include <iostream>
#include <sstream>
#include <vector>

using namespace std;

int main() {
    string input = "41 3.14 0xff 1.2345e2 false hello world";
    istringstream stream(input);
    // stringstream stream(input);
 
    int n, m;
    double f, g;
    bool b;
 
    stream >> n >> f >> boolalpha >> hex >> m >> scientific >> g >> b;
    cout << "n = " << n << '\n' << "f = " << f << '\n'
        << hex << showbase << "m = " << m << '\n' 
        << fixed << "g = " << g << '\n'
        << "b = " << boolalpha << b << '\n';
 
    // extract the rest using the streambuf overload
    stream >> cout.rdbuf();
    stringstream s2(" foo bar");
    s2 >> cout.rdbuf();
    cout << '\n';

    cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    string str("Hello from\nthe\tdark-- ~~side");
    cout << "Original: " << str << endl;
    string tmp; // A string to store the word on each iteration.
    stringstream str_strm(str);
    vector<string> words; // Create vector to hold our words
    while (str_strm >> tmp) {
        // Provide proper checks here for tmp like if empty
        // Also strip down symbols like !, ., ?, etc.
        // Finally push it.
        words.push_back(tmp);
    }
    cout << "Parsed: ";
    for(int i = 0; i<words.size(); i++)
       cout << words[i] << "..:..";
    cout << endl;
}