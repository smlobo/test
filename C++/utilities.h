#ifndef _UTILITIES_H
#define _UTILITIES_H

#include <iostream>
#include <vector>
#include <unordered_map>
#include <map>
#include <list>

using namespace std;

// Prototypes of utility functions

double randomD();
int randomInt(int, int);

// Implementations for type specialization

template <class T>
ostream& operator<<(ostream& strm, const vector<T>& v) {
    for (const T& t : v)
        strm << t << ", ";
    // for (typename vector<T>::iterator it = v.begin(); it != v.end(); it++)
    // for (auto it = v.begin(); it != v.end(); it++)
        // strm << *it << ", ";
    // for (int i = 0; i != v.size(); i++)
    //     strm << v[i] << ", ";
    return strm;   
}

template <class K, class V, class H, class E>
ostream& operator<<(ostream& strm, unordered_map<K,V,H,E>& m) {
    for (const auto& i : m)
        strm << "{" << i.first << ":" << i.second << "}, ";
    // for (auto i = m.begin(); i != m.end(); i++)
    //     strm << "{" << i->first << ":" << i->second << "}, ";
    return strm;
}

template <class K, class V, class C>
ostream& operator<<(ostream& strm, map<K,V,C>& m) {
    // for (const auto& i : m)
    //     strm << "{" << i.first << ":" << i.second << "}, ";
    for (auto i = m.begin(); i != m.end(); i++)
        strm << "{" << i->first << ":" << i->second << "}, ";
    return strm;
}

template <class T>
ostream& operator<<(ostream& strm, list<T>& l) {
    // for (const T& t : l)
    //     strm << t << ", ";
    for (typename list<T>::iterator it = l.begin(); it != l.end(); it++)
    // for (auto it = l.begin(); it != l.end(); it++)
        strm << *it << ", ";
    return strm;   
}

#endif // _UTILITIES_H
