import java.util.*;

// Time finding top X number of repeats in a dataset (N) (unique elements = M < N)
// [1] Hash Map & Sort (logN + MlogM)
// [2] Hash Map & Priority Queue (logN + MlogX) {fastest}
// [3] Sort & Priority Queue (NlogN + MlogX) {slowest}
public class TestMaps {
    public static void main(String[] args) {
        // Common locals
        long timer, totalTime;
        HashMap<Integer, Integer> hashMap;
        int currentCount;
        Set<Map.Entry<Integer, Integer>> set;
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue;

        // Read from stdin
        Scanner stdin = new Scanner(System.in);

        System.out.print("Enter dataset size: ");
        int n = stdin.nextInt();

        System.out.print("Enter top repeats desired: ");
        int t = stdin.nextInt();

        System.out.println("Generating " + n + " random integers. I'll tell you the top " + t + " repeating numbers!");

        // Generate the dataset
        Random random = new Random();
        Integer[] dataset = new Integer[n];
        for (int i = 0; i < n; i++)
            dataset[i] = random.nextInt(n);

        // HashMap & Sort
        Shuffle.shuffle(dataset);
        timer = System.currentTimeMillis();
        hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            currentCount = hashMap.getOrDefault(dataset[i], 0);
            hashMap.put(dataset[i], ++currentCount);
        }
        set = hashMap.entrySet();
        Map.Entry<Integer, Integer>[] mapArray = set.toArray(new Map.Entry[set.size()]);
        Arrays.sort(mapArray, 0, mapArray.length, new MapEntryComparator());
        totalTime = System.currentTimeMillis() - timer;
        System.out.println("Repeats via HashMap & Sort took: " + totalTime);
//        for (int i = 0; i < mapArray.length && i < t; i++) {
//            System.out.println("  {" + mapArray[i].getKey() + "} " + mapArray[i].getValue());
//        }

        // HashMap & PriorityQueue
        Shuffle.shuffle(dataset);
        timer = System.currentTimeMillis();
        hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            currentCount = hashMap.getOrDefault(dataset[i], 0);
            hashMap.put(dataset[i], ++currentCount);
        }
        set = hashMap.entrySet();
        priorityQueue = new PriorityQueue<>(t+1, new MapEntryComparator().reversed());
        for (Map.Entry<Integer, Integer> entry : set) {
            priorityQueue.add(entry);
            if (priorityQueue.size() > t)
                priorityQueue.poll();
        }
        totalTime = System.currentTimeMillis() - timer;
        System.out.println("Repeats via HashMap & PriorityQueue took: " + totalTime);
        Iterator<Map.Entry<Integer, Integer>> iterator = priorityQueue.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, Integer> entry = iterator.next();
//            System.out.println("  <" + entry.getKey() + "> " + entry.getValue());
//        }

        // Sort followed by PriorityQueue
        Shuffle.shuffle(dataset);
        timer = System.currentTimeMillis();
        Arrays.sort(dataset);
        //TreeSet<Map.Entry<Integer, Integer>> treeSet = new TreeSet<>(new MapEntryComparator());
        priorityQueue = new PriorityQueue<>(t+1, new MapEntryComparator().reversed());
        currentCount = 1;
        int previous = -1;      // dataset has no negative numbers
        Map.Entry<Integer, Integer> entry;
        for (int i = 0; i < n; i++) {
            if (dataset[i] == previous) {
                currentCount++;
            }
            else if (previous != -1) {
                entry = new LocalEntry<>(previous, currentCount);
                priorityQueue.add(entry);
                if (priorityQueue.size() > t)
                    priorityQueue.poll();
                currentCount = 1;
                previous = dataset[i];
            }
            else {
                previous = dataset[i];
            }
        }
        entry = new LocalEntry<>(previous, currentCount);
        priorityQueue.add(entry);
        if (priorityQueue.size() > t)
            priorityQueue.poll();
        totalTime = System.currentTimeMillis() - timer;
        System.out.println("Repeats via Sort & PriorityQueue took: " + totalTime);
//        Iterator<Map.Entry<Integer, Integer>> iterator2 = priorityQueue.iterator();
//        while (iterator2.hasNext()) {
//            entry = iterator2.next();
//            System.out.println("  [" + entry.getKey() + "] " + entry.getValue());
//        }
    }

    private static class MapEntryComparator implements Comparator<Map.Entry<Integer, Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return -o1.getValue().compareTo(o2.getValue());
        }
    }

    private static class LocalEntry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public LocalEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }
}
