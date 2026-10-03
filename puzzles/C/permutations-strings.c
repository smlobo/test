/*

Given an array of strings sorted in lexicographical order, print all of its 
permutations in strict lexicographical order. If two permutations look the 
same, only print one of them.

For example, s = [ab, bc, cd]. The six permutations in correct order are:

ab bc cd
ab cd bc
bc ab cd
bc cd ab
cd ab bc
cd bc ab

*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static int compare_strings(const void *p, const void *q) {
	return strcmp(* (char * const *) p, * (char * const *) q);
}

static void swap(char **s, int x, int y) {
	char *temp = s[x];
	s[x] = s[y];
	s[y] = temp;
}

static void reverse(char **s, int start, int end) {
	int midpoint = (end - start + 1) / 2;
	for (int i = 0; i < midpoint; i++)
		swap(s, start++, end--);
}

int next_permutation(int n, char **s)
{
	/**
	* Complete this method
	* Return 0 when there is no next permutation and 1 otherwise
	* Modify array s to its next permutation
	*/

	// Reverse search for a greater to lower transition
	// ex: 1 3 2 0 - find the 3 -> 1 transition
	int lowTransition = -1;
	for (int i = n-2; i >= 0; i--) {
		if (strcmp(s[i], s[i+1]) < 0) {
			lowTransition = i;
			break;
		}
	}

	// Perfectly reverse sorted - we are done
	if (lowTransition == -1) {
		return 0;
	}

	// Search forward from the transition point for the next highest element
	int nextHighIndex = lowTransition + 1;
	for (int i = nextHighIndex+1; i < n; i++) {
		if (strcmp(s[lowTransition], s[i]) < 0 && 
			strcmp(s[i], s[nextHighIndex]) < 0)
			nextHighIndex = i;
	}

	// Swap the 2 - the lowTransition with next highest string
	//printf("swap: %d %d\n", lowTransition, nextHighIndex);
	swap(s, lowTransition, nextHighIndex);

	// Sort the remainder of the array
	//printf("qsort: %d count %d\n", lowTransition+1, n-(lowTransition+1));
	//qsort(s + lowTransition + 1, n - (lowTransition + 1), sizeof(char *), 
	//	compare_strings);
	// Reverse sorted - reverse to get back to sorted order
	reverse(s, lowTransition + 1, n - 1);

	return 1;
}

int main()
{
	char **s;
	int n;
	scanf("%d", &n);
	s = calloc(n, sizeof(char*));
	for (int i = 0; i < n; i++)
	{
		s[i] = calloc(11, sizeof(char));
		scanf("%s", s[i]);
	}
	int count = 0;
	do
	{
		for (int i = 0; i < n; i++)
			printf("%s%c", s[i], i == n - 1 ? '\n' : ' ');
	} while (next_permutation(n, s));
	for (int i = 0; i < n; i++)
		free(s[i]);
	free(s);
	return 0;
}
