import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** Run with: javac CustomComparatorTreeMap.java && java CustomComparatorTreeMap */
public class CustomComparatorTreeMap {
    private record Candidate(int id, String name, int score) {}

    // Higher score first; break ties by name, then by unique ID.
    private static class CandidateComparator implements Comparator<Candidate> {
        @Override
        public int compare(Candidate a, Candidate b) {
            int byScore = Integer.compare(b.score(), a.score());
            if (byScore != 0) return byScore;

            int byName = a.name().compareTo(b.name());
            if (byName != 0) return byName;

            return Integer.compare(a.id(), b.id());
        }
    }

    public static void main(String[] args) {
        Comparator<Candidate> order = new CandidateComparator();
        TreeMap<Candidate, String> decisions = new TreeMap<>(order);
        decisions.put(new Candidate(3, "Mia", 90), "waitlist");
        decisions.put(new Candidate(1, "Ana", 95), "accepted");
        decisions.put(new Candidate(4, "Ana", 90), "interview");
        decisions.put(new Candidate(2, "Lee", 80), "declined");
        decisions.put(new Candidate(5, "Ana", 90), "review");

        System.out.println("TreeMap entries (score descending, name and ID ascending):");
        for (Map.Entry<Candidate, String> entry : decisions.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("First entry: " + decisions.firstEntry());
        System.out.println("Last entry:  " + decisions.lastEntry());

        Candidate target = new Candidate(4, "Ana", 90);
        System.out.println("\nContains target key: " + decisions.containsKey(target));
        System.out.println("Value for target: " + decisions.get(target));
        System.out.println("put(target, accepted), previous value: "
                + decisions.put(target, "accepted"));
        System.out.println("New value for target: " + decisions.get(target));

        // Navigation compares keys using CandidateComparator. No match returns null.
        Candidate missing = new Candidate(6, "Bob", 90);
        System.out.println("\nContains missing key: " + decisions.containsKey(missing));
        System.out.println("lowerEntry(missing):   " + decisions.lowerEntry(missing));   // <
        System.out.println("floorEntry(missing):   " + decisions.floorEntry(missing));   // <=
        System.out.println("ceilingEntry(missing): " + decisions.ceilingEntry(missing)); // >=
        System.out.println("higherEntry(missing):  " + decisions.higherEntry(missing));  // >
        decisions.put(missing, "pending");
        System.out.println("After adding missing: " + decisions);
        System.out.println("Descending view: " + decisions.descendingMap());

        // TreeMap treats compare(a, b) == 0 as the same key and replaces its value.
        TreeMap<Candidate, String> byScoreOnly =
                new TreeMap<>(Comparator.comparingInt(Candidate::score));
        Candidate mia = new Candidate(3, "Mia", 90);
        Candidate ana = new Candidate(4, "Ana", 90);
        System.out.println("\nput Mia/90, previous: " + byScoreOnly.put(mia, "first"));
        System.out.println("put Ana/90, previous: " + byScoreOnly.put(ana, "second"));
        System.out.println("By score only: " + byScoreOnly);
        System.out.println("Size: " + byScoreOnly.size());
        System.out.println("get(Ana/90): " + byScoreOnly.get(ana));
        // The stored key is still Mia; only its associated value was replaced.
    }
}
