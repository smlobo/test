/* 
Length of the longest substring without repeated characters

Input: s = "abcabcbb"
Output: 3

Input: s = "bbbbb"
Output: 1

Input: s = "pwwkew"
Output: 3

Input: s = ""
Output: 0
*/

import java.util.Arrays;

public class SubstringNoRepeats {
    /*public int lengthOfLongestSubstringImproved(String s) {
        int[] occurs = new int[256];
        Arrays.fill(occurs, -1);
        int longest = 0;
        int lastRepeatIndex = -1;

        // Start at index 0, and so on
        for (int i = 0; i < s.length(); i++) {
            // Calculate the substring length since the last occurence of this 
            // char
            //if (occurs[(int) s.charAt(i)] > lastRepeatIndex) {
                int currentLongest = i - occurs[(int) s.charAt(i)];
                System.out.println("[" + i + "] " + s.charAt(i) + " -> " + 
                    occurs[(int) s.charAt(i)]);
                longest = Math.max(longest, currentLongest);
                lastRepeatIndex = occurs[(int) s.charAt(i)];
            //}
            
            // Record the index of this char
            occurs[(int) s.charAt(i)] = i;
        }

        return longest;
    }*/

    public int lengthOfLongestSubstring(String s) {
        boolean[] occurs = new boolean[256];
        int longest = 0;

        // Start at index 0, and so on
        for (int i = 0; i < s.length(); i++) {
            int j;
            for (j = i; j < s.length(); j++) {
                if (occurs[(int) s.charAt(j)])
                    break;
                occurs[(int) s.charAt(j)] = true;
            }

            int currentLongest = j - i;
            longest = Math.max(longest, currentLongest);

            // Reset 'occurs' array
            //for (j = 0; j < occurs.length; j++)
            //    occurs[j] = false;
            Arrays.fill(occurs, false);
        }

        return longest;
    }

    public static void main(String[] args) {
        SubstringNoRepeats ssnr = new SubstringNoRepeats();
        String s;
        int r;

        s = "abcabcbb";
        r = ssnr.lengthOfLongestSubstring(s);
        //r = ssnr.lengthOfLongestSubstringImproved(s);
        System.out.println(s + " longest no repeat subsring: " + r);
        assert(r == 3);

        s = "bbbbb";
        r = ssnr.lengthOfLongestSubstring(s);
        //r = ssnr.lengthOfLongestSubstringImproved(s);
        System.out.println(s + " longest no repeat subsring: " + r);
        assert(r == 1);

        s = "pwwkew";
        r = ssnr.lengthOfLongestSubstring(s);
        //r = ssnr.lengthOfLongestSubstringImproved(s);
        System.out.println(s + " longest no repeat subsring: " + r);
        assert(r == 3);

        s = "";
        r = ssnr.lengthOfLongestSubstring(s);
        //r = ssnr.lengthOfLongestSubstringImproved(s);
        System.out.println(s + " longest no repeat subsring: " + r);
        assert(r == 0);

        s = " ";
        r = ssnr.lengthOfLongestSubstring(s);
        //r = ssnr.lengthOfLongestSubstringImproved(s);
        System.out.println(s + " longest no repeat subsring: " + r);
        assert(r == 1);
    }
}