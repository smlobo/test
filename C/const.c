#include <stdio.h>

int main() {
    int x = 10;
    int* xPtr = &x;
    x++;
    (*xPtr)++;
    printf("[%p] x = %d\n", &x, x);
    printf("[%p] xPtr = %p; *xPtr = %d\n", &xPtr, xPtr, *xPtr);
    
    // Pointer to a const int (pointed to cannot change)
    const int xConst = 100;
    // int* xConstPtr = &xConst;        // WARNING
    const int* xConstPtr = &xConst;
    // xConst++;                        // ERROR
    // (*xConstPtr)++;                  // ERROR
    printf("[%p] xConst = %d\n", &xConst, xConst);
    printf("[%p] xConstPtr = %p; *xConstPtr = %d\n", &xConstPtr, xConstPtr, 
        *xConstPtr);
    const int yConst = 200;
    xConstPtr = &yConst;
    printf("[%p] yConst = %d\n", &yConst, yConst);
    printf("[%p] xConstPtr = %p; *xConstPtr = %d\n", &xConstPtr, xConstPtr, 
        *xConstPtr);

    // Casting
    int* xConstPtrCast = (int*)&xConst;
    // ((int)xConst)++;                 // ERROR lvalue cast not supported
    (*xConstPtrCast)++;
    printf("[%p] xConst = %d\n", &xConst, xConst);
    printf("[%p] xConstPtrCast = %p; *xConstPtrCast = %d\n", &xConstPtrCast, 
        xConstPtrCast, *xConstPtrCast);

    // Const pointer to an int (pointer cannot change)
    // int* const xPtrConst = &xConst;  // WARNING
    int* const xPtrConst = &x;
    int y = 20;
    // xPtrConst = &y;                  // ERROR
    printf("[%p] xPtrConst = %p; *xPtrConst = %d\n", &xPtrConst, xPtrConst, 
        *xPtrConst);

    // Casting
    int** xPtrConstPtr = (int**)&xPtrConst;
    *xPtrConstPtr = &y;
    printf("[%p] y = %d\n", &y, y);
    printf("[%p] xPtrConst = %p; *xPtrConst = %d\n", &xPtrConst, xPtrConst, 
        *xPtrConst);
    printf("[%p] xPtrConstPtr = %p; *xPtrConstPtr = %p; **xPtrConstPtr = %d\n", 
        &xPtrConstPtr, xPtrConstPtr, *xPtrConstPtr, **xPtrConstPtr);
}