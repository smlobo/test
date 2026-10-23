import java.util.Map;
import java.util.TreeMap;

public class UploadCoverage {
    int totalSize;
    TreeMap<Integer, Integer> map;

    public UploadCoverage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("bad size: " + size);
        }
        this.totalSize = size;
        this.map = new TreeMap<>();
    }

    public void addChunk(int start, int length) {
        if (start < 0 || start >= totalSize || length <= 0 || length > totalSize - start) {
            throw new IllegalArgumentException("bad start/length");
        }

        // Store covered ranges as [start, stop).
        int stop = start + length;

        Map.Entry<Integer, Integer> previous = map.floorEntry(start);
        if (previous != null && previous.getValue() >= start) {
            start = previous.getKey();
            stop = Math.max(stop, previous.getValue());
            map.remove(previous.getKey());
        }

        Map.Entry<Integer, Integer> next = map.ceilingEntry(start);
        while (next != null && next.getKey() <= stop) {
            stop = Math.max(stop, next.getValue());
            map.remove(next.getKey());
            next = map.ceilingEntry(start);
        }

        map.put(start, stop);
    }

    public boolean isComplete() {
        if (!map.containsKey(0)) {
            return false;
        }
        int value = map.get(0);
        if (value < totalSize) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        UploadCoverage uc = new UploadCoverage(10);
        uc.addChunk(0, 4);
        uc.addChunk(6, 4);
        System.out.println("[1]: " + uc.isComplete());
        uc.addChunk(3, 3);
        System.out.println("[2]: " + uc.isComplete());

        uc = new UploadCoverage(10);
        uc.addChunk(0, 4);
        uc.addChunk(4, 4);
        System.out.println("[1]: " + uc.isComplete());
        uc.addChunk(8, 2);
        System.out.println("[2]: " + uc.isComplete());

        // duplicate
        uc = new UploadCoverage(10);
        uc.addChunk(0, 10);
        uc.addChunk(0, 10);
        System.out.println("[1]: " + uc.isComplete());

        // invalid
        uc = new UploadCoverage(10);
        uc.addChunk(8, 3);
    }
}
