import java.util.*;

public class Mock20201213 {
    public static void main(String[] args) {
        System.out.println(args.length);

        Comparator<String> letterComparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }    
        };
        
    }
}