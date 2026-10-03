#include <algorithm>
#include <cstddef>
#include <iostream>
#include <ostream>
#include <queue>
#include <map>
#include <vector>

typedef std::pair<char,int> PQItem;

struct CompareSecond {
    bool operator()(const PQItem& a, const PQItem& b) {
        return a.second > b.second;
    }
};

std::vector<char> topKElements(const std::vector<char>& iVec, std::size_t k) {
    // Count the freq of elements
    std::map<char,int> freqMap = {};
    for (char iChar : iVec) {
        freqMap[iChar] += 1;
    }
    // for (const std::pair<char,int>& freqElement : freqMap) {
    //     std::cout << "[" << freqElement.first << "] " << freqElement.second << "\n";
    // }

    // Priority queue of top K values
    std::priority_queue<PQItem, std::vector<PQItem>, CompareSecond> topKPQ = {};
    for (const std::pair<char,int>& freqElement : freqMap) {
        // Insert into PQ
        topKPQ.emplace(freqElement.first, freqElement.second);
        // Prune
        if (topKPQ.size() > k) {
            topKPQ.pop();
        }
    }

    // Populate the output vector
    std::vector<char> oVec = {};
    while (!topKPQ.empty()) {
        oVec.emplace_back(topKPQ.top().first);
        topKPQ.pop();
    }

    std::reverse(oVec.begin(), oVec.end());
    return oVec;
}

std::ostream& operator<<(std::ostream& os, const std::vector<char>& v) {
    for (const char& c : v) {
        os << c << ", ";
    }
    return os;
}

int main() {
    std::vector<char> i1 = {'a', 'b', 'c', 'a', 'd', 'c', 'c'};
    std::vector<char> o1 = topKElements(i1, 2);
    std::cout << o1 << "\n";
}
