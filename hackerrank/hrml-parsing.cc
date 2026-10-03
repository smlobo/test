#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <list>
#include <sstream>

using namespace std;

string getFullTag(list<string>& tS) {
    string fT = "";
    bool first = true;
    for (const string& s : tS) {
        if (!first)
            fT += ".";
        else
            first = false;
        fT += s;
    }
    return fT;
}
int main() {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */   
    
    // # of lines & queries
    int n, q;
    string newline;
    cin >> n >> q >> newline;
    // string line1;
    // getline(cin, line1);
    // istringstream iss1(line1);
    // iss1 >> n >> q;
    
    // Map of tag to map of attribute to value
    unordered_map<string,unordered_map<string,string>> hrml;
    
    // Stack of tags
    list<string> tagStack;
    
    // Iterate over source lines
    for (int i = 0; i < n; i++) {
        string line;
        getline(cin, line);
        cout << line << endl;
        string cLine = line.substr(1, line.length()-2);
        
        // Tag closing
        if (cLine[0] == '/') {
            tagStack.pop_back();
            continue;
        }
        
        // Parse the tag
        istringstream iss(cLine);
        string tag;
        iss >> tag;
        tagStack.push_back(tag);
        
        // Iterate over attributes
        unordered_map<string,string> attrMap;
        string attr, value, eq;
        while (iss >> attr >> eq >> value) {
            string cValue = value.substr(1, value.length()-2);
            attrMap.insert({attr, cValue});
        }

        string fullTag = getFullTag(tagStack);
        hrml.insert({fullTag, attrMap});
    }
    
    // Iterate over queries
    string query;
    for (int i = 0; i < q; i++) {
        cin >> query;
        
        // Split into tag and attr
        int tildaIndex = query.find("~");
        string qTag = query.substr(0, tildaIndex);
        string qAttr = query.substr(tildaIndex+1, query.length()-tildaIndex-1);
        
        // Find the attribute map
        auto attrMapIter = hrml.find(qTag);
        if (attrMapIter == hrml.end()) {
            cout << "Not Found!" << endl;
            continue;
        }
        auto attrValIter = attrMapIter->second.find(qAttr);
        if (attrValIter == attrMapIter->second.end()) {
            cout << "Not Found!" << endl;
            continue;
        }
        cout << attrValIter->second << endl;
    }
    return 0;
}
