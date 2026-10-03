#include <iostream>
#include <memory>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

int main() {
    unique_ptr<Cartesian> uC1 = make_unique<Cartesian>(randomD(), randomD());
    cout << "[" << &uC1 << "] uC1 = " << *uC1 << " / ptr=" << uC1.get() << 
        "; val=" << uC1 << "\n";
    unique_ptr<Cartesian> uC2 = make_unique<Cartesian>(randomD(), randomD());
    cout << "[" << &uC2 << "] uC1 = " << *uC2 << " / ptr=" << uC2.get() << 
        "; val=" << uC2 << "\n";

    // Swap
    uC1.swap(uC2);
    cout << "After swap [" << &uC1 << "] uC1 = " << *uC1 << "; get()=" << 
        uC1.get() << "\n";
    cout << "After swap [" << &uC2 << "] uC2 = " << *uC2 << "; get()=" << 
        uC2.get() << "\n";

    // Vector of unique ptr
    vector<unique_ptr<Cartesian>> uCV;
    uCV.push_back(make_unique<Cartesian>(randomD(), randomD()));
    uCV.push_back(make_unique<Cartesian>(randomD(), randomD()));
    uCV.push_back(make_unique<Cartesian>(randomD(), randomD()));
    for (auto &uC : uCV) {
        cout << "  [" << &uC << "] uC = " << *uC << " / ptr=" << uC.get() << 
            "; val=" << uC << "\n";
    }

    // Move
    unique_ptr<Cartesian> uC3 = make_unique<Cartesian>(randomD(), randomD());
    cout << "[" << &uC3 << "] uC3 = " << *uC3 << " / ptr=" << uC3.get() << 
        "; val=" << uC3 << "\n";
    unique_ptr<Cartesian> uC3M = std::move(uC3);
    cout << "[" << &uC3M << "] uC3M = " << *uC3M << " / ptr=" << uC3M.get() << 
        "; val=" << uC3M << "\n";
    cout << "After move [" << &uC3 << "] uC3 = ? / ptr=" << uC3.get() << 
        "; val=" << uC3 << "\n";

    // Release
    unique_ptr<Cartesian> uC4 = make_unique<Cartesian>(randomD(), randomD());
    cout << "[" << &uC4 << "] uC4 = " << *uC4 << " / ptr=" << uC4.get() << 
        "; val=" << uC4 << "\n";
    Cartesian *releasedUC4 = uC4.release();
    delete releasedUC4;
    cout << "After release [" << &uC4 << "] uC4 = ? / ptr=" << uC4.get() << 
        "; val=" << uC4 << "\n";

    // Reset
    unique_ptr<Cartesian> uC5 = make_unique<Cartesian>(randomD(), randomD());
    cout << "[" << &uC5 << "] uC5 = " << *uC5 << " / ptr=" << uC5.get() << 
        "; val=" << uC5 << "\n";
    uC5.reset();
    cout << "After reset [" << &uC5 << "] uC5 = ? / ptr=" << uC5.get() << 
        "; val=" << uC5 << "\n";

    return 0;
}
