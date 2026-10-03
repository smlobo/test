/*
Given a string, your task is to count how many palindromic substrings in 
this string.

The substrings with different start indexes or end indexes are counted as 
different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

*/

class PalindromicSubstring {

	public int scanEvenPalindrome(String s, int index) {
		int answer = 0;

		int x = Math.min(index, s.length()-index-2);

		for (int i = 0; i <= x; i++) {
			if (s.charAt(index-i) != s.charAt(index+i+1)) {
				break;
			}
			answer++;
		}

		return answer;
	}

	public int scanOddPalindrome(String s, int index) {
		int answer = 0;

		int x = Math.min(index, s.length()-index-1);

		for (int i = 1; i <= x; i++) {
			if (s.charAt(index-i) != s.charAt(index+i)) {
				break;
			}
			answer++;
		}

		return answer;
	}

	public int countSubstrings(String s) {

		// Each letter is a palindrome
		int answer = s.length();

		// Scan for 2 of longer even number palindromes
		for (int i = 0; i < s.length()-1; i++) {
			answer += scanEvenPalindrome(s, i);
		}

		// Scan for 3 or longer odd number palindromes
		for (int i = 1; i < s.length() - 1; i++) {
			answer += scanOddPalindrome(s, i);
		}

		return answer;
	}

	public static void main(String[] args) {
		PalindromicSubstring s = new PalindromicSubstring();
		String t;
		int a;

		// test 1
		t = "abc";
		a = s.countSubstrings(t);
		System.out.println(t + " = " + a);
		assert a == 3;

		// test 2
		t = "aaa";
		a = s.countSubstrings(t);
		System.out.println(t + " = " + a);
		assert a == 6;

		// test 3
		t = "abaaba";
		a = s.countSubstrings(t);
		System.out.println(t + " = " + a);
		assert a == 11;

		// test 4
		t = "abcdedccc";
		a = s.countSubstrings(t);
		System.out.println(t + " = " + a);
		assert a == 14;

		// test 4
		t = "aaaaa";
		a = s.countSubstrings(t);
		System.out.println(t + " = " + a);
		assert a == 15;

	}
}