import java.util.ArrayDeque;
import java.util.Iterator;

public class BinarySearchTree <K extends Comparable<K>, V> 
        implements Iterable <K> {
    class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    public void put(K key, V value) {
        Node newNode = new Node(key, value);

        if (root == null)
            root = newNode;
        else
            put(root, newNode);
    }

    private void put(Node node, Node newNode) {
        // New node goes in the left subtree
        if (newNode.key.compareTo(node.key) < 0) {
            if (node.left == null)
                node.left = newNode;
            else
                put(node.left, newNode);
        }
        // New node goes in the right subtree
        else if (newNode.key.compareTo(node.key) > 0) {
            if (node.right == null)
                node.right = newNode;
            else
                put(node.right, newNode);
        }
        // New node replaces the current node
        else {
            node.value = newNode.value;
        }
    }

    public V get(K key) {
        Node node = root;

        while (node != null) {
            // Look in the left subtree
            if (key.compareTo(node.key) < 0)
                node = node.left;
            // Look in the right subtree
            else if (key.compareTo(node.key) > 0)
                node = node.right;
            // Found it
            else
                break;
        }

        if (node == null)
            return null;

        return node.value;
    }

    public K min() {
        // Empty tree
        if (root == null)
            return null;
        return min(root);
    }

    private K min(Node node) {
        // If the left is empty, we've found the min
        if (node.left == null)
            return node.key;

        // Keep searching the left sub-tree
        return min(node.left);
    }

    public K max() {
        // Empty tree
        if (root == null)
            return null;
        return max(root);
    }

    private K max(Node node) {
        // If the right is empty, we've found the max
        if (node.right == null)
            return node.key;

        // Keep searching the right sub-tree
        return max(node.right);
    }

    public K floor(K key) {
        return floor(key, root, null);
    }

    private K floor(K key, Node node, K current) {
        if (node == null)
            return current;

        // Update current?
        if (key.compareTo(node.key) >= 0 && 
            (current == null || current.compareTo(node.key) < 0))
            current = node.key;

        // Continue search
        // Key is < node - look in the left subtree
        if (key.compareTo(node.key) < 0)
            return floor(key, node.left, current);
        // Key is > node - look in the right subtree (for better floor)
        else if (key.compareTo(node.key) > 0)
            return floor(key, node.right, current);

        // Key == node - nothing else can be just lower - return the same key
        return current;
    }

    public K ceiling(K key) {
        return ceiling(key, root, null);
    }

    private K ceiling(K key, Node node, K current) {
        if (node == null)
            return current;

        // Update current?
        // Update current?
        if (key.compareTo(node.key) <= 0 && 
            (current == null || current.compareTo(node.key) > 0))
            current = node.key;

        // Continue search
        // Key is < node - look in the left subtree (for better ceiling)
        if (key.compareTo(node.key) < 0)
            return ceiling(key, node.left, current);
        // Key is > node - look in the right subtree
        else if (key.compareTo(node.key) > 0)
            return ceiling(key, node.right, current);

        // Key == node - nothing else can be just higher - return the same key
        return current;
    }

    public Iterator<K> iterator() {
        ArrayDeque<K> arrayDeque = new ArrayDeque<>();

        addToIterator(root, arrayDeque);

        return arrayDeque.iterator();
    }

    private void addToIterator(Node node, ArrayDeque<K> arrayDeque) {
        if (node == null)
            return;

        // Add left subtree before self & right
        addToIterator(node.left, arrayDeque);

        // Add the current node
        arrayDeque.add(node.key);

        // Finally add the right subtree
        addToIterator(node.right, arrayDeque);
    }

    public void printInOrder() {
        Iterator<K> iterator = iterator();
        while (iterator.hasNext()) {
            K nKey = iterator.next();
            System.out.print("<" + nKey + "," + get(nKey) + ">; ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Character> bst = 
            new BinarySearchTree<>();

        bst.put(5, 'E');
        bst.put(10, 'J');
        bst.put(3, 'C');
        bst.put(7, 'G');
        bst.put(13, 'M');
        bst.put(8, 'H');

        System.out.println("Min: " + bst.min() + " -> " + bst.get(bst.min()));
        System.out.println("Max: " + bst.max() + " -> " + bst.get(bst.max()));
        System.out.println("Floor 12: " + bst.floor(12) + " -> " + 
            bst.get(bst.floor(12)));
        System.out.println("Floor 500: " + bst.floor(500) + " -> " + 
            bst.get(bst.floor(500)));
        System.out.println("Floor 6: " + bst.floor(6) + " -> " + 
            bst.get(bst.floor(6)));
        System.out.println("Floor 7: " + bst.floor(7) + " -> " + 
            bst.get(bst.floor(7)));
        System.out.println("Floor 2: " + bst.floor(2));
        System.out.println("Ceiling 12: " + bst.ceiling(12) + " -> " + 
            bst.get(bst.ceiling(12)));
        System.out.println("Ceiling -500: " + bst.ceiling(-500) + " -> " + 
            bst.get(bst.ceiling(-500)));
        System.out.println("Ceiling 6: " + bst.ceiling(6) + " -> " + 
            bst.get(bst.ceiling(6)));
        System.out.println("Ceiling 8: " + bst.ceiling(8) + " -> " + 
            bst.get(bst.ceiling(8)));
        System.out.println("Ceiling 14: " + bst.ceiling(14));
        bst.printInOrder();

        System.out.println("Replace 8,H with 8,$");
        bst.put(8, '$');
        bst.printInOrder();

    }
}