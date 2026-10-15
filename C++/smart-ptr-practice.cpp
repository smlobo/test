#include <ios>
#include <iostream>
#include <memory>
#include <string>
#include <utility>
#include <vector>
#include <functional>

class Item {
public:
    std::string name;
    ~Item() {
        std::cout << "[Destructor] Item: " << name << "\n";
    }
};

// borrow
void inspect(const Item& i) {
    std::cout << "[inspect] " << i.name << "\n";
}

// own
void consume(std::unique_ptr<Item> i) {
    i.get()->name += "-consume";
    std::cout << "[consume] " << i->name << "\n";
}

int main() {
    std::unique_ptr<int> src_ptr = std::make_unique<int>(42);
    std::cout << "src_ptr: " << *src_ptr << "\n";
    std::unique_ptr<int> dest_ptr = std::move(src_ptr);
    std::cout << "Is src_ptr empty? " << std::boolalpha 
        << (src_ptr == nullptr) << "\n";
    std::cout << "dest_ptr: " << *dest_ptr << "\n";

    // object
    std::unique_ptr<Item> item = std::make_unique<Item>("ABC");
    inspect(*item);
    consume(std::move(item));

    // vector of unique_ptr
    std::vector<std::unique_ptr<Item>> items;
    items.emplace_back(std::make_unique<Item>("Item1"));
    items.emplace_back(std::make_unique<Item>("Item2-Moved"));
    items.emplace_back(std::make_unique<Item>("Item3"));
    std::unique_ptr<Item> dest_item = std::move(items[1]);
    for (size_t i = 0; i < items.size(); i++) {
        if (items[i] != nullptr) {
            std::cout << "[" << i << "] " << items[i]->name << "\n";
        } else {
            std::cout << "[" << i << "] <empty>\n";
        }
    }

    // shared pointer
    std::shared_ptr<Item> a = std::make_shared<Item>("shared-item");
    std::cout << "shared count [a]: " << a.use_count() << ", " << a->name 
        << "\n";
    {
        std::shared_ptr<Item> b = a;
        std::cout << "shared count [b]: " << b.use_count() << ", " << b->name 
            << "\n";
        {
            std::shared_ptr<Item> c = b;
            std::cout << "shared count [c]: " << c.use_count() << ", "
                << c->name << "\n";
        }
    }
    std::cout << "shared [scope end] count: " << a.use_count() << ", " 
        << a->name << "\n";

    // weak ptr
    std::shared_ptr<Item> w_shared = std::make_shared<Item>("weak-shared");
    std::weak_ptr<Item> weak = w_shared;
    std::cout << "weak count: " << w_shared.use_count() << ", "
        << w_shared->name << "\n";
    if (auto locked = weak.lock()) {
        std::cout << "shared-owner count in lock: " << weak.use_count() << ", "
            << locked->name << "\n";
    }
    w_shared.reset();
    std::cout << "Weak expired? " << std::boolalpha << weak.expired() << "\n";

    // move-only-lambda
    std::unique_ptr<Item> lambda_item = std::make_unique<Item>("lambda-item");
    std::move_only_function<void()> lambda = [i = std::move(lambda_item)] {
        std::cout << "in lambda: " << i->name << "\n";
    };
    std::vector<std::move_only_function<void()>> lambda_vecs;
    lambda_vecs.emplace_back(std::move(lambda));
    for (auto& ll : lambda_vecs) {
        ll();
    }
    lambda_vecs.clear();
}
