import java.util.*;

public class Mock20220222 {

    public static List<String> commonChars(String[] words) {
        ArrayList<String> answer = new ArrayList<>();

        if (words.length == 0)
            return answer;

        HashMap<String, Integer> previous = new HashMap<>();
        HashMap<String, Integer> current = new HashMap<>();
        
        // Fill the previous hashmap
        String word = words[0];
        for (int i = 0; i < word.length(); i++) {
            String s = word.substring(i, i+1);
            previous.put(s, previous.getOrDefault(s, 0) + 1);
        }

        // Iterate over the remaining strings
        for (int i = 1; i < words.length; i++) {
            word = words[i];
            for (int j = 0; j < word.length(); j++) {
                String s = word.substring(j, j+1);

                // Is in previous hash set
                int previousCount = previous.getOrDefault(s, 0);
                if (previousCount > 0) {
                    current.put(s, current.getOrDefault(s, 0) + 1);
                    previous.put(s, previousCount - 1);
                }
            }
            previous = current;
            current = new HashMap<>();
            System.out.println(previous);
        }

        // Convert to ArrayList
        for (Map.Entry<String, Integer> entry : previous.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                answer.add(entry.getKey());
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] words;

        // Test1
        words = new String[] {"bella", "label", "roller"};
        List<String> answer = commonChars(words);
        System.out.println(Arrays.toString(answer.toArray()));

        // Test2
        words = new String[] {"cool", "lock", "cook"};
        answer = commonChars(words);
        System.out.println(Arrays.toString(answer.toArray()));
    }
}