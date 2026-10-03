#ifndef _CARTESIAN_H
#define _CARTESIAN_H

#include <iostream>

using namespace std;

class Cartesian {
public:
    double x;
    double y;
    Cartesian();
    Cartesian(double, double);
    ~Cartesian();
    bool operator<(const Cartesian&) const;
    Cartesian operator+(const Cartesian&);
    friend ostream& operator<<(ostream&, const Cartesian&);
};

class CartesianComparator {
public:
    bool operator()(const Cartesian&, const Cartesian&) const;
};

class ReverseYCartesianComparator {
public:
    bool operator()(const Cartesian&, const Cartesian&) const;
};

class CartesianHash {
public:
    size_t operator()(Cartesian const&) const;
};

class CartesianEquals {
public:
    bool operator()(const Cartesian&, const Cartesian&) const;
};

#endif // _CARTESIAN_H
