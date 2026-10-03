/*
You are given a main() function which takes a set of inputs to create two 
matrices and prints the result of their addition. You need to write the class 
Matrix which has a member a of type vector<vector<int> >. You also need to 
write a member function to overload the operator +. The function's job will be 
to add two objects of Matrix type and return the resultant Matrix.

Input Format

First line will contain the number of test cases x. For each test case, there 
are three lines of input.

The first line of each test case will contain two integers m and n which denote 
the number of the rows and columns respectively of the two matrices that will 
follow on the next two lines. These next two lines will each contain m*n 
elements describing the two matrices in row-wise format.

Output Format

The code provided in the editor will use your class Matrix and overloaded 
operator function to add the two matrices and give the output.

Sample Input
1
2 2
2 2 2 2
1 2 3 4

Sample Output
3 4 
5 6

*/

//#include <cmath>
#include <cstdio>
//#include <stdio.h>
#include <vector>
#include <iostream>
//#include <algorithm>
using namespace std;

class Matrix {
public:
   vector< vector<int> > a;

   /*Matrix operator+(Matrix that) {
      Matrix result;

      for (int i = 0; i < a.size(); i++) {
         vector<int> b;
         for (int j = 0; j < a[i].size(); j++) {
            b.push_back(a[i][j] + that.a[i][j]);
         }
         result.a.push_back(b);
      }

      Matrix result(that);
      for (int i = 0; i < a.size(); i++) {
         for (int j = 0; j < a[i].size(); j++) {
            result.a[i][j] += a[i][j];
         }
      }

      return result;

      for (int i = 0; i < a.size(); i++) {
         for (int j = 0; j < a[i].size(); j++) {
            that.a[i][j] += a[i][j];
         }
      }

      return that;
   }*/

   Matrix & operator+ (const Matrix &that) {
      for (int i = 0; i < a.size(); i++) {
         for (int j = 0; j < a[i].size(); j++) {
            a[i][j] += that.a[i][j];
         }
      }
      return *this;
   }

   string toString() {
      string result(100, '\0');
      result += "[ ";
      for (int i = 0; i < a.size(); i++) {
         vector<int> b = a[i];
         for (int j = 0; j < b.size(); j++) {
            char x[10];
            sprintf(x, "%d", b[j]);
            result += x;
            if (j != b.size()-1)
               result += ", ";
         }
         if (i != a.size()-1)
            result += "; ";
      }
      result += "] ";
      return result;      
   }
};

int main () {
   int cases,k;
   cin >> cases;
   for(k=0;k<cases;k++) {
      Matrix x;
      Matrix y;
      Matrix result;
      int n,m,i,j;
      cin >> n >> m;
      for(i=0;i<n;i++) {
         vector<int> b;
         int num;
         for(j=0;j<m;j++) {
            cin >> num;
            b.push_back(num);
         }
         x.a.push_back(b);
      }
      // cout << x.toString() << endl;
      for(i=0;i<n;i++) {
         vector<int> b;
         int num;
         for(j=0;j<m;j++) {
            cin >> num;
            b.push_back(num);
         }
         y.a.push_back(b);
      }
      // cout << y.toString() << endl;
      result = x+y;
      for(i=0;i<n;i++) {
         for(j=0;j<m;j++) {
            cout << result.a[i][j] << " ";
         }
         cout << endl;
      }
   }  
   return 0;
}
