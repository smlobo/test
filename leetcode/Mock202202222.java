import java.util.*;

public class Mock202202222 {

    public static int balancedStringSplit(String s) {
        int countNeedL = 0;
        int countNeedR = 0;

        int splitCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char theChar = s.charAt(i);

            if (theChar == 'L') {
                countNeedR++;
            }
            else {
                countNeedL++;                
            }

            if (countNeedR == countNeedL) {
                splitCount++;
                countNeedR = 0;
                countNeedL = 0;
            }
        }

        return splitCount;
    }

    public static void main(String[] args) {
        String s;
        int a;

        s = "RLRRLLRLRL";
        a = balancedStringSplit(s);
        System.out.println(a); 

        s = "RLLLLRRRLR";
        a = balancedStringSplit(s);
        System.out.println(a); 

        s = "LLLLRRRR";
        a = balancedStringSplit(s);
        System.out.println(a); 
    }
}