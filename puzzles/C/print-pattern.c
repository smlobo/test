/*
In this problem, you need to print the pattern of the following form containing 
the numbers from 1 to n.

                            4 4 4 4 4 4 4  
                            4 3 3 3 3 3 4   
                            4 3 2 2 2 3 4   
                            4 3 2 1 2 3 4   
                            4 3 2 2 2 3 4   
                            4 3 3 3 3 3 4   
                            4 4 4 4 4 4 4   
*/

#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() 
{
    int n;
    scanf("%d", &n);

    for (int i = n; i > 0; i--) {
    	int x = n;
    	for (int j = 0; j < n; j++) {
    		printf("%d ", x);
    		if (x > i)
    			x--;
    	}
    	for (int j = 2; j <= n; j++) {
    		if (j > x)
    			x++;
    		printf("%d ", x);
    	}
    	printf("\n");
    }

    for (int i = 2; i <= n; i++) {
    	int x = n;
    	for (int j = 0; j < n; j++) {
    		printf("%d ", x);
    		if (x > i)
    			x--;
    	}
    	for (int j = 2; j <= n; j++) {
    		if (j > x)
    			x++;
    		printf("%d ", x);
    	}
    	printf("\n");
    }

    return 0;
}

