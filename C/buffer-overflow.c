#include <stdlib.h>

void buffer_overflow() {
	char* x = malloc(10);
	// for (int i = 0; i < 10; i++)
	for (int i = 0; i <= 10; i++)
		x[i] = 'a';
	free(x);
}

int main() {
	buffer_overflow();
}