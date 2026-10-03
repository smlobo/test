#include <stdio.h>
#include <strings.h>
#include <stdlib.h>

/* 
Given two strings s and part, perform the following operation on s until all 
occurrences of the substring part are removed:

Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.

A substring is a contiguous sequence of characters in a string.
*/

int match(char *s, char *part, int index, int sLength) {
    int pLength = strlen(part);
    int origIndex = index;

    // Check for match
    for (int i = 0; i < pLength; i++) {
        // Over string bounds
        if (index >= sLength)
            return 0;

        // Blanked out portion
        if (*(s+index) == 'X') {
            i--;
            index++;
            continue;
        }

        // Compare - no match
        if (*(s+index) != *(part+i)) {
            return 0;
        }

        index++;
    }

    // Matches - blank out
    index = origIndex;
    for (int i = 0; i < pLength; i++) {
        // Blanked out portion
        if (*(s+index) == 'X') {
            i--;
            index++;
            continue;
        }

        *(s+index) = 'X';
        index++;
    }

    return 1;
}

char *removeOccurrences(char * s, char * part) {
    int sLength = strlen(s);
    int pLength = strlen(part);

    // My copy
    char *x = (char *) malloc(sLength+1);
    strcpy(x, s);

    // Blank out matching chars
    int i = 0;
    while (i <= (sLength-pLength)) {
        if (match(x, part, i, sLength)) {
            // i -= (pLength-1);
            // if (i < 0)
                i = 0;
        }
        else {
            i++;
        }
    }

    // Concatenate
    // int j = 0;
    // for (int i = 0; i < sLength; i++) {
    //     if (*(x+i) != 'X') {
    //         *(x+j) = *(x+i);
    //         j++;
    //     }
    // }
    // *(x+j) = '\0';

    return x;
}

int main() {
    // test 0
    // char *i1 = "abbccd";
    // char *p1 = "bc";
    // printf("%s - %s = ", i1, p1);
    // printf("%s\n", removeOccurrences(i1, p1));

    // test 1
    // char *i1 = "daabcbaabcbc";
    // char *p1 = "abc";
    // printf("%s - %s = ", i1, p1);
    // printf("%s\n", removeOccurrences(i1, p1));

    // test 2
    // char *i1 = "axxxxyyyyb";
    // char *p1 = "xy";
    // printf("%s - %s = ", i1, p1);
    // printf("%s\n", removeOccurrences(i1, p1));

    // test 3
    // char *i1 = "eemckxmckx";
    // char *p1 = "emckx";
    // printf("%s - %s = ", i1, p1);
    // printf("%s\n", removeOccurrences(i1, p1));

    // test 4
    char *i1 = "ccctltctlltlb";
    char *p1 = "ctl";
    printf("%s - %s = ", i1, p1);
    printf("%s\n", removeOccurrences(i1, p1));
}