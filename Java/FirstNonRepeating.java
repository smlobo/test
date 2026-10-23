import java.util.HashMap;
import java.util.Map;

class FirstNonRepeating {

    static class CountIndex {
        int count;
        int index;
        CountIndex(int c, int i) {
            this.count = c;
            this.index = i;
        }
    }

    public static int firstNonRepeating(String x) {
        // hash map of char to its first index
        HashMap<Character, CountIndex> map = new HashMap<>();
        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(i);
            // duplicate
            if (map.containsKey(c)) {
                CountIndex ci = map.get(c);
                ci.count++;
            } else {
                CountIndex ci = new CountIndex(1, i);
                map.put(c, ci);
            }
        }

        // scan for lowest index
        int lowestIndex = x.length();
        for (Map.Entry<Character, CountIndex> entry : map.entrySet()) {
            CountIndex ci = entry.getValue();
            if (ci.count == 1) {
                lowestIndex = Math.min(lowestIndex, ci.index);
            }
        }

        return lowestIndex;
    }

    public static void main(String[] args) {
        String input = "abcac";
        int output = firstNonRepeating(input);
        System.out.println(input + " : " + output);

        input = "abcbc";
        output = firstNonRepeating(input);
        System.out.println(input + " : " + output);

        input = "abcabc";
        output = firstNonRepeating(input);
        System.out.println(input + " : " + output);

        input = "";
        output = firstNonRepeating(input);
        System.out.println(input + " : " + output);

        input = "aaab";
        output = firstNonRepeating(input);
        System.out.println(input + " : " + output);
    }
}