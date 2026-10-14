#include <iostream>
#include <iterator>
#include <list>
#include <ostream>

template <typename T>
std::ostream& operator<<(std::ostream& os, const std::list<T>& l) {
    for (auto it = l.begin(); it != l.end(); ++it) {
        os << *it << ", ";
    }
    return os;
}

int main() {
    std::list<int> a = {1, 2, 3};
    std::list<int> a_copy = a;
    std::list<int> b = {10, 20};
    std::list<int> b_copy = b;
    std::cout << "initial list a: " << a << "\n";
    std::cout << "initial list b: " << b << "\n";

    // splice b on the end of a
    a.splice(a.end(), b);
    std::cout << "after splice all a: " << a << "\n";
    std::cout << "after splice all b: " << b << "\n";

    // splice single element from b onto a
    a = a_copy;
    b = b_copy;
    b.emplace_back(30);
    std::cout << "initial list a: " << a << "\n";
    std::cout << "initial list b: " << b << "\n";
    auto a_it = a.begin();
    ++a_it;
    auto b_it = b.begin();
    ++b_it;
    a.splice(a_it, b, b_it);
    std::cout << "after splice single a: " << a << "\n";
    std::cout << "after splice single b: " << b << "\n";

    // splice range
    a = a_copy;
    b = {10, 20, 30, 40, 50};
    std::cout << "initial list a: " << a << "\n";
    std::cout << "initial list b: " << b << "\n";
    a_it = std::prev(a.end());
    b_it = std::next(b.begin());
    auto bend_it = std::next(b_it, 2);
    a.splice(a_it, b, b_it, bend_it);
    std::cout << "after splice range a: " << a << "\n";
    std::cout << "after splice range b: " << b << "\n";

    // change saved iterators
    *a_it += 300;
    *b_it += 2000;
    *bend_it += 4000;
    std::cout << "after mod save it a: " << a << "\n";
    std::cout << "after mod save it b: " << b << "\n";

    // splice in the same list
    a = {1, 2, 3, 4, 5};
    std::cout << "initial list a: " << a << "\n";
    auto a_to_it = std::next(a.begin());
    auto a_from_it = std::prev(a.end(), 2);
    a.splice(a_to_it, a, a_from_it);
    std::cout << "after internal splice a: " << a << "\n";

    // iterate and splice
    a = {1, 3, 5};
    b = {2, 4, 6, 7};
    std::cout << "initial list a: " << a << "\n";
    std::cout << "initial list b: " << b << "\n";
    b_it = b.begin();
    while (b_it != b.end()) {
        if (*b_it % 2 == 0) {
            auto splice_it = b_it;
            ++b_it;
            a.splice(a.end(), b, splice_it);
        } else {
            ++b_it;
        }
    }
    std::cout << "after splice even a: " << a << "\n";
    std::cout << "after splice even b: " << b << "\n";
}