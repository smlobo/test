import java.lang.ref.WeakReference;

class StrongReference<T> {
    private T referent;
    public StrongReference(T t) {
        referent = t;
    }
    public T get() {
        return referent;
    }
}

public class ReferenceTest {

    private static int getAnswer(Integer[] ga) {
        int total = 0;
        for (int i = 0; i < ga.length; i++)
            if (i%2 == 0)
                total += ga[i];
            else
                total -= ga[i];
        return total;
    }

    public static void main(String[] args) throws InterruptedException {
        WeakReference<Integer> wr = new WeakReference<>(Integer.valueOf(-10));
        StrongReference<Integer> sr = new StrongReference<>(Integer.valueOf(10));
        System.out.println("Before gc: wr=" + wr.get() + ", sr=" + sr.get());

        System.gc();
        // Allocate a lot of memory
        Integer[] giantArray = new Integer[10000000];
        for (int i = 0; i < giantArray.length; i++)
            giantArray[i] = Integer.valueOf(i-1);
        System.out.println(getAnswer(giantArray));
        System.gc();
        Thread.sleep(100);

        // Only r.get() becomes null.
        System.out.println("After gc: wr=" + wr.get() + ", sr=" + sr.get());
    }
}
