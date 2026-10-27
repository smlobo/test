import java.util.*;

class TopKFrequent {
    class ElementFrequency implements Comparable<ElementFrequency> {
        int element;
        int frequency;
        public ElementFrequency(int e, int f) {
            this.element = e;
            this.frequency = f;
        }
        public int compareTo(ElementFrequency that) {
            return that.frequency - this.frequency;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        // Calculate frequency
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int current = freqMap.getOrDefault(nums[i], 0);
            current++;
            freqMap.put(nums[i], current);
        }

        // ArrayList of pairs
        ArrayList<ElementFrequency> list = new ArrayList<>(freqMap.size());
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            list.add(new ElementFrequency(entry.getKey(), entry.getValue()));
        }

        list.sort((a, b) -> {
            return b.frequency - a.frequency;
        });

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = list.get(i).element;
        }

        return topK;
    }

    public static void main(String[] args) {
        TopKFrequent t = new TopKFrequent();

        int[] i = {1, 1, 2, 2, 1, 3};
        int k = 2;
        int[] o = t.topKFrequent(i, k);
        System.out.println(Arrays.toString(i) + ", " + k + " = " + 
            Arrays.toString(o));

        int[] i2 = {1,2,1,2,1,2,3,1,3,2};
        k = 3;
        o = t.topKFrequent(i2, k);
        System.out.println(Arrays.toString(i2) + ", " + k + " = " + 
            Arrays.toString(o));
    }
}