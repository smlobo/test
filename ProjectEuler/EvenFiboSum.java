
public class EvenFiboSum {
    public static void main(String[] args) {
        int p1 = 1;
        int p2 = 2;
        int current = p1 + p2;
        int evenSum = p2;
        while (current < 4000000) {
            if (current%2 == 0)
                evenSum += current;
            p1 = p2;
            p2 = current;
            current = p1 + p2;
        }
        System.out.println(evenSum);
    }
}