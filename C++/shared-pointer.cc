#include <iostream>
#include <memory>
#include <vector>

#include "cartesian.h"
#include "utilities.h"

using namespace std;

int main() {
    shared_ptr<Cartesian> sC1a = make_shared<Cartesian>(randomD(), randomD());
    cout << "[" << &sC1a << "] sC1a = " << *sC1a << "; get()=" << sC1a.get() << 
        "; use_count()=" << sC1a.use_count() << "\n";
    shared_ptr<Cartesian> sC1b = sC1a;
    cout << "[" << &sC1b << "] sC1b = " << *sC1b << "; get()=" << sC1b.get() << 
        "; use_count()=" << sC1b.use_count() << "\n";

    // Swap
    shared_ptr<Cartesian> sC2a = make_shared<Cartesian>(randomD(), randomD());
    cout << "[" << &sC2a << "] sC2a = " << *sC2a << "; get()=" << sC2a.get() << 
        "; use_count()=" << sC2a.use_count() << "\n";
    sC1a.swap(sC2a);
    cout << "After swap [" << &sC1a << "] sC1a = " << *sC1a << "; get()=" << 
        sC1a.get() << "; use_count()=" << sC1a.use_count() << "\n";
    cout << "After swap [" << &sC2a << "] sC2a = " << *sC2a << "; get()=" << 
        sC2a.get() << "; use_count()=" << sC2a.use_count() << "\n";

    // Vectors of shared pointers
    vector<shared_ptr<Cartesian>> sCVa, sCVb;
    sCVa.emplace_back(make_shared<Cartesian>(randomD(), randomD()));
    sCVb.emplace_back(sCVa.back());
    sCVa.emplace_back(make_shared<Cartesian>(randomD(), randomD()));
    sCVb.push_back(sCVa.back());
    sCVa.emplace_back(make_shared<Cartesian>(randomD(), randomD()));
    sCVb.push_back(sCVa.back());
    for (unsigned i = 0; i < sCVa.size(); i++) {
        cout << "  [" << &sCVa[i] << "] sCVa[" << i << "] = " << *sCVa[i] << 
            "; get=" << sCVa[i].get() << "; use_count=" << sCVa[i].use_count() 
            << "\n";
        auto &sCa = sCVa[i];
        cout << "  Ref [" << &sCa << "] sCVa = " << *sCa << "; get=" << 
            sCa.get() << "; use_count=" << sCa.use_count() << "\n";
        cout << "  [" << &sCVb[i] << "] sCVb[" << i << "] = " << *sCVb[i] << 
            "; get=" << sCVb[i].get() << "; use_count=" << sCVb[i].use_count() 
            << "\n";
        auto &sCb = sCVb[i];
        cout << "  Ref [" << &sCb << "] sCVb = " << *sCb << "; get=" << 
            sCb.get() << "; use_count=" << sCb.use_count() << "\n";
    }

    // Erase from vector sCVa
    sCVa.erase(sCVa.begin());
    cout << "After erase sCVa[0] [" << &sCVb[0] << "] sCVb[0] = " << *sCVb[0] 
        << "; get=" << sCVb[0].get() << "; use_count=" << sCVb[0].use_count() 
        << "\n";
    // Clear vector sCVb
    sCVb.clear();
    cout << "After clear sCVb [" << &sCVa[0] << "] sCVa[0] = " << *sCVa[0] 
        << "; get=" << sCVa[0].get() << "; use_count=" << sCVa[0].use_count() 
        << "\n";
    cout << "After clear sCVb [" << &sCVa[1] << "] sCVa[1] = " << *sCVa[1] 
        << "; get=" << sCVa[1].get() << "; use_count=" << sCVa[1].use_count() 
        << "\n";

    cout << "--- end main() ---\n";
}
