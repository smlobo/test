// Stack and heap addresses
#include <stdio.h>
#include <malloc.h>

int main() {
	int i = 10;
	int *ip = &i;
	printf("i addr = %#x\n", ip);

	int j = 11;
	int *jp = &j;
	printf("j addr = %#x\n", jp);

	int k = 10;
	int *kp = &k;
	printf("k addr = %#x\n", kp);

	int *x = (int *) malloc(sizeof(int));
	printf("x addr = %#x\n", x);

	int *y = (int *) malloc(sizeof(int));
	printf("y addr = %#x\n", y);

	int *z = (int *) malloc(sizeof(int));
	printf("z addr = %#x\n", z);

	return 0;
}
