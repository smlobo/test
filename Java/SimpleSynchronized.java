/* Demo not-synchronized race condition v/s synchronized no race */

public class SimpleSynchronized implements Runnable {
    private static final int MAX_THREADS = 10;
    private int notSync;
    private int inSync;

    @Override
    public void run() {
        // Sleep for a random amount of time - 0 - 10ms
        try {
            Thread.sleep((long)(Math.random() * 10L));
            notSync++;
            Thread.sleep((long)(Math.random() * 10L));
            synchronized(this) {
                inSync++;
            }
            // Do this twice to better show un-synchronized
            Thread.sleep((long)(Math.random() * 10L));
            notSync++;
            Thread.sleep((long)(Math.random() * 10L));
            synchronized(this) {
                inSync++;
            }
        }
        catch (Exception e) {}
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java SimpleSynchronized <num-threads>");
            System.exit(1);
        }

        int n = Math.max(Integer.parseInt(args[0]), 10);
        Thread[] tArray = new Thread[n];

        // The single shared object
        SimpleSynchronized ss = new SimpleSynchronized();

        for (int i = 0; i < n; i++) {
            tArray[i] = new Thread(ss);
            tArray[i].start();
        }

        for (int i = 0; i < n; i++) {
            tArray[i].join();
        }

        System.out.println("notSync field: " + ss.notSync);
        System.out.println("inSync field: " + ss.inSync);
    }
}