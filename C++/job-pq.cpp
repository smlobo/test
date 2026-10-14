#include <algorithm>
#include <iostream>
#include <ostream>
#include <queue>
#include <string>
#include <vector>
#include <random>
#include <chrono>

struct Job {
    std::string id;
    int deadline;
    int priority;
};

struct JobComparator {
    bool operator()(const Job& a, const Job& b) const {
        if (a.deadline != b.deadline) {
            return a.deadline > b.deadline;
        }
        if (a.priority != b.priority) {
            return a.priority < b.priority;
        }
        return a.id > b.id;
    }
};

std::ostream& operator<<(std::ostream& os, const Job& j) {
    os << "{" << j.id << ", " << j.deadline << ", " << j.priority << "}";
    return os;
}

int main() {
    // Random for shuffle
    std::default_random_engine engine;
    engine.seed(static_cast<std::default_random_engine::result_type>(
        std::chrono::system_clock::now().time_since_epoch().count()));
    std::vector<Job> jobs = {
        {"first", 1, 100},
        {"second", 1, 90},
        {"third", 2, 100},
        {"fourth", 2, 90},
        {"fifth", 3, 110},
        {"sixth", 3, 110},
    };
    std::shuffle(jobs.begin(), jobs.end(), engine);

    std::priority_queue<Job, std::vector<Job>, JobComparator> jobPQ = {};
    for (const auto& job : jobs) {
        jobPQ.emplace(job);
    }
    std::cout << "Job PQ top order:\n\t";
    while (!jobPQ.empty()) {
        const auto& job = jobPQ.top();
        std::cout << job << ", ";
        jobPQ.pop();
    }
    std::cout << "\n";
}
