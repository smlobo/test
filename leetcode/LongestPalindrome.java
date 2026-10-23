import java.util.ArrayList;

public class LongestPalindrome {

    static String getPalindrome(String s, int start, int size) {
        // 2 indexes; stop when cross
        int begin = start;
        int end = start + size - 1;
        // System.out.println("\t" + begin + "," + end);
        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end)) {
                return null;
            }
            ++begin;
            --end;
        }

        // String x = s.substring(start, start + size);
        // System.out.println("\tFound: " + x);
        return s.substring(start, start + size);
    }

    static String longestPalindrome(String s) {
        // start with the longest possible palindrome
        // return when any palindrome is found
        for (int i = s.length(); i >= 2; --i) {
            // max possible palindromes for this length
            int maxP = s.length() - i + 1;
            // index of starting palindrome
            for (int j = 0; j < maxP; j++) {
                // System.out.println("check: " + i + "," + j);
                String palindrome = getPalindrome(s, j, i);
                if (palindrome != null) {
                    return palindrome;
                }
            }
        }

        // min length is 1
        return s.substring(0, 1);
    }

    public static void main(String[] args) {
        String s = "babad";
        String r = longestPalindrome(s);
        System.out.println(s + " = " + r);

        s = "cbbd";
        r = longestPalindrome(s);
        System.out.println(s + " = " + r);

        s = "abcd";
        r = longestPalindrome(s);
        System.out.println(s + " = " + r);

        s = "abcddcba";
        r = longestPalindrome(s);
        System.out.println(s + " = " + r);

        s = "abcdcba";
        r = longestPalindrome(s);
        System.out.println(s + " = " + r);
    }
}