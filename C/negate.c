#include <stdio.h>
#include <stdint.h>

int main() {
	uint8_t x = 0xff;
	uint8_t nX = ~x;
	uint8_t dNX = nX - 1;
	printf("[uint8_t] x = %#x [%d] [%u], ~x = %#x, ~x - 1 = %#x [%d] [%u]\n", 
		x, x, x, nX, dNX, dNX, dNX);

	x = 0;
	nX = ~x;
	dNX = nX - 1;
	printf("[uint8_t] x = %#x [%d] [%u], ~x = %#x, ~x - 1 = %#x [%d] [%u]\n", 
		x, x, x, nX, dNX, dNX, dNX);

	int8_t y = 0xff;
	int8_t nY = ~y;
	int8_t dNY = nY - 1;
	printf("[int8_t] y = %#x [%d] [%u], ~y = %#x, ~y - 1 = %#x [%d] [%u]\n", 
		y, y, y, nY, dNY, dNY, dNY);

	y = 0x7f;
	nY = ~y;
	dNY = nY - 1;
	printf("[int8_t] y = %#x [%d] [%u], ~y = %#x [%d] [%u], ~y-1 = %#x [%d] [%u]\n", 
		y, y, y, nY, nY, nY, dNY, dNY, dNY);

	y = 0x7f;
	int8_t mY = -y;
	int8_t dMY = mY - 1;
	printf("[int8_t] y = %#x [%d] [%u], -y = %#x [%d] [%u], -y-1 = %#x [%d] [%u]\n", 
		y, y, y, mY, mY, mY, dMY, dMY, dMY);
}