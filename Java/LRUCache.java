import java.util.HashMap;
import java.util.Map;

class LRUCache {
    static class Element {
        String key;
        String value;
        Element prev;
        Element next;
        Element(String k, String v) {
            this.key = k;
            this.value = v;
            this.prev = null;
            this.next = null;
        }
        public String toString() {
            return "<" + key + "," + value + ">";
        }
    }

    int size;
    Element mru;
    Element lru;
    HashMap<String, Element> map;

    public LRUCache(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size == " + size);
        }
        this.size = size;
        this.mru = null;
        this.lru = null;
        this.map = new HashMap<>();
    }

    public String get(String k) {
        Element e = map.get(k);

        // Not available
        if (e == null) {
            return "";
        }

        // move to lru list head
        Element prev = e.prev;
        Element next = e.next;
        Element oldMru = mru;
        Element oldLru = lru;

        // single element
        if (prev == null && next == null) {
            assert oldMru == e;
            assert oldLru == e;
            return e.value;
        }
        // Already mru
        if (prev == null) {
            assert oldMru == e;
            return e.value;
        }
        // Lru
        if (next == null) {
            assert oldLru == e;
            prev.next = null;
            e.next = oldMru;
            e.prev = null;
            oldMru.prev = e;
            lru = prev;
            mru = e;
            return e.value;
        }

        // middle
        prev.next = next;
        next.prev = prev;
        e.next = oldMru;
        e.prev = null;
        oldMru.prev = e;
        mru = e;

        return e.value;
    }

    public void put(String k, String v) {
        // key in cache
        Element e = map.get(k);
        if (e != null) {
            // move to front & fix to new value
            get(k);
            mru.value = v;
            return;
        }

        e = new Element(k, v);
        map.put(k, e);

        Element oldMru = mru;
        Element oldLru = lru;

        // 1st entry
        if (oldMru == null) {
            assert oldLru == null;
            mru = e;
            lru = e;
            return;
        }

        // cache not full
        if (map.size() <= this.size) {
            // make mru
            e.next = oldMru;
            oldMru.prev = e;
            mru = e;
            return;
        }

        // evict
        map.remove(lru.key);

        // size == 1
        if (this.size == 1) {
            assert oldMru == oldLru;
            mru = e;
            lru = e;
            return;
        }

        // size >= 2; lru pruned; mru new
        e.next = oldMru;
        oldMru.prev = e;
        mru = e;
        lru = oldLru.prev;
        lru.next = null;
    }

    private void status() {
        System.out.println("LRU: " + size);
        for (Map.Entry<String, Element> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Forward:");
        Element e = mru;
        while (e != null) {
            System.out.print(e + ", ");
            e = e.next;
        }
        System.out.println("\nBackward:");
        e = lru;
        while (e != null) {
            System.out.print(e + ", ");
            e = e.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);
        cache.put("a", "");
        cache.put("a", "aa");
        // cache.status();
        cache.put("b", "bb");
        // cache.status();
        cache.put("c", "cc");
        // cache.status();
        System.out.println("get(a): " + cache.get("a"));
        // cache.status();
        cache.put("d", "dd");
        // cache.status();
        System.out.println("get(a) (still in): " + cache.get("a"));
        System.out.println("after evict get(b): " + cache.get("b"));

        cache = new LRUCache(1);
        cache.put("a", "aa");
        cache.put("b", "bb");
        System.out.println("[2] get(a): " + cache.get("a"));
        System.out.println("[2] get(b): " + cache.get("b"));

        try {
            cache = new LRUCache(0);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}