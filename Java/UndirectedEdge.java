import java.util.HashMap;
import java.util.Map;

public class UndirectedEdge {
    private final String node1;
    private final String node2;
    public UndirectedEdge(String n1, String n2) {
        if (n1 == null || n2 == null) {
            throw new IllegalArgumentException("node id null");
        }
        this.node1 = n1;
        this.node2 = n2;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UndirectedEdge)) {
            return false;
        }
        UndirectedEdge that = (UndirectedEdge) obj;
        return this.node1.equals(that.node1) && this.node2.equals(that.node2) || 
            this.node1.equals(that.node2) && this.node2.equals(that.node1);
    }
    @Override
    public int hashCode() {
        String s1, s2;
        if (node1.compareTo(node2) < 0) {
            s1 = node2;
            s2 = node1;
        } else {
            s1 = node1;
            s2 = node2;
        }
        return 31 * s1.hashCode() + s2.hashCode();
    }
    @Override
    public String toString() {
        return "[" + hashCode() + "] {" + node1 + "," + node2 + "}";
    }

    public static void main(String[] args) {
        UndirectedEdge e1 = new UndirectedEdge("A", "B");
        System.out.println(e1);
        UndirectedEdge e2 = new UndirectedEdge("B", "A");
        System.out.println(e2);
        if (e1.equals(e2)) {
            System.out.println("Commutative: " + e1 + " == " + e2);
        } else {
            System.out.println("Error: " + e1 + " != " + e2);            
        }

        // Map
        Map<UndirectedEdge, Integer> weights = new HashMap<>();
        weights.put(e1, 7);
        System.out.println(e2 + " weight = " + weights.get(e2));

        UndirectedEdge e3 = new UndirectedEdge("A", "A");
        UndirectedEdge e4 = new UndirectedEdge("A", "C");
        weights.put(e3, 10);
        weights.put(e4, 3);
        System.out.println(e3 + " weight = " + weights.get(e3));
        System.out.println(e4 + " weight = " + weights.get(e4));        
        System.out.println(e1 + " weight = " + weights.get(e1));

        e3 = new UndirectedEdge("Aa", "X");
        e4 = new UndirectedEdge("BB", "X");
        weights.put(e3, 1000);
        weights.put(e4, 1001);
        System.out.println(e3 + " weight = " + weights.get(e3));
        System.out.println(e4 + " weight = " + weights.get(e4));        

    }
}