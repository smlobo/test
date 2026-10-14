#include <iostream>
#include <set>
#include <string>
#include <vector>

struct Player {
    std::string name;
    int score;
    bool operator<(const Player& other) const {
        if (score != other.score) {
            return score > other.score;
        }
        return name < other.name;
    }
};

struct ByName {
    bool operator()(const Player& a, const Player& b) const {
        return a.name < b.name;
    }
};

std::ostream& operator<<(std::ostream& os, const Player& p) {
    os << "{" << p.name << ", " << p.score << "}";
    return os;
}

template <typename Compare>
std::ostream& operator<<(std::ostream& os, const std::set<Player, Compare>& s) {
    for (const auto& p : s) {
        os << p << ", ";
    }
    return os;
}

int main() {
    std::set<Player> playerSet = {};
    std::vector<Player> players = {
        Player{"Zoe", 80},
        Player{"Amy", 80},
        Player{"Max", 95},
        Player{"Amy", 80},
    };
    for (const auto& player : players) {
        const auto& emplaceRet = playerSet.emplace(player);
        if (emplaceRet.second) {
            std::cout << "Unique player: " << *emplaceRet.first << "\n";
        } else {
            std::cout << "Existing player: " << *emplaceRet.first << "\n";            
        }
    }
    std::cout << "Ordered player set:\n\t" << playerSet << "\n";

    // Name only ordered set
    players.back().score = 95;
    std::set<Player, ByName> namePlayerSet = {};
    for (const auto& player : players) {
        const auto& emplaceRet = namePlayerSet.emplace(player);
        if (emplaceRet.second) {
            std::cout << "Unique player: " << *emplaceRet.first << "\n";
        } else {
            std::cout << "Existing player: " << *emplaceRet.first << 
                ", does not replace with new: " << player << "\n";            
        }
    }
    std::cout << "Name-only player set:\n\t" << namePlayerSet << "\n";
}
