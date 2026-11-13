import java.util.LinkedList;
import java.util.Iterator;

class MyTuple<X> {
    private String key;
    private X value;

    public MyTuple(String key, X value) {
        this.key = key;
        this.value = value;
    }

    public String key() {
        return key;
    }

    public X value() {
        return value;
    }

    public void setValue(X value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

public class MyHashTable<V> {
    private static final int SIZE = 100;
    private LinkedList<MyTuple<V>>[] array;

    public MyHashTable() {
        array = new LinkedList[SIZE];
        for (int i = 0; i < array.length; i++)
            array[i] = new LinkedList<MyTuple<V>>();
    }

    public V add(String key, V value) {
        // Valid input
        if (key == null || value == null)
            return null;

        // Hash the string
        int hash = key.hashCode();

        // Get the index into the array
        int index = hash % SIZE;

        // Check for existing in the index linked list
        Iterator<MyTuple<V>> iterator = array[index].iterator();
        while (iterator.hasNext()) {
            MyTuple<V> myTuple = iterator.next();
            // Replace existing
            if (key.equals(myTuple.key())) {
                myTuple.setValue(value);
                System.out.println("R [" + index + "] " + myTuple);
                return value;
            }
        }

        // Create new
        MyTuple<V> myTuple = new MyTuple<>(key, value);
        array[index].add(myTuple);
        System.out.println("A [" + index + "] " + myTuple);
        return value;
    }

    public V get(String key) {
        // Valid input
        if (key == null)
            return null;

        // Hash the string
        int hash = key.hashCode();

        // Get the index into the array
        int index = hash % SIZE;

        // Retrieve from the index linked list
        Iterator<MyTuple<V>> iterator = array[index].iterator();
        while (iterator.hasNext()) {
            MyTuple<V> myTuple = iterator.next();
            // Replace existing
            if (key.equals(myTuple.key())) {
                System.out.println("G [" + index + "] " + myTuple);
                return myTuple.value();
            }
        }

        return null;        
    }

    public V delete(String key) {
        // Valid input
        if (key == null)
            return null;

        // Hash the string
        int hash = key.hashCode();

        // Get the index into the array
        int index = hash % SIZE;

        // Check for existing in the index linked list
        MyTuple<V> myTuple = null;
        Iterator<MyTuple<V>> iterator = array[index].iterator();
        while (iterator.hasNext()) {
            myTuple = iterator.next();
            // Replace existing
            if (key.equals(myTuple.key())) {
                System.out.println("D [" + index + "] " + myTuple);
                break;
            }
        }

        // If found, delete it, and return the value
        if (myTuple != null) {
            array[index].remove(myTuple);
            return myTuple.value();
        }

        return null;        
    }

    public static void main(String[] args) {
        // Create a MyHashTable of integers
        MyHashTable<Integer> integerHashTable = new MyHashTable<>();

        // Add some test values
        integerHashTable.add("Apple", 10);
        integerHashTable.add("Banana", 20);
        integerHashTable.add("Pear", 30);

        // Modify existing value
        integerHashTable.add("Apple", 100);

        // Retrieve some values
        System.out.println("Banana = " + integerHashTable.get("Banana"));
        System.out.println("Peach = " + integerHashTable.get("Peach"));
        System.out.println("Apple = " + integerHashTable.get("Apple"));

        // Delete something
        integerHashTable.delete("Banana");
        System.out.println("Banana = " + integerHashTable.get("Banana"));

        System.out.println("Pear = " + integerHashTable.get("Pear"));
    }
}