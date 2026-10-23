import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class MissingSequence {

    static class SequenceSIndex implements Comparable<SequenceSIndex> {
        long seqNum;
        int streamIndex;
        SequenceSIndex(long sn, int si) {
            this.seqNum = sn;
            this.streamIndex = si;
        }
        public int compareTo(SequenceSIndex that) {
            return Long.compare(this.seqNum, that.seqNum);
        }
    }

    static class Range {
        long lower;
        long upper;
        Range(long l, long u) {
            this.lower = l;
            this.upper = u;
        }
        public String toString() {
            return "{" + lower + "," + upper + "}";
        }
    }

    private static List<Range> findMissing(List<Iterator<Long>> streams, 
        long n) {

        // return list
        List<Range> missings = new ArrayList<>();
        long nextExpected = 1;
        long prevSeqNum = 0;

        // empty list of streams
        if (streams.isEmpty()) {
            missings.add(new Range(1, n));
            return missings;
        }

        // setup pq for sorted global order
        PriorityQueue<SequenceSIndex> pq = new PriorityQueue<>(streams.size());

        // initialize
        for (int i = 0; i < streams.size(); i++) {
            if (!streams.get(i).hasNext()) {
                continue;
            }
            long seqNum = streams.get(i).next();
            pq.add(new SequenceSIndex(seqNum, i));
        }

        // iterate in global sorted order
        while (!pq.isEmpty()) {
            SequenceSIndex si = pq.poll();

            int streamIndex = si.streamIndex;
            if (streams.get(streamIndex).hasNext()) {
                long seqNum = streams.get(streamIndex).next();
                pq.add(new SequenceSIndex(seqNum, streamIndex));                
            }

            // duplicate
            if (si.seqNum == prevSeqNum) {
                continue;
            }
            prevSeqNum = si.seqNum;
            System.out.print(si.seqNum + ", ");

            // not found
            if (nextExpected < si.seqNum) {
                missings.add(new Range(nextExpected, si.seqNum - 1));
            }
            nextExpected = si.seqNum + 1;
        }
        System.out.println();

        // tail not found
        if (nextExpected <= n) {
            missings.add(new Range(nextExpected, n));
        }

        return missings;
    }

    public static void main(String[] args) {
        List<Long> s1 = new ArrayList<>(List.of(1L, 4L, 7L));
        List<Long> s2 = new ArrayList<>(List.of(2L, 4L, 6L));
        long n = 8;
        List<Iterator<Long>> ss = new ArrayList<>(List.of(s1.iterator(), 
            s2.iterator()));
        List<Range> m = findMissing(ss, n);
        System.out.println(s1 + "; " + s2 + " = " + m);

        s1 = new ArrayList<>(List.of(1L, 4L, 4L, 7L));
        s2 = new ArrayList<>();
        n = 8;
        ss = new ArrayList<>(List.of(s1.iterator(), s2.iterator()));
        m = findMissing(ss, n);
        System.out.println(s1 + "; " + s2 + " = " + m);

        s1 = new ArrayList<>(List.of(2L, 4L, 4L, 7L));
        s2 = new ArrayList<>(List.of(2L, 4L, 4L, 8L, 8L));
        n = 8;
        ss = new ArrayList<>(List.of(s1.iterator(), s2.iterator()));
        m = findMissing(ss, n);
        System.out.println(s1 + "; " + s2 + " = " + m);

        n = 8;
        ss = new ArrayList<>();
        m = findMissing(ss, n);
        System.out.println("null; null = " + m);
    }
}