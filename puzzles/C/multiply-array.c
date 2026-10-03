/* Given an array, multiple content at each index by every other array 
   context except the current index, no division */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int x[10];
int y[10];
int z[10];

int multiply_next(int a) {
  if (a == 9)
    y[a] = 1;
  else
    y[a] = x[a+1] * multiply_next(a+1);
  return y[a];
}

int multiply_prev(int a) {
  if (a == 0)
    z[a] = 1;
  else 
    z[a] = x[a-1] * multiply_prev(a-1);
  return z[a];
}

void print_array(int p[]) {
    for (int i=0; i<10; i++) {
       printf("%d, ", p[i]);
    }
    printf("\n");
}

void my_function(){
    for (int i=0; i<10; i++) {
       x[i] = i+2;
    }
    print_array(x);
    multiply_next(0);
    print_array(x);
    print_array(y);
    multiply_prev(9);
    print_array(x);
    print_array(y);
    print_array(z);
    for (int i=0; i<10; i++) {
       x[i] = y[i] * z[i];
    }
    print_array(x);
}

int main() {
    my_function();
    return 0;
}

