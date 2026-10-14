#include <ios>
#include <iostream>
#include <map>
#include <string>

struct Date {
    int year;
    int month;
    int day;
    bool operator<(const Date& other) const {
        if (year != other.year) {
            return year < other.year;
        }
        if (month != other.month) {
            return month < other.month;
        }
        return day < other.day;
    }
};

struct NewestFirst {
    bool operator()(const Date& a, const Date& b) const {
        if (a.year != b.year) {
            return a.year > b.year;
        }
        if (a.month != b.month) {
            return a.month > b.month;
        }
        return a.day > b.day;
    }
};

std::ostream& operator<<(std::ostream& os, const Date& d) {
    os << d.year << "-" << d.month << "-" << d.day;
    return os;
}

template <typename Comparator>
std::ostream& operator<<(
    std::ostream& os, const std::map<Date,std::string,Comparator>& m) {
    for (const auto& e : m) {
        os << "{" << e.first << " : " << e.second << "}, ";
    }
    return os;
}

int main() {
    std::map<Date, std::string> dateEventMap = {
        {{2025, 11, 12}, "second"},
        {{2026, 11, 12}, "third"},
        {{2025, 11, 11}, "first"},
    };
    std::cout << "Ordered event map:\n\t" << dateEventMap << "\n";

    std::map<Date, std::string, NewestFirst> newFirstMap = {};
    for (const auto& e : dateEventMap) {
        newFirstMap.emplace(e.first, e.second);
    }
    std::cout << "Newest first map:\n\t" << newFirstMap << "\n";

    // emplace non-unique event
    const auto& dupEmplace = newFirstMap.emplace(
        Date{2025, 11, 12}, "duplicate");
    bool dupEmplaceResult = dupEmplace.second;
    const auto& dupEmplaceIter = dupEmplace.first;
    std::cout << "Adding a duplicate date returns: " << std::boolalpha << 
        dupEmplaceResult << "\n";
    std::cout << "\tExisting entry is: " << dupEmplaceIter->first << " : " << 
        dupEmplaceIter->second << "\n";
    std::cout << "With dup map:\n\t" << newFirstMap << "\n";

    Date b = {2025, 11, 12};
    // Lower bound
    auto it = dateEventMap.lower_bound(b);
    if (it == dateEventMap.end()) {
        std::cout << "No event on or after: " << b << "\n";
    } else {
        std::cout << "First event on or after: " << b << " = " << "{" 
            << it->first << " : " << it->second << "}, \n";
    }
    // Upper bound
    it = dateEventMap.upper_bound(b);
    if (it == dateEventMap.end()) {
        std::cout << "No event after: " << b << "\n";
    } else {
        std::cout << "First event after: " << b << " = " << "{" 
            << it->first << " : " << it->second << "}, \n";
    }
}
