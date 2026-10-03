#include <stdio.h>
#include <stdbool.h>

bool unknownBoolean();

#define GENERATE_FUNCTION(name, num) \
    bool name ## num () { \
        if (!unknownBoolean()) { \
            return false; \
        } \
        return true;\
    }\

GENERATE_FUNCTION(callerChecked, 1)
GENERATE_FUNCTION(callerChecked, 2)
GENERATE_FUNCTION(callerChecked, 3)
GENERATE_FUNCTION(callerChecked, 4)
GENERATE_FUNCTION(callerChecked, 5)
GENERATE_FUNCTION(callerChecked, 6)
GENERATE_FUNCTION(callerChecked, 7)
GENERATE_FUNCTION(callerChecked, 8)
GENERATE_FUNCTION(callerChecked, 9)
GENERATE_FUNCTION(callerChecked, 10)
GENERATE_FUNCTION(callerChecked, 11)
GENERATE_FUNCTION(callerChecked, 12)
GENERATE_FUNCTION(callerChecked, 13)
GENERATE_FUNCTION(callerChecked, 14)
GENERATE_FUNCTION(callerChecked, 15)
GENERATE_FUNCTION(callerChecked, 16)
GENERATE_FUNCTION(callerChecked, 17)
GENERATE_FUNCTION(callerChecked, 18)
GENERATE_FUNCTION(callerChecked, 19)
GENERATE_FUNCTION(callerChecked, 20)

bool xxx() {
    return unknownBoolean();
}
