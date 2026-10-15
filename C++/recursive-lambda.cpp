#include <iostream>
#include <vector>

struct Node {
    int value;
    std::vector<Node> children;
};

int main() {
    Node tree{1, 
        {Node{2, {}},
         Node{3, {Node{4, {}}}}
        }
    };
    auto sum = [](this auto& self, const Node& node) -> int {
        int sum = 0;
        for (const Node& child : node.children) {
            sum += self(child);
        }
        return sum + node.value;
    };
    std::cout << "Tree sum: " << sum(tree) << "\n";
}