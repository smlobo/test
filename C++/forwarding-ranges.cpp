#include <iostream>
#include <ranges>
#include <list>

template<typename T>
void oldPrint(const T& collection) {
    std::cout << "\tOld {";
    for (const auto& elem : collection) {
        std::cout << elem << ", ";
    }
    std::cout << "}\n";
}

void newPrint(auto&& collection) {
    std::cout << "\tNew {";
    for (const auto& elem : collection) {
        std::cout << elem << ", ";
    }
    std::cout << "}\n";
}

int main() {
    std::vector vec{1, 2, 3, 4, 5};
    std::list lst{10, 20, 30, 40, 50};

    std::cout << "vec:\n";
    oldPrint(vec);
    newPrint(vec);
    std::cout << "vec | drop(3):\n";
    oldPrint(vec | std::views::drop(3));
    newPrint(vec | std::views::drop(3));
    std::cout << "vec | filter(...):\n";
    // oldPrint(vec | std::views::filter([](int x) {
    //     return x % 2 == 0;
    // }));
    newPrint(vec | std::views::filter([](int x) {
        return x % 2 == 0;
    }));
    std::cout << "lst:\n";
    oldPrint(lst);
    newPrint(lst);
    std::cout << "lst | drop(3):\n";
    // oldPrint(lst | std::views::drop(3));
    newPrint(lst | std::views::drop(3));
}
