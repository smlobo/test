/*
A template parameter pack is a template parameter that accepts zero or more 
template arguments (non-types, types, or templates).

Create a template function named reversed_binary_value. It must take an 
arbitrary number of bool values as template parameters. These booleans represent 
binary digits in reverse order. Your function must return an integer 
corresponding to the binary value of the digits represented by the booleans. For 
example: reversed_binary_value<0,0,1>() should return 4.

Input Format

The first line contains an integer, t, the number of test cases. Each of the t 
subsequent lines contains a test case. A test case is described as 2 space-
separated integers, x and y, respectively.

* x is the value to compare against.
* y represents the range to compare: 64 * y to 64 * y + 63.

Constraints
* 0 <= x <= 65535
* 0 <= y <= 1023
* The number of template parameters passed to reversed_binary_value will be <= 
  16.

Output Format

Each line of output contains 64 binary characters (i.e., 1's and 0's). Each 
character represents one value in the range. The first character corresponds to 
the first value in the range. The last character corresponds to the last value 
in the range. The character is 1 if the value in the range matches x; otherwise, 
the character is 0.

Sample Input

2
65 1
10 0

Sample Output

0100000000000000000000000000000000000000000000000000000000000000
0000000000100000000000000000000000000000000000000000000000000000

Explanation

The second character on the first line is a 1, because the second value in the 
range 64..127 is 65 and x is 65.

The eleventh character on the second line is a 1, because the eleventh value in 
the range 0..63 is 10 and x is 10.

All other characters are 0, because the corresponding values in the range do 
not match x.

*/

#include <iostream>

using namespace std;

// Enter your code for reversed_binary_value<bool...>()

// base function to stop recursion
template<bool bit>
int reversed_binary_value() {
    return bit;
}

// recursive function to extract the variadic arguments
// Note: need to have 3 arguments since elipsis implies 0 arguments and we get 
// an ambiguous error for: template<bool bit, bool... digits>
template<bool bit1, bool bit2, bool... digits>
int reversed_binary_value() {
    int aa = reversed_binary_value<bit2, digits...>();
    //int bb = aa * 2 + bit1;
    int bb = (aa << 1) + bit1;
    return bb;
}

template <int n, bool...digits>
struct CheckValues {
  	static void check(int x, int y)
  	{
        //cout << "[0] n = " << n << endl;
    	CheckValues<n-1, 0, digits...>::check(x, y);
        //cout << "[1] n = " << n << endl;
    	CheckValues<n-1, 1, digits...>::check(x, y);
        //cout << "[done] n = " << n << endl;
  	}
};

template <bool...digits>
struct CheckValues<0, digits...> {
  	static void check(int x, int y)
  	{
    	int z = reversed_binary_value<digits...>();
    	std::cout << (z+64*y==x);
  	}
};

int main()
{
  	int t;
    std::cin >> t;

  	for (int i=0; i!=t; ++i) {
		int x, y;
    	cin >> x >> y;
    	CheckValues<6>::check(x, y);
    	cout << "\n";
  	}
}
