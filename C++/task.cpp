#include <algorithm>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

struct Task {
    std::string name;
    int priority;
    bool operator<(const Task& other) const {
        if (priority != other.priority) {
            return priority < other.priority;
        }
        return name > other.name;
    }
};

struct ReverseTaskComparator {
    bool operator()(const Task& a, const Task& b) const {
        if (a.priority != b.priority) {
            return a.priority > b.priority;
        }
        return a.name < b.name;
    }
};

std::ostream& operator<<(std::ostream& os, const Task& t) {
    os << "{" << t.name << "," << t.priority << "}";
    return os;
}

std::ostream& operator<<(std::ostream& os, const std::vector<Task>& v) {
    for (const auto & task : v) {
        os << task << ", ";
    }
    return os;
}

int main() {
    std::vector<Task> tasks = {{"Z", 10}, {"A", 10}, {"A", 1}};
    std::cout << "Original order:\n\t" << tasks << "\n";
    std::sort(tasks.begin(), tasks.end());
    std::cout << "Sorted order:\n\t" << tasks << "\n";

    std::priority_queue<Task> taskPQ = {};
    for (const auto& task : tasks) {
        taskPQ.emplace(task);
    }
    std::cout << "PQ top() order:\n\t";
    while (!taskPQ.empty()) {
        const Task& t = taskPQ.top();
        std::cout << t << ", ";
        taskPQ.pop();
    }
    std::cout << "\n";

    std::reverse(tasks.begin(), tasks.end());
    std::cout << "Reverse sorted (matches PQ):\n\t" << tasks << "\n";

    std::priority_queue<Task, std::vector<Task>, ReverseTaskComparator> 
        rTaskPQ = {};
    for (const auto& task : tasks) {
        rTaskPQ.emplace(task);
    }
    std::cout << "Reverse PQ top() order:\n\t";
    while (!rTaskPQ.empty()) {
        const Task& t = rTaskPQ.top();
        std::cout << t << ", ";
        rTaskPQ.pop();
    }
    std::cout << "\n";
}
