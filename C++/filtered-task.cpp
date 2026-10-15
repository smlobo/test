#include <iostream>
#include <string>
#include <vector>
#include <ranges>

struct Task {
    std::string name;
    int priority;
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
    std::vector<Task> tasks = {{"Z", 2}, {"A", 8}, {"B", 12}};
    std::cout << "Original tasks:\n\t" << tasks << "\n";

    int threshold = 5;
    auto filtereds = tasks | std::views::filter([threshold](const Task& t) {
        return t.priority > threshold;
    });
    threshold = 10;
    std::vector<Task> filteredTasks = std::ranges::to<std::vector<Task>>(
        filtereds);
    std::cout << "Filtered tasks:\n\t" << filteredTasks << "\n";
}
