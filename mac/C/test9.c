#include <stdio.h>

main()
{
	struct Data {
		int 	a;
		int	b;
	} y[4] = { 1, 10, 3, 30, 2, 20, 4, 40};

	struct Data *x = y;
	int i;

	for(i=0; i<4; i++) {
		x->a = x->b, ++x++->b;  
		printf("%d %d\t", y[i].a, y[i].b); 
	}
}
