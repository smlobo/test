import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/** Run with: javac CustomComparatorTreeSet.java && java CustomComparatorTreeSet */
public class CustomComparatorTreeSet {
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
        TreeSet<Candidate> candidates = new TreeSet<>(order);
        candidates.addAll(List.of(
                new Candidate(3, "Mia", 90),
                new Candidate(1, "Ana", 95),
                new Candidate(4, "Ana", 90),
                new Candidate(2, "Lee", 80),
                new Candidate(5, "Ana", 90)));

        System.out.println("TreeSet iteration (score descending, name and ID ascending):");
        candidates.forEach(System.out::println);
        System.out.println("First: " + candidates.first());
        System.out.println("Last:  " + candidates.last());

        Candidate target = new Candidate(4, "Ana", 90);
        System.out.println("\nContains target: " + candidates.contains(target));
        System.out.println("Add target again: " + candidates.add(target)); // false

        // No binarySearch is needed: TreeSet has navigation methods.
        Candidate missing = new Candidate(6, "Bob", 90);
        System.out.println("\nContains missing: " + candidates.contains(missing));
        System.out.println("lower(missing):   " + candidates.lower(missing));   // <
        System.out.println("floor(missing):   " + candidates.floor(missing));   // <=
        System.out.println("ceiling(missing): " + candidates.ceiling(missing)); // >=
        System.out.println("higher(missing):  " + candidates.higher(missing));  // >
        candidates.add(missing);
        System.out.println("After adding missing: " + candidates);
        System.out.println("Descending view: " + candidates.descendingSet());

        // TreeSet treats compare(a, b) == 0 as a duplicate, even if equals() is false.
        TreeSet<Candidate> byScoreOnly =
                new TreeSet<>(Comparator.comparingInt(Candidate::score));
        System.out.println("\nAdd Mia/90 by score: "
                + byScoreOnly.add(new Candidate(3, "Mia", 90)));
        System.out.println("Add Ana/90 by score: "
                + byScoreOnly.add(new Candidate(4, "Ana", 90))); // false
        System.out.println("By score only: " + byScoreOnly);
    }
}
