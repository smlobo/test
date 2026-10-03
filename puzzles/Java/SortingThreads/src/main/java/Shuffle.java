import java.util.Random;

public class Shuffle {
    public static void shuffle(Comparable[] array) {
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            Util.swap(array, i, random.nextInt(array.length));
        }
    }
}
