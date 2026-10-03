import java.util.Arrays;

public class ACG {
    enum Turn {
        MY, YOUR
    }

    private static int acg(int[] a, int p, int q, Turn t) {
        // Last turn
        if (q == p)
            return t == Turn.MY ? a[p] : 0;

        // Next to last turn (for MY only)
        if ((q-p) == 1 && t == Turn.MY)
            return Math.max(a[p], a[q]);

        // Whose turn is next?
        Turn nextTurn = (t == Turn.MY) ? Turn.YOUR : Turn.MY;

        // If the left coin was chosen
        int leftCoin = acg(a, p+1, q, nextTurn);
        // If the right coin was chosen
        int rightCoin = acg(a, p, q-1, nextTurn);

        int returnValue = 0;

        // When its my turn, I choose the max
        if (t == Turn.MY) {
            if (leftCoin > rightCoin)
                returnValue = a[p] + leftCoin;
            else
                returnValue = a[q] + rightCoin;
        }
        // When its not my turn, I get the min
        else {
            if (leftCoin < rightCoin)
                returnValue = leftCoin;
            else
                returnValue = rightCoin;
        }

        // System.out.println(p + "," + q + " -> " + returnValue + " : " + t);
        return returnValue;
    }

    public static void main(String[] args) {
        int[] a = new int[] {5, 10, 100, 25};
        int s = acg(a, 0, a.length-1, Turn.MY);
        System.out.println(Arrays.toString(a) + " = " + s);

        a = new int[] {1, 25, 5, 100, 1, 10, 5};
        s = acg(a, 0, a.length-1, Turn.MY);
        System.out.println(Arrays.toString(a) + " = " + s);
    }
}