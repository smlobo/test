#include <cassert>
#include <functional>
#include <iostream>
#include <unordered_set>
#include <string>
#include <vector>

struct Contact {
    std::string email;
    std::string name;
};

struct ContactHash {
    bool operator()(const Contact& c) const {
        return std::hash<std::string>{}(c.email);
    }
};

struct ContactEquals {
    bool operator()(const Contact& a, const Contact& b) const {
        return a.email == b.email;
    }
};

std::ostream& operator<<(std::ostream& os, const Contact& c) {
    os << "{" << c.email << ", " << c.name << "}";
    return os;
}

template <typename Hash, typename Equals>
std::ostream& operator<<(std::ostream& os, 
    const std::unordered_set<Contact,Hash,Equals>& s) {
    for (const auto& c : s) {
        os << c << ", ";
    }
    return os;
}

int main() {
    std::vector<Contact> contacts = {
        {"a@example.com", "Amy"},
        {"b@example.com", "Ben"},
        {"a@example.com", "Alex"},
    };
    std::unordered_set<Contact, ContactHash, ContactEquals> contactsSet = {};
    for (const auto& contact : contacts) {
        const auto& [it, inserted] = contactsSet.emplace(contact);
        if (inserted) {
            std::cout << "Unique contact: " << *it << "\n";
        } else {
            std::cout << "Duplicate contact: (new): " << contact << ", (old); "
                << *it << "\n";
        }
    }
    assert(contactsSet.size() == 2);
    std::cout << "Contacts set:\n\t" << contactsSet << "\n";
}