/*
Decode ways string of ints may decoded:
A -> 1, B -> 2, .... Z -> 26

Examples:
  12 -> AB & L -> 2
  226 -> BBF, VF, BZ -> 3
  0 -> 0
  1 -> 1
*/

public class DecodeWays {
    public int numDecodings(String s) {
        int count = 0;

        // String contains at least 1 valid encoding
        boolean valid = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                valid = true;
                break;
            }
        }

        // No valid encoding, count is 0
        if (!valid)
            return count;

        // Single digit encoding
        count++;

        // Last char cannot become 2 digit encoding - no need to check it
        for (int i = 0; i < s.length()-1; i++) {
            char current = s.charAt(i);
            char next = s.charAt(i+1);

            // 1 can be combined with any digit except 0
            if (current == '1' && !(next == '0'))
                count++;

            // 2 can be combined with any digit EXCEPT 7, 8, 9, 0 (z -> 26)
            else if (current == '2' && 
                    !(next == '7' || next == '8' || next == '9' || next == '0'))
                count++;
        }

        return count;
    }

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        String s;
        int r;

        s = "12";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 2);

        s = "226";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 3);

        s = "0";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 0);

        s = "1";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 1);

        s = "10";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 1);

        s = "1111";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 4);

        s = "1234";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 3);

        s = "27";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 1);

        s = "2101";
        r = d.numDecodings(s);
        System.out.println(s  + " -> " + r);
        assert(r == 1);
    }
}