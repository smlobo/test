import java.util.HashSet;

public class CircularLinkedList<T> {
    private static class Node<T> {
        private Node<T> next;
        private T data;
        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;

    public static void main(String[] args) {
        // Generate a circular linked list
        CircularLinkedList<String> cll = new CircularLinkedList<>();

        // Array of Nodes
        Node<String>[] nodes = new Node[5];
        nodes[0] = new Node<String>("A");
        nodes[1] = new Node<String>("B");
        nodes[2] = new Node<String>("C");
        nodes[3] = new Node<String>("D");
        nodes[4] = new Node<String>("E");

        // Connect
        cll.head = nodes[0];
        nodes[0].next = nodes[1];
        nodes[1].next = nodes[2];
        nodes[2].next = nodes[3];
        nodes[3].next = nodes[4];

        Node circleHead = cll.findCircular();
        System.out.println("Head of circular (before): " + ((circleHead == null) ? 
            "<no-circle>" : circleHead.data));

        // Make circular E -> C
        nodes[4].next = nodes[2];

        circleHead = cll.findCircular();
        System.out.println("Head of circular (after): " + ((circleHead == null) ? 
            "<no-circle>" : circleHead.data));
    }

    public Node<T> findCircular() {
        // Iterate over the linked list, adding elements to a HashSet. If 
        // the element exists, that is the head of the circular list
        HashSet<Node<T>> hashSet = new HashSet<>();

        Node<T> current = head;
        while (current != null) {
            System.out.println(" -> " + current.data);
            if (!hashSet.add(current)) {
                // found circle
                break;
            }
            current = current.next;
        }

        return current;
    }
}