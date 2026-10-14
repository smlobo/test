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

std::ostream& operator<<(std::ostream& os, const Date& d) {
    os << d.year << "-" << d.month << "-" << d.day;
    return os;
}

template <typename Comparator>
std::ostream& operator<<(
    std::ostream& os, const std::multimap<Date,std::string,Comparator>& m) {
    for (const auto& e : m) {
        os << "{" << e.first << " : " << e.second << "}, ";
    }
    return os;
}

int main() {
    std::multimap<Date, std::string> dateEventMMap = {
        {{2025, 11, 12}, "second"},
        {{2026, 11, 12}, "third"},
        {{2025, 11, 11}, "first"},
        {{2025, 11, 12}, "duplicate"},
    };
    std::cout << "Ordered event map:\n\t" << dateEventMMap << "\n";
    Date d = {2025, 11, 12};
    auto [begin, end] = dateEventMMap.equal_range(d);
    std::cout << "All events on " << d << ":\n\t";
    for (auto it = begin; it != end; ++it) {
        std::cout << "{" << it->first << " : " << it->second << "}, ";
    }
    std::cout << "\n";
}
