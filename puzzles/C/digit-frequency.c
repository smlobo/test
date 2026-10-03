/*

Given a string, s, consisting of alphabets and digits, find the frequency of 
each digit in the given string.

Input Format

The first line contains a string.

Constraints
* 1 <= len(s) <= 1000
* All the elements of num are made of english alphabets and digits.

Output Format

Print ten space-separated integers in a single line denoting the frequency of 
each digit from 0 to 9.

Sample Input 0
a11472o5t6

Sample Output 0
0 2 1 0 1 1 1 1 0 0 

*/

#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>

int main() {

	/* Enter your code here. Read input from STDIN. Print output to STDOUT */
	int freq[10];
	for (int i = 0; i < 10; i++)
		freq[i] = 0;

	char num[1000];
	int count = 0;
	do {
		scanf("%c", &num[count]);
		int index = num[count] - '0';
		if (index >= 0 && index <= 9)
			freq[index]++;
		count++;
	//} while (num[count-1] != '\0');
	} while (num[count-1] != '\n');
	for (int i = 0; i < count-1; i++)
		printf("%c.", num[i]);
	printf("\n");
	for (int i = 0; i < 10; i++)
		printf("%d ", freq[i]);
	printf("\n");

	return 0;
}
