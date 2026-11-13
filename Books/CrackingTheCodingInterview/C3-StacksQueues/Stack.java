import java.util.Calendar;
import java.util.GregorianCalendar;

public class Stack<T> {
    class Node<T> {
        Node<T> next;
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    Node<T> top;

    public void push(T value) {
        // Create a new Node
        Node<T> node = new Node<>(value);

        if (top == null) {
            top = node;
        }
        else {
            node.next = top;
            top = node;
        }
    }

    public T pop() {
        if (top == null)
            return null;

        T returnValue = top.data;
        top = top.next;
        return returnValue;
    }

    public T peek() {
        if (top == null)
            return null;
        return top.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node node = top;
        while (node != null) {
            sb.append(node.data);
            sb.append(", ");
            node = node.next;
        }

        return sb.toString();
    }
    
    public static void main(String[] args) {
        Stack<Calendar> stack = new Stack<>();

        GregorianCalendar gc;

        gc = new GregorianCalendar();
        gc.set(2010, 0, 1);
        stack.push(gc);
        System.out.println("Pushed: " + stack.peek().get(Calendar.YEAR));
        gc = new GregorianCalendar();
        gc.set(2011, 0, 1);
        stack.push(gc);
        System.out.println("Pushed: " + stack.peek().get(Calendar.YEAR));
        gc = new GregorianCalendar();
        gc.set(2012, 0, 1);
        stack.push(gc);
        System.out.println("Pushed: " + stack.peek().get(Calendar.YEAR));

        System.out.println("Popped: " + stack.pop().get(Calendar.YEAR));
        System.out.println("Popped: " + stack.pop().get(Calendar.YEAR));
        System.out.println("Popped: " + stack.pop().get(Calendar.YEAR));
        System.out.println("Popped: " + stack.pop());
    }
}