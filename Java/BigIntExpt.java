import java.math.BigInteger;

public class BigIntExpt {
    private static final String[] table = { "", "K", "M", "G", "T", "P", "E", 
        "Z", "Y" };

    public static String simplified(long a, int count) {
        while (a >= 1024) {
            a /= 1024;
            count++;
        }
        return a + table[count];
    }

    public static String simplified(long a) {
        return simplified(a, 0);
    }

    public static String simplified(BigInteger b) {
        int count = 0;
        BigInteger oneK = new BigInteger("1024");
        BigInteger twoE = new BigInteger("2305843009213693952");
        // while (!(b < 2E) {
        while (b.compareTo(twoE) != -1) {
            b = b.divide(oneK);
            count++;
        }
        return simplified(b.longValue(), count);
    }

    public static void main(String[] args) {
        int x = 2;
        for (int i = 1; i < 30; i++) {
            System.out.println("[" + i + "] " + x + " : " + simplified(x));
            x *= 2;
        }
        long y = x;
        for (int i = 30; i < 62; i++) {
            System.out.println("[" + i + "] " + y + " : " + simplified(y));
            y *= 2;
        }
        BigInteger z = new BigInteger(Long.toString(y));
        BigInteger two = new BigInteger("2");
        for (int i = 62; i < 90; i++) {
            System.out.println("[" + i + "] " + z + " : " + simplified(z));
            //z *= 2;
            z = z.multiply(two);
        }
    }
}