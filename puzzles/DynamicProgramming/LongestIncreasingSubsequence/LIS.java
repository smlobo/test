import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class LIS {
    private String a;
    private TreeSet<StringBuilder> s;
    int longest;

    static class SBComparator implements Comparator<StringBuilder> {
        public int compare(StringBuilder s1, StringBuilder s2) {
            return s1.toString().compareTo(s2.toString());
        }
    }

    public LIS(String a) {
        this.a = a;
        this.longest = 0;
        this.s = new TreeSet<StringBuilder>(new SBComparator());
    }

    private void lis(int i) {
        // Base case
        if (i == a.length())
            return;

        // Get the longest increasing subsequence for the rest of the string
        lis(i+1);

        // Iterate over all SBs in the TreeSet adding this char as appropriate
        StringBuilder hssb = new StringBuilder();
        hssb.append((char)(a.charAt(i)+1));
        Iterator<StringBuilder> iter = s.tailSet(hssb).iterator();
        LinkedList<StringBuilder> llsb = new LinkedList<>();
        while (iter.hasNext()) {
            StringBuilder sb = iter.next();
            //System.out.println(i + ": Seeing: " + sb);
            // if (sb.charAt(0) > a.charAt(i)) {
                StringBuilder newSB = new StringBuilder(sb);
                newSB.insert(0, a.charAt(i));
                llsb.add(newSB);
                longest = Math.max(longest, newSB.length());
                //System.out.println(". Adding: " + newSB);
            // }
        }
        s.addAll(llsb);

        // Always add as the possible end of a LIS
        StringBuilder x = new StringBuilder();
        x.append(a.charAt(i));
        s.add(x);
        longest = Math.max(longest, 1);
    }

    public static void main(String[] args) {
        String a = "carbohydrate";
        LIS lis = new LIS(a);
        lis.lis(0);
        System.out.println("LIS: " + a + " = " + lis.longest);
        Iterator<StringBuilder> iter = lis.s.iterator();
        while (iter.hasNext()) {
            StringBuilder sb = iter.next();
            if (sb.length() == lis.longest)
                System.out.println("  " + sb);
        }

        a = "abbxbcyccsccdarz";
        lis = new LIS(a);
        lis.lis(0);
        System.out.println("LIS: " + a + " = " + lis.longest);
        iter = lis.s.iterator();
        while (iter.hasNext()) {
            StringBuilder sb = iter.next();
            if (sb.length() == lis.longest)
                System.out.println("  " + sb);
        }
    }
}