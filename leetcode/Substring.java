/* 
Check if a string is made up of repeated substrings:
ababab -> true
abcab -> false
*/

class Substring {
    // The substring has to start at the first index
    // Start with dividing the String in 2, then 3, etc
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        
        // Corner cases - string length 0, 1
        if (length == 0 || length == 1)
            return false;
        
        for (int i = 2; i <= length; i++) {
            // Can the string be divided exactly into 'i' subparts?
            if (length%i != 0)
                continue;
            
            int subLength = length/i;
            int index = subLength;
            String sample = s.substring(0, index);
            boolean found = true;
            for (int j = 1; j < i; j++) {
                if (!sample.equals(s.substring(index, index+subLength))) {
                    found = false;
                    break;
                }
                index += subLength;
            }
            
            // Found!
            if (found)
                return true;
        }
        
        // Tried all divisions of the string
        return false;
    }

    public static void main(String[] args) {
        Substring s = new Substring();
        String ss;
        boolean result;

        ss = "abcabcabcabcabc";
        result = s.repeatedSubstringPattern(ss);
        System.out.println(ss + " has repeated substrig? " + result);
        assert(result == true);
    }
}