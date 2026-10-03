import java.util.*;

class PQ {

    static class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }
    
    private static void print_queue(PriorityQueue<String> q) {
        Iterator itr = q.iterator();
        while (itr.hasNext())
            System.out.print(itr.next() + ", ");
        System.out.println();
    }
    
    public static void main(String args[]) {
        //PriorityQueue<String> queue = new PriorityQueue<>();
        //PriorityQueue<String> queue = new PriorityQueue<>
        //  (5, Collections.reverseOrder());
        PriorityQueue<String> queue = new PriorityQueue<String>
            (5, new StringComparator());

        queue.add("Amelia");
        queue.add("Ryan");
        queue.add("Aparna");
        queue.add("Sheldon");

        System.out.println("Head: " + queue.element());
        System.out.println("Head: " + queue.peek());

        System.out.println("Iterating: ");
        print_queue(queue);
        
        queue.remove();
        queue.poll();

        System.out.println("Iterating after remove: ");
        print_queue(queue);
    }
}
