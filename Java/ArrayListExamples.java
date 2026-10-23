import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/** Run with: javac ArrayListExamples.java && java ArrayListExamples (Java 21+). */
public class ArrayListExamples {
    private static void show(String label, Object value) {
        System.out.println(label + ": " + value);
    }

    public static void main(String[] args) {
        // Constructors: empty, initial capacity, and a copy of another collection.
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> preallocated = new ArrayList<>(20);
        ArrayList<String> copy = new ArrayList<>(List.of("one", "two"));
        show("empty / capacity / copied", words + " / " + preallocated + " / " + copy);

        // Add one item, insert at an index, and add a whole collection.
        show("add returned", words.add("pear"));
        words.add("apple");
        words.add(1, "banana");
        words.addAll(List.of("kiwi", "apple"));
        words.addAll(2, List.of("plum", "fig"));
        show("after adds", words);

        // Index access and replacement. set returns the previous element.
        show("get(0)", words.get(0));
        show("set(0, ...), previous", words.set(0, "orange"));
        show("size / isEmpty", words.size() + " / " + words.isEmpty());
        show("contains / indexOf / lastIndexOf apple",
                words.contains("apple") + " / " + words.indexOf("apple")
                        + " / " + words.lastIndexOf("apple"));
        show("containsAll", words.containsAll(List.of("fig", "kiwi")));

        // Java 21 SequencedCollection methods. These operate at the ends.
        words.addFirst("first");
        words.addLast("last");
        show("first / last", words.getFirst() + " / " + words.getLast());
        show("removeFirst / removeLast", words.removeFirst() + " / " + words.removeLast());
        List<String> reversed = words.reversed(); // A view, not a copy.
        show("reversed view", reversed);
        reversed.set(0, "changed through reversed view");
        show("original after reversed.set(0, ...)", words);

        // remove(int) removes by index; remove(Object) removes the first equal value.
        show("remove(0), removed", words.remove(0));
        show("remove((Object) \"apple\"), success", words.remove((Object) "apple"));
        show("after removals", words);
        show("removeIf(length == 4), changed", words.removeIf(s -> s.length() == 4));
        show("after removeIf", words);

        // These Collection methods work in terms of equals(), not a comparator.
        ArrayList<String> group = new ArrayList<>(List.of("a", "b", "c", "b"));
        group.removeAll(List.of("b"));
        show("removeAll(b)", group);
        group.addAll(List.of("d", "e"));
        group.retainAll(List.of("a", "e"));
        show("retainAll(a, e)", group);

        // subList(from, to) is a backed view; to is exclusive.
        ArrayList<Integer> numbers = new ArrayList<>(List.of(9, 4, 7, 2, 5));
        List<Integer> middle = numbers.subList(1, 4);
        show("subList(1, 4)", middle);
        middle.set(0, 40);
        middle.clear(); // Removes that range from numbers as well.
        show("original after subList edits", numbers);

        // List.sort is stable. Comparator.naturalOrder() gives ascending order.
        ArrayList<Integer> scores = new ArrayList<>(List.of(30, 10, 20, 10));
        scores.sort(Integer::compare);
        show("sorted ascending", scores);
        show("binarySearch(20)", Collections.binarySearch(scores, 20));
        scores.sort(Collections.reverseOrder());
        show("sorted descending", scores);
        scores.replaceAll(n -> n + 1);
        show("replaceAll(+1)", scores);

        // Three common ways to iterate. Use Iterator.remove() during iteration.
        System.out.print("for-each: ");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        scores.forEach(n -> System.out.print(n + " "));
        System.out.println(" <- forEach");
        Iterator<Integer> iterator = scores.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 11) {
                iterator.remove();
            }
        }
        show("after Iterator.remove", scores);

        // ListIterator can traverse backward and modify the list safely.
        ListIterator<Integer> cursor = scores.listIterator(scores.size());
        if (cursor.hasPrevious()) {
            int old = cursor.previous();
            cursor.set(old * 10);
            cursor.add(99);
        }
        show("after ListIterator.set/add", scores);

        // Conversion to arrays, streams, and a Spliterator.
        Object[] objects = scores.toArray();
        Integer[] integers = scores.toArray(new Integer[0]);
        show("Object[]", Arrays.toString(objects));
        show("Integer[]", Arrays.toString(integers));
        show("stream sum", scores.stream().mapToInt(Integer::intValue).sum());
        System.out.print("spliterator: ");
        scores.spliterator().forEachRemaining(n -> System.out.print(n + " "));
        System.out.println();

        // List equality uses elements and order; clone makes a shallow copy.
        ArrayList<Integer> same = new ArrayList<>(scores);
        show("equals / hashCode", scores.equals(same) + " / "
                + (scores.hashCode() == same.hashCode()));
        @SuppressWarnings("unchecked")
        ArrayList<Integer> shallowClone = (ArrayList<Integer>) scores.clone();
        show("clone equals original", shallowClone.equals(scores));

        // ArrayList accepts null. Capacity is internal and is not size.
        ArrayList<String> nullable = new ArrayList<>();
        nullable.add(null);
        show("contains(null)", nullable.contains(null));
        nullable.ensureCapacity(100); // Reserve space; size stays 1.
        show("size after ensureCapacity(100)", nullable.size());
        nullable.trimToSize(); // Reduce internal storage to current size.
        nullable.clear();
        show("after clear: size / isEmpty", nullable.size() + " / " + nullable.isEmpty());

        // Integer overload trap: remove(int index) versus remove(Object value).
        ArrayList<Integer> values = new ArrayList<>(List.of(10, 20, 30));
        show("remove(1), removed index 1", values.remove(1));
        show("remove(Integer.valueOf(30)), removed value", values.remove(Integer.valueOf(30)));
        show("remaining integers", values);
    }
}
