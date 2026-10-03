#include <iostream>
#include <string>
#include <format>
#include <functional>

using namespace std;

int main(int argc, char** argv) {
    int n = stoi(argv[1]);
    cout << format("Generating {:d} fibos.\n", n);

    int prev = 0;
    int curr = 1;

    function<void()> fibo = [&]() -> void {
        if (0 == n)
            return;
        cout << format("{:d}, ", curr);
        int next = prev + curr;
        prev = curr;
        curr = next;
        n--;
        fibo();
    };

    fibo();
    cout << endl;
}