import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

/** Run with: javac TreeMapExamples.java && java TreeMapExamples (Java 21+). */
public class TreeMapExamples {
    private static void show(String label, Object value) {
        System.out.println(label + ": " + value);
    }

    public static void main(String[] args) {
        // Natural ordering: entries are kept sorted by key, not by value.
        TreeMap<Integer, String> events = new TreeMap<>();
        events.put(30, "lunch");
        events.put(10, "breakfast");
        events.put(50, "dinner");
        events.putAll(Map.of(20, "meeting", 40, "walk"));
        show("sorted by key", events);
        show("put(30, ...), previous value", events.put(30, "late lunch"));
        show("after replacing key 30", events);

        show("get(20)", events.get(20));
        show("getOrDefault(99, missing)", events.getOrDefault(99, "missing"));
        show("containsKey(30) / containsValue(walk)",
                events.containsKey(30) + " / " + events.containsValue("walk"));
        show("size / isEmpty", events.size() + " / " + events.isEmpty());

        // Endpoints: Key methods throw on an empty map; Entry methods return null.
        show("firstKey / lastKey", events.firstKey() + " / " + events.lastKey());
        show("firstEntry / lastEntry", events.firstEntry() + " / " + events.lastEntry());

        // Navigation compares keys. No match produces null.
        show("lowerKey(30)   < 30", events.lowerKey(30));
        show("floorKey(30)  <= 30", events.floorKey(30));
        show("ceilingKey(30) >= 30", events.ceilingKey(30));
        show("higherKey(30)   > 30", events.higherKey(30));
        show("lowerEntry(30)", events.lowerEntry(30));
        show("floorEntry(30)", events.floorEntry(30));
        show("ceilingEntry(30)", events.ceilingEntry(30));
        show("higherEntry(30)", events.higherEntry(30));
        show("lowerKey(10) has no match", events.lowerKey(10));

        // Range maps are backed views. Boolean bounds specify inclusion.
        NavigableMap<Integer, String> middle = events.subMap(20, true, 40, false);
        show("subMap [20, 40)", middle);
        middle.put(25, "coffee"); // Also changes events; an out-of-range key would throw.
        show("original after middle.put(25, ...)", events);
        show("headMap(< 30)", events.headMap(30));
        show("tailMap(>= 30)", events.tailMap(30));
        show("headMap(<= 30)", events.headMap(30, true));
        show("tailMap(> 30)", events.tailMap(30, false));

        // Reverse order is another view of the same map.
        NavigableMap<Integer, String> descending = events.descendingMap();
        show("descendingMap", descending);
        show("descending firstEntry", descending.firstEntry());
        descending.remove(50);
        show("original after descending.remove(50)", events);

        // entrySet is the usual way to iterate over both keys and values.
        System.out.println("entries in key order:");
        for (Map.Entry<Integer, String> entry : events.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        show("keySet / values", events.keySet() + " / " + events.values());
        show("descendingKeySet", events.descendingKeySet());
        NavigableSet<Integer> keys = events.navigableKeySet(); // Backed view.
        keys.remove(25);
        show("original after keySet.remove(25)", events);

        show("remove(20), previous value", events.remove(20));
        show("pollFirstEntry", events.pollFirstEntry());
        show("pollLastEntry", events.pollLastEntry());
        show("remaining entries", events);

        // Common update methods, useful for interview frequency problems.
        TreeMap<String, Integer> counts = new TreeMap<>();
        counts.putIfAbsent("apple", 1);
        counts.putIfAbsent("apple", 99); // Existing non-null value stays 1.
        counts.merge("apple", 1, Integer::sum);
        counts.merge("pear", 1, Integer::sum);
        counts.computeIfAbsent("plum", key -> key.length());
        counts.computeIfPresent("pear", (key, old) -> old + 4);
        counts.compute("apple", (key, old) -> old == null ? 1 : old * 2);
        show("after putIfAbsent / merge / compute", counts);
        show("replace(apple, 4, 7)", counts.replace("apple", 4, 7));
        show("remove(pear, 5)", counts.remove("pear", 5));
        counts.replaceAll((key, value) -> value + 1);
        show("after replaceAll", counts);
        counts.forEach((key, value) -> System.out.println("  " + key + " = " + value));

        // Grouping: computeIfAbsent creates a list only for a missing key.
        TreeMap<String, List<String>> groups = new TreeMap<>();
        groups.computeIfAbsent("fruit", key -> new ArrayList<>()).add("apple");
        groups.computeIfAbsent("fruit", key -> new ArrayList<>()).add("pear");
        show("grouped values", groups);

        // TreeMap treats compare(a, b) == 0 as the same key.
        TreeMap<String, Integer> caseInsensitive =
                new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitive.put("Alice", 1);
        show("put(ALICE, 2), previous value", caseInsensitive.put("ALICE", 2));
        show("case-insensitive map (original key remains)", caseInsensitive);
        show("case-insensitive comparator", caseInsensitive.comparator()
                == String.CASE_INSENSITIVE_ORDER);

        // A comparator can also impose a different sort order.
        TreeMap<Integer, String> reverse = new TreeMap<>(Comparator.reverseOrder());
        reverse.putAll(Map.of(1, "one", 2, "two", 3, "three"));
        show("reverse key order", reverse);

        // TreeMap allows null values. get() cannot distinguish null from absence.
        TreeMap<String, String> nullableValues = new TreeMap<>();
        nullableValues.put("present", null);
        show("get(present) / containsKey(present)", nullableValues.get("present")
                + " / " + nullableValues.containsKey("present"));
        // Natural-order TreeMap does not allow a null key.

        TreeMap<String, Integer> independentCopy = new TreeMap<>(counts);
        counts.clear();
        show("after clear / copy unaffected", counts + " / " + independentCopy);
    }
}
