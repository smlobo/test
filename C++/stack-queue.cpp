#include <iostream>
#include <stack>
#include <queue>
#include <vector>
#include <string>

int main() {
    std::vector<std::string> tasks = {
        "compile",
        "link",
        "execute",
    };
    std::stack<std::string> stk = {};
    std::queue<std::string> que = {};
    for (const auto& task : tasks) {
        stk.push(task);
        que.push(task);
    }
    std::cout << "Stack pop order:\n\t";
    while (!stk.empty()) {
        std::cout << stk.top() << ", ";
        stk.pop();
    }
    std::cout << "\nQueue pop order:\n\t";
    while (!que.empty()) {
        std::cout << que.front() << ", ";
        que.pop();
    }
    std::cout << "\n";
}