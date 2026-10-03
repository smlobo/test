#include <iomanip>
#include <cmath>

#include "cartesian.h"

using namespace std;

Cartesian::Cartesian() :
    x(0.0), y(0.0) {}

Cartesian::Cartesian(double x, double y) : 
    x(x), y(y) {
    cout << fixed << setprecision(4);
    cout << "Cartesian Constructor:  <" << x << "," << y << ">\n"; 
}

Cartesian::~Cartesian() {
    cout << "Cartesian Destructor: " << *this << "\n";
}

bool Cartesian::operator<(const Cartesian& that) const {
    return (x != that.x) ? (x < that.x) : (y < that.y);    
}

Cartesian Cartesian::operator+(const Cartesian& that) {
    Cartesian result;
    result.x = this->x + that.x;
    result.y = this->y + that.y;
    return result;
}

ostream& operator<<(ostream& strm, const Cartesian& c) {
    strm << fixed << setprecision(4);
    strm << "<" << c.x << "," << c.y << ">";
    return strm;
}

bool CartesianComparator::operator()(const Cartesian& a, const Cartesian& b) const {
    return (a.x != b.x) ? (a.x < b.x) : (a.y < b.y);
}

bool ReverseYCartesianComparator::operator()(const Cartesian& a, const Cartesian& b) const {
    return a.y > b.y;
}

size_t CartesianHash::operator()(Cartesian const& c) const {
    size_t hx = hash<double>{}(c.x);
    size_t hy = hash<double>{}(c.y);
    return hx ^ (hy << 1);
}

bool CartesianEquals::operator()(const Cartesian& a, const Cartesian& b) const {
    return (abs(a.x-b.x) < numeric_limits<double>::epsilon()) && 
        (abs(a.y-b.y) < numeric_limits<double>::epsilon());
}
