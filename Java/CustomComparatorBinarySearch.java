import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Run with: javac CustomComparatorBinarySearch.java && java CustomComparatorBinarySearch */
public class CustomComparatorBinarySearch {
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
        List<Candidate> candidates = new ArrayList<>(List.of(
                new Candidate(3, "Mia", 90),
                new Candidate(1, "Ana", 95),
                new Candidate(4, "Ana", 90),
                new Candidate(2, "Lee", 80),
                new Candidate(5, "Ana", 90)));

        Comparator<Candidate> order = new CandidateComparator();
        candidates.sort(order);
        System.out.println("Sorted (score descending, name and ID ascending):");
        candidates.forEach(System.out::println);

        // Binary search requires the list to be sorted with this same ordering.
        Candidate target = new Candidate(4, "Ana", 90);
        int index = Collections.binarySearch(candidates, target, order);
        System.out.println("\nFound index: " + index);
        if (index >= 0) {
            System.out.println("Found value: " + candidates.get(index));
        }

        Candidate missing = new Candidate(6, "Bob", 90);
        int result = Collections.binarySearch(candidates, missing, order);
        // If absent, result is -(insertion point) - 1.
        int insertionPoint = -result - 1;
        System.out.println("\nMissing search result: " + result);
        System.out.println("Insertion point: " + insertionPoint);
        candidates.add(insertionPoint, missing);
        System.out.println("After inserting at that point: " + candidates);
    }
}
