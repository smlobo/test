

public class MyBinarySearchTree<V> {
    class Node<V> {
        String key;
        V value;
        Node<V> left;
        Node<V> right;

        public Node(String key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }
    
    private Node<V> root;

    private void add(Node<V> node, String key, V value) {
        // key < node
        if (key.compareTo(node.key) < 0) {
            // Empty - it goes here
            if (node.left == null) {
                node.left = new Node<>(key, value);
                System.out.println("Add left {to " + node.key + "}, " + 
                    node.left);
                return;
            }
            // Keep searching
            else {
                add(node.left, key, value);
            }
        }
        // key > node
        else if (key.compareTo(node.key) > 0) {
            // Empty - it goes here
            if (node.right == null) {
                node.right = new Node<>(key, value);
                System.out.println("Add right {to " + node.key + "}, " + 
                    node.right);
                return;
            }
            // Keep searching
            else {
                add(node.right, key, value);
            }
        }
        // Exact match - replace with the new value
        else {
            node.value = value;
            System.out.println("Replaced: " + node);
            return;
        }
    }

    public V add(String key, V value) {
        // Initial special case, create the root
        if (root == null)
            root = new Node<>(key, value);

        // Binary search and add to the tree
        else
            add(root, key, value);

        return value;
    }

    private V get(Node<V> node, String key) {
        // Does not exist
        if (node == null)
            return null;

        // key < node
        if (key.compareTo(node.key) < 0)
            return get(node.left, key);
        // key > node
        else if (key.compareTo(node.key) > 0)
            return get(node.right, key);

        // Found
        return node.value;
    }

    public V get(String key) {
        return get(root, key);
    }

    public V delete(String key) {
        // TODO
        return null;
    }

    public static void main(String[] args) {
        // Create a MyHashTable of integers
        MyBinarySearchTree<Integer> integerBST = new MyBinarySearchTree<>();

        // Add some test values
        integerBST.add("Apple", 10);
        integerBST.add("Banana", 20);
        integerBST.add("Pear", 30);

        // Modify existing value
        integerBST.add("Apple", 100);

        // Retrieve some values
        System.out.println("Banana = " + integerBST.get("Banana"));
        System.out.println("Peach = " + integerBST.get("Peach"));
        System.out.println("Apple = " + integerBST.get("Apple"));

        // Delete something
        integerBST.delete("Banana");
        System.out.println("Banana = " + integerBST.get("Banana"));

        System.out.println("Pear = " + integerBST.get("Pear"));
    }
}