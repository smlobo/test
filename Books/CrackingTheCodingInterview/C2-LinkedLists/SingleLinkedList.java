import java.util.Iterator;
import java.util.Date;

public class SingleLinkedList<T extends Comparable<T>> implements Iterable<T> {
    class Node<T extends Comparable<T>> {
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public void add(T value) {
        Node<T> node = new Node<>(value);

        // Corner case - list not initialized
        if (head == null) {
            head = node;
            tail = node;
        }

        else {
            tail.next = node;
            tail = node;
        }
    }

    public T head() {
        return head.data;
    }

    public T tail() {
        return tail.data;
    }

    public T delete(T value) {
        if (value == null)
            return null;

        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.compareTo(value) == 0) {
                // Corner case - 1st item deleted
                if (head == current) {
                    head = head.next;
                }
                // Normal case
                else {
                    previous.next = current.next;                    
                }
                // Corner case - last item deleted
                if (tail == current) {
                    tail = previous;
                }
                return value;
            }

            previous = current;
            current = current.next;
        }
        return null;
    }

    class SingleLinkedListIterator implements Iterator<T> {
        private Node<T> current;

        public SingleLinkedListIterator() {
            current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T retValue = current.data;
            current = current.next;
            return retValue;
        }

        public void remove() {}
    }

    public Iterator<T> iterator() {
        return new SingleLinkedListIterator();
    }

    public static void main(String[] args) {
        SingleLinkedList<Date> sll1 = new SingleLinkedList<>();
        sll1.add(new Date(101, 0, 1));
        sll1.add(new Date(102, 1, 2));
        sll1.add(new Date(103, 2, 3));
        sll1.add(new Date(104, 3, 4));

        System.out.println("Head: " + sll1.head());
        System.out.println("Tail: " + sll1.tail());

        Iterator<Date> iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }

        System.out.println("Delete: " + sll1.delete(new Date(103, 2, 3)));
        iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }

        System.out.println("Delete: " + sll1.delete(new Date(105, 4, 5)));
        iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }

        System.out.println("Delete: " + sll1.delete(new Date(101, 0, 1)));
        iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }

        System.out.println("Delete: " + sll1.delete(new Date(104, 3, 4)));
        iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }

        System.out.println("Delete: " + sll1.delete(new Date(102, 1, 2)));
        iterator = sll1.iterator();
        while (iterator.hasNext()) {
            System.out.println("  -> " + iterator.next());
        }
    }
}