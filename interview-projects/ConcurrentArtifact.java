import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Arrays;

public class ConcurrentArtifact {
    Lock globalLock;
    HashMap<String, byte[]> cache;
    HashMap<String, CompletableFuture<byte[]>> inFlight;
    Random random;
    static int fetchCount = 0;

    public ConcurrentArtifact() {
        this.globalLock = new ReentrantLock();
        this.cache = new HashMap<>();
        this.inFlight = new HashMap<>();
        this.random = new Random();
    }

    private byte[] fetchFromStore(String artifactKey) throws TimeoutException {
        try {
            Thread.sleep(100 + random.nextInt(100));
        }
        catch (InterruptedException e) {
            return null;
        }

        // fail alternate calls
        fetchCount++;
        if (fetchCount % 2 == 0) {
            // on 4th call throw
            if (fetchCount == 4) {
                throw new TimeoutException("timeout");
            }
            System.out.println("FAIL from fetch");
            return null;
        }

        System.out.println("success from fetch");
        return new byte[]{'a', 'b'};
    }

    public byte[] get(String artifactKey) {
        // Global lock
        globalLock.lock();

        // Check cache
        byte[] cacheResult = cache.get(artifactKey);
        if (cacheResult != null) {
            globalLock.unlock();
            return cacheResult;
        }

        // Check inFlight
        CompletableFuture<byte[]> future = inFlight.get(artifactKey);
        if (future == null) {
            future = CompletableFuture.supplyAsync(() -> {
                byte[] b = null;
                try {
                    b = fetchFromStore(artifactKey);
                }
                catch (Exception e) {
                    System.out.println("fetch ex: " + e);
                }
                return b;
            });
            inFlight.put(artifactKey, future);
        }

        // global unlock
        globalLock.unlock();

        // block on future (first or later calls to this key)
        byte[] result;
        try {
            result = future.join();
        }
        catch (Exception e) {
            result = null;
        }

        // lock for publishing success or failure
        globalLock.lock();

        // no longer inflight, only remove matching attempt
        if (inFlight.get(artifactKey) == future) {
            inFlight.remove(artifactKey);
        }
        // cache success
        if (result != null) {
            cache.put(artifactKey, result);
        }

        // unlock
        globalLock.unlock();

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentArtifact c = new ConcurrentArtifact();

        // success
        Thread get1 = new Thread(() -> {
            byte[] r1 = c.get("t1");
            System.out.println("r1 = " + r1 + " : " + Arrays.toString(r1));
        });
        get1.start();
        Thread get2 = new Thread(() -> {
            byte[] r2 = c.get("t1");
            System.out.println("r2 = " + r2 + " : " + Arrays.toString(r2));
        });
        get2.start();
        get1.join();
        get2.join();

        // non concurrent check after success - from cache
        byte[] r3 = c.get("t1");
        System.out.println("r3 = " + r3 + " : " + Arrays.toString(r3));

        // failure
        get1 = new Thread(() -> {
            byte[] r1 = c.get("t2");
            System.out.println("2 r1 = " + r1 + " : " + Arrays.toString(r1));
        });
        get1.start();
        get2 = new Thread(() -> {
            byte[] r2 = c.get("t2");
            System.out.println("2 r2 = " + r2 + " : " + Arrays.toString(r2));
        });
        get2.start();
        get1.join();
        get2.join();

        // non concurrent check after failure - retry
        r3 = c.get("t2");
        System.out.println("2 r3 = " + r3 + " : " + Arrays.toString(r3));

        r3 = c.get("t3");
        System.out.println("3 r3 = " + r3 + " : " + Arrays.toString(r3));
        r3 = c.get("t3");
        System.out.println("3s r3 = " + r3 + " : " + Arrays.toString(r3));
    }
}