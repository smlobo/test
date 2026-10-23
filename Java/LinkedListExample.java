import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/** Run with: javac LinkedListExample.java && java LinkedListExample */
public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>(List.of("Ana", "Bob", "Cara"));
        names.addFirst("Start");
        names.addLast("End");
        System.out.println("List: " + names);
        System.out.println("First / last: " + names.getFirst() + " / " + names.getLast());

        // Iterate forward. Use Iterator.remove() to remove during iteration.
        System.out.print("Forward: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            if (it.next().equals("Bob")) {
                it.remove();
            }
        }
        System.out.println("After removing Bob: " + names);

        // ListIterator can insert at its current position.
        ListIterator<String> cursor = names.listIterator();
        while (cursor.hasNext()) {
            if (cursor.next().equals("Ana")) {
                cursor.add("Bea"); // Insert immediately after Ana.
                break;
            }
        }
        System.out.println("After inserting Bea: " + names);

        // Descending iteration leaves the list unchanged.
        System.out.print("Backward: ");
        Iterator<String> backward = names.descendingIterator();
        while (backward.hasNext()) {
            System.out.print(backward.next() + " ");
        }
        System.out.println();

        // Reverse the actual list in place.
        Collections.reverse(names);
        System.out.println("After Collections.reverse: " + names);

        // Java has no LinkedList.splice(). Move a range by copying references,
        // removing that range from the source, and inserting into the destination.
        LinkedList<String> source = new LinkedList<>(List.of("A", "B", "C", "D"));
        LinkedList<String> destination = new LinkedList<>(List.of("X", "Y"));
        List<String> moved = new ArrayList<>(source.subList(1, 3)); // B, C
        source.subList(1, 3).clear();
        destination.addAll(1, moved);
        System.out.println("Source after range move: " + source);
        System.out.println("Destination after range move: " + destination);

        // LinkedList also implements Deque, so it can be used as a queue.
        LinkedList<String> queue = new LinkedList<>();
        queue.offerLast("first");
        queue.offerLast("second");
        System.out.println("Queue front: " + queue.peekFirst());
        System.out.println("Removed from front: " + queue.pollFirst());
        System.out.println("Queue now: " + queue);
    }
}
