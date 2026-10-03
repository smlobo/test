/*
You are given n triangles, specifically, their sides a, b and c. Print them in 
the same style but sorted by their areas from the smallest one to the largest 
one. It is guaranteed that all the areas are different.

The best way to calculate a volume of the triangle with sides a, b and c is 
Heron's formula

Input Format
First line of each test file contains a single integer n. n lines follow with a,
b and c on each separated by single spaces.

Constraints
1 <= n <= 100
1 <= a, b, c <= 70
a + b > c, ...

Output Format
Print exactly n lines. On each line print 3 integers separated by single 
spaces, which are a, b and c of the corresponding triangle.

Sample Input 0
3
7 24 25
5 12 13
3 4 5

Sample Output 0
3 4 5
5 12 13
7 24 25

Explanation 0
The square of the first triangle is 64. The square of the second triangle is 30. 
The square of the third triangle is 6. So the sorted order is the reverse one.
*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

struct triangle
{
	int a;
	int b;
	int c;
};

typedef struct triangle triangle;

int compare_triangles(const void *xx, const void *yy) {
	const triangle *x = (const triangle *) xx;
	const triangle *y = (const triangle *) yy;

	int px = x->a + x->b + x->c;
	int ax = px * (px - 2*x->a) * (px - 2*x->b) * (px - 2*x->c);

	int py = y->a + y->b + y->c;
	int ay = py * (py - 2*y->a) * (py - 2*y->b) * (py - 2*y->c);

	return ax - ay;
}

void sort_by_area(triangle* tr, int n) {
	/**
	* Sort an array a of the length n
	*/
	qsort(tr, n, sizeof(triangle), compare_triangles);
}

int main()
{
	int n;
	scanf("%d", &n);
	triangle *tr = malloc(n * sizeof(triangle));
	for (int i = 0; i < n; i++) {
		scanf("%d%d%d", &tr[i].a, &tr[i].b, &tr[i].c);
	}
	sort_by_area(tr, n);
	for (int i = 0; i < n; i++) {
		printf("%d %d %d\n", tr[i].a, tr[i].b, tr[i].c);
	}
	return 0;
}
