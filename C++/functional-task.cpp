#include <iostream>
#include <string>
#include <vector>
#include <functional>
// #include <algorithm>
// #include <iterator>

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

std::vector<Task> filter(const std::vector<Task>& tasks, 
    std::function<bool(const Task&)> matchF) {

    std::vector<Task> mTasks = {};
    for (const Task& task : tasks) {
        if (matchF(task)) {
            mTasks.emplace_back(task);
        }
    }
    // std::copy_if(tasks.begin(), tasks.end(), std::back_inserter(mTasks), 
    //     matchF);
    return mTasks;
}

int main() {
    std::vector<Task> tasks = {{"Z", 10}, {"A", 10}, {"A", 1}};
    std::cout << "Original tasks:\n\t" << tasks << "\n";

    int threshold = 9;
    std::vector<Task> priorityTasks = filter(tasks, 
        [threshold](const Task& t) -> bool {
            return t.priority > threshold;
    });
    std::cout << "Tasks over threshold " << threshold << ":\n\t"
        << priorityTasks << "\n";

    std::vector<Task> nameTasks = filter(tasks, [](const Task& t) -> bool {
            return !t.name.empty() && t.name.at(0) == 'A';
    });
    std::cout << "Tasks name starting with A:\n\t" << nameTasks
        << "\n";
}
