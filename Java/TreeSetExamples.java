import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/** Run with: javac TreeSetExamples.java && java TreeSetExamples (Java 21+). */
public class TreeSetExamples {
    private record Player(String name, int score) {}

    private static void show(String label, Object value) {
        System.out.println(label + ": " + value);
    }

    public static void main(String[] args) {
        // Natural ordering: integers are kept sorted, with no duplicates.
        TreeSet<Integer> numbers = new TreeSet<>();
        show("add(20)", numbers.add(20));
        numbers.addAll(List.of(40, 10, 30, 50));
        show("add duplicate 20", numbers.add(20));
        show("sorted set", numbers);
        show("size / isEmpty", numbers.size() + " / " + numbers.isEmpty());
        show("contains(30) / containsAll(10, 50)",
                numbers.contains(30) + " / " + numbers.containsAll(List.of(10, 50)));

        // first() and last() throw if the set is empty.
        show("first / last", numbers.first() + " / " + numbers.last());

        // Each navigation method returns null if there is no matching element.
        show("lower(30)   < 30", numbers.lower(30));
        show("floor(30)  <= 30", numbers.floor(30));
        show("ceiling(30) >= 30", numbers.ceiling(30));
        show("higher(30)   > 30", numbers.higher(30));
        show("lower(10) has no match", numbers.lower(10));

        // Range views are backed by numbers; bounds with booleans are explicit.
        NavigableSet<Integer> middle = numbers.subSet(20, true, 40, false); // [20, 40)
        show("subSet [20, 40)", middle);
        middle.remove(30);
        show("original after middle.remove(30)", numbers);
        show("headSet(< 40)", numbers.headSet(40));
        show("tailSet(>= 20)", numbers.tailSet(20));
        show("headSet(<= 40)", numbers.headSet(40, true));
        show("tailSet(> 20)", numbers.tailSet(20, false));

        // descendingSet() is also a backed view, not a copy.
        NavigableSet<Integer> descending = numbers.descendingSet();
        show("descendingSet", descending);
        show("descending.first() is original maximum", descending.first());
        descending.remove(40);
        show("original after descending.remove(40)", numbers);

        System.out.print("ascending iteration: ");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.print("descendingIterator: ");
        Iterator<Integer> reverseIterator = numbers.descendingIterator();
        while (reverseIterator.hasNext()) {
            System.out.print(reverseIterator.next() + " ");
        }
        System.out.println();

        show("remove(20)", numbers.remove(20));
        show("pollFirst()", numbers.pollFirst()); // Remove and return smallest, or null.
        show("pollLast()", numbers.pollLast());   // Remove and return largest, or null.
        show("pollFirst() on empty set", numbers.pollFirst());

        // A comparator supplies the ordering when there is no natural ordering.
        Comparator<Player> ranking = Comparator.comparingInt(Player::score)
                .reversed()                  // Higher score first.
                .thenComparing(Player::name); // Tie-break by name ascending.
        TreeSet<Player> leaderboard = new TreeSet<>(ranking);
        leaderboard.add(new Player("Mia", 90));
        leaderboard.add(new Player("Ana", 90));
        leaderboard.add(new Player("Lee", 80));
        show("leaderboard", leaderboard);
        show("leaderboard comparator", leaderboard.comparator() == ranking);
        show("add duplicate Ana/90", leaderboard.add(new Player("Ana", 90)));

        // A TreeSet treats compare(a, b) == 0 as a duplicate, regardless of equals().
        TreeSet<Player> byScoreOnly = new TreeSet<>(Comparator.comparingInt(Player::score));
        show("byScoreOnly add Mia/90", byScoreOnly.add(new Player("Mia", 90)));
        show("byScoreOnly add Ana/90", byScoreOnly.add(new Player("Ana", 90)));
        show("byScoreOnly contents", byScoreOnly);

        // Collection operations, conversion, and creating an independent copy.
        TreeSet<Integer> extras = new TreeSet<>(List.of(1, 2, 3, 4, 5));
        extras.removeIf(n -> n % 2 == 0);
        show("removeIf(even)", extras);
        extras.retainAll(List.of(1, 5));
        show("retainAll(1, 5)", extras);
        extras.addAll(List.of(3, 7));
        extras.removeAll(List.of(1, 7));
        show("removeAll(1, 7)", extras);
        show("toArray", Arrays.toString(extras.toArray(new Integer[0])));
        show("stream sum", extras.stream().mapToInt(Integer::intValue).sum());
        TreeSet<Integer> independentCopy = new TreeSet<>(extras);
        extras.clear();
        show("after clear / copy unaffected", extras + " / " + independentCopy);
    }
}
