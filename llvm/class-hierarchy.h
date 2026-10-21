#ifndef _CLASS_HIERARCHY
#define _CLASS_HIERARCHY

#include <string>

using namespace std;

class Tree {
public:
    int height;
    string type();
};

class Maple : public Tree {
public:
    string type();
};

class Oak : public Tree {
public:
    string type();
};

#endif // _CLASS_HIERARCHY