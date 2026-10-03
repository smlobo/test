// Reverse words in a sentence in the same area
// reverse the whole sentence, then reverse individual words
#include <stdio.h>
#include <string.h>

char thestr[100];

void myreverse(char *begin, char *end) {
	while (begin < end) {
		char t = *begin;
		*begin = *end;
		*end = t;
		begin++;
		end--;
	}
}

int main() {
	gets(thestr);
	printf("step 0 = %s\n", thestr);

	// reverse the whole string
	myreverse(thestr, thestr+strlen(thestr)-1);

	printf("step 1 = %s\n", thestr);

	// reverse each word
	char *wordstart = thestr;
	for (int i=0; i<strlen(thestr); i++) {
		if (thestr[i] == ' ') {
			myreverse(wordstart, thestr+i-1);
			wordstart = thestr + i + 1;
		}
	}

	// reverse the last word
	myreverse(wordstart, thestr+strlen(thestr)-1);

	printf("Reversed = %s\n", thestr);
	return 0;
}