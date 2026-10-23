/** Run with: javac LinkedListNodeExample.java && java LinkedListNodeExample */
public class LinkedListNodeExample {
    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    // Reverse each link: 1 -> 2 -> 3 becomes 3 -> 2 -> 1.
    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node next = current.next; // Save the rest of the list.
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous; // New head.
    }

    // Floyd's algorithm: if fast catches slow, the list has a cycle.
    static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Call only when the list has no cycle.
    static void printList(Node head) {
        for (Node node = head; node != null; node = node.next) {
            System.out.print(node.value);
            System.out.print(node.next == null ? "\n" : " -> ");
        }
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        one.next = two;
        two.next = three;
        three.next = four;

        printList(one);
        Node head = reverse(one);
        printList(head);
        System.out.println("Cycle? " + hasCycle(head));

        // After reversal, one is the tail. Link it back to three.
        one.next = three;
        System.out.println("Cycle after linking tail back? " + hasCycle(head));
    }
}
