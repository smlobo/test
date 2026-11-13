import java.util.Calendar;
import java.util.GregorianCalendar;

public class Queue<T> {
    class Node<T> {
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    Node<T> head;
    Node<T> tail;

    // Add to tail
    public void enqueue(T data) {
        // Create a new Node
        Node<T> node = new Node<>(data);

        if (head == null) {
            head = node;
            tail = node;
        }
        else {
            tail.next = node;
            tail = node;
        }
    }

    // Remove from head
    public T dequeue() {
        T returnValue = null;
        if (head != null) {
            returnValue = head.data;
            head = head.next;
        }
        return returnValue;
    }

    public static void main(String[] args) {
        Queue<Calendar> queue = new Queue<>();

        GregorianCalendar gc;

        gc = new GregorianCalendar();
        gc.set(2010, 0, 1);
        queue.enqueue(gc);
        System.out.println("Enqueue: " + gc.get(Calendar.YEAR));
        gc = new GregorianCalendar();
        gc.set(2011, 0, 1);
        queue.enqueue(gc);
        System.out.println("Enqueue: " + gc.get(Calendar.YEAR));
        gc = new GregorianCalendar();
        gc.set(2012, 0, 1);
        queue.enqueue(gc);
        System.out.println("Enqueue: " + gc.get(Calendar.YEAR));

        System.out.println("Dequeue: " + queue.dequeue().get(Calendar.YEAR));
        System.out.println("Dequeue: " + queue.dequeue().get(Calendar.YEAR));
        System.out.println("Dequeue: " + queue.dequeue().get(Calendar.YEAR));
        System.out.println("Dequeue: " + queue.dequeue());
    }
}