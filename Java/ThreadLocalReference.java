import java.util.LinkedList;
import java.lang.ref.WeakReference;

class ThreadHolder {
    //private Thread thread;
    private WeakReference<Thread> weakThread;

    ThreadHolder() {
        // this.thread = Thread.currentThread();
        // System.out.println("Holding on to thread: " + toString());
        this.weakThread = new WeakReference<>(Thread.currentThread());
        System.out.println("Weak holding on to thread: " + toString());
    }

    public String toString() {
        // return "<" + thread.getName() + ":" + thread + ">";
        if (weakThread.get() == null)
            return "<null>";
        return "<" + weakThread.get().getName() + ":" + weakThread.get() + ">";
    }
}

class DummyThread extends Thread {
    DummyThread(String name) {
        super.setName(name);
    }

    public void run() {
        System.out.println("Starting dummy " + getName() + " ...");
        ThreadLocalReference.threadHolderList.add(new ThreadHolder());
        for (int i = 0; i < 5; i++) {
            System.out.println("\tdummy: " + getName() + " -> " + i);
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {}
        }
        System.out.println("Stopping dummy " + getName() + " ...");
    }
}

public class ThreadLocalReference {
    public static LinkedList<ThreadHolder> threadHolderList = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        ThreadLocalReference tlr = new ThreadLocalReference();

        createNewThread("foo");
        Thread.sleep(2000);
        createNewThread("bar");
        Thread.sleep(2000);
        createNewThread("zoo");

        System.out.println("Early Print ...");
        printList();

        Thread.sleep(10000);

        System.out.println("Late Print ...");
        printList();

        System.gc();
        //Thread.sleep(10000);

        System.out.println("After GC ...");
        printList();

        System.out.println("Done!");
    }

    private static void createNewThread(String name) {
        Thread t = new DummyThread(name);
        t.start();
    }

    private static void printList() {
        for (ThreadHolder th : ThreadLocalReference.threadHolderList) {
            System.out.println("\t" + th);
        }
    }
}