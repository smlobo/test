#include <stdio.h>
#include <limits.h>
#include <stdint.h>

int main() {
	printf("CHAR_MIN = %d (%#x)\n", CHAR_MIN, (uint8_t) CHAR_MIN);
	printf("SCHAR_MIN = %d (%#x)\n", SCHAR_MIN, (uint8_t) SCHAR_MIN);
	printf("CHAR_MAX = %d (%#x)\n", CHAR_MAX, (uint8_t) CHAR_MAX);
	printf("SCHAR_MAX = %d (%#x)\n", SCHAR_MAX, (uint8_t) SCHAR_MAX);

	return 0;
}
