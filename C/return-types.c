#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>

struct S {
    int x;
    float y;
    char z[3];
};

uint8_t maxu1byte() {
    return ~(uint8_t) 0;
}

unsigned short maxu2byte() {
    return ~(uint16_t) 0;
}

struct S structS() {
    struct S s = {
        10,
        3.1415,
        {'a', 'b', 'c'}
    };
    printf("structS(): &s = %p\n", &s);
    return s;
}

int main() {
    printf("max1byte: u %d/%#x; s %d\n", maxu1byte(), maxu1byte(), (int8_t) maxu1byte());
    printf("max2byte: u %d/%#x; s %d\n", maxu2byte(), maxu2byte(), (int16_t) maxu2byte());
    struct S ss = structS();
    printf("main(): &ss = %p, ss = {%d, %.4f, {%c, %c, %c}}\n", &ss, ss.x,
        ss.y, ss.z[0], ss.z[1], ss.z[2]);
    return 0;
}