#import <Foundation/Foundation.h>

int main() {
    int integerArray[4] = { 2, 3, 5, 7 };
    int *arrayPtr = integerArray;
    while (arrayPtr <= &integerArray[3]) {
        NSLog(@"[%p] %d", arrayPtr, *arrayPtr);
        arrayPtr++;
    }

    return 0;
}