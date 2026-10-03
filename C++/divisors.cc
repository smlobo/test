// Given a number, generate a vector of its divisors

#include <iostream>
#include <vector>
#include <cstdint>

using namespace std;

class Divisors {
public:
	static void populateDivisors(uint64_t x, vector<uint64_t>& divisors) {
		
	}
};

ostream& operator<<(ostream& strm, vector<uint64_t>& uint64Vec) {
	bool first = true;
	for (uint64_t n : uint64Vec) {
		if (first)
			first = false;
		else
			strm << ", ";
		strm << n;
	}
	return strm;
}

int main(int argc, char* argv[]) {
	// input arguments
	if (argc != 2) {
		cout << "Usage: " << argv[0] << " <n>\n";
		return 1;
	}

	uint64_t n = stol(argv[1]);
	cout << "Generating divisors for: " << n << "\n";

	vector<uint64_t> divisors;
	Divisors::populateDivisors(n, divisors);

	// Print
	cout << "  [" << divisors << "]\n";

	return 0;
}