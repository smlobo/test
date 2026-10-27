import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashMap;


public class ArtifactReserve {
    class TeamData {
        long currentCapacity;
        HashMap<String, Long> reserveRequests;
        public TeamData(long initialCapacity) {
            this.currentCapacity = initialCapacity;
            this.reserveRequests = new HashMap<>();
        }
    }

    long initialCapacity;
    Lock lock;
    HashMap<String, TeamData> teamMap;
    HashMap<String, String> requestMap;

    public ArtifactReserve(long i) {
        this.initialCapacity = i;
        this.lock = new ReentrantLock();
        this.teamMap = new HashMap<>();
        this.requestMap = new HashMap<>();
    }

    public boolean reserve(String team, String requestId, long bytes) throws IllegalAccessException {
        if (bytes < 0 || bytes > initialCapacity) {
            throw new IllegalAccessException("reserve() bad reservation");
        }
        lock.lock();

        try {
            String existingTeam = requestMap.get(requestId);
            if (existingTeam != null) {
                throw new IllegalAccessException("reserve() reused request id");
            }
            TeamData data = teamMap.get(team);
            if (data == null) {
                teamMap.put(team, new TeamData(initialCapacity));
                data = teamMap.get(team);
            }
            if (data.currentCapacity < bytes) {
                return false;
            }
            data.currentCapacity -= bytes;
            data.reserveRequests.put(requestId, bytes);
            requestMap.put(requestId, team);
            return true;
        }
        finally {
            lock.unlock();
        }
    }

    public void upload(String requestId, long actualBytes) throws IllegalAccessException {
        if (actualBytes < 0 || actualBytes > initialCapacity) {
            throw new IllegalAccessException("upload() bad actualBytes");
        }
        commit(requestId, actualBytes);
    }

    public void commit(String requestId, long actualBytes) throws IllegalAccessException {
        if (actualBytes < 0 || actualBytes > initialCapacity) {
            throw new IllegalAccessException("commit() bad actualBytes");
        }
        lock.lock();

        try {
            String team = requestMap.get(requestId);
            if (team == null) {
                throw new IllegalAccessException("commit() reserved request not found");
            }
            TeamData data = teamMap.get(team);
            if (data == null) {
                throw new IllegalAccessException("commit() team not found");
            }
            Long reservedCapacity = data.reserveRequests.get(requestId);
            if (reservedCapacity == null) {
                throw new IllegalAccessException("commit() team request not found");
            }
            if (actualBytes > reservedCapacity) {
                throw new IllegalAccessException("commit() actual bytes not < reserved");
            }
            long unusedCapacity = reservedCapacity - actualBytes;
            data.currentCapacity += unusedCapacity;
            data.reserveRequests.remove(requestId);
            requestMap.remove(requestId);
        }
        finally {
            lock.unlock();
        }
    }

    public void cancel(String requestId) throws IllegalAccessException {
        lock.lock();

        try {
            String team = requestMap.get(requestId);
            if (team == null) {
                throw new IllegalAccessException("cancel() reserved request not found");
            }
            TeamData data = teamMap.get(team);
            if (data == null) {
                throw new IllegalAccessException("cancel() team not found");
            }
            Long reservedCapacity = data.reserveRequests.get(requestId);
            if (reservedCapacity == null) {
                throw new IllegalAccessException("upload() team request not found");
            }
            data.currentCapacity += reservedCapacity;
            data.reserveRequests.remove(requestId);
            requestMap.remove(requestId);
        }
        finally {
            lock.unlock();
        }
    }

    public long available(String team) throws IllegalAccessException {
        lock.lock();

        try {
            TeamData data = teamMap.get(team);
            if (data == null) {
                throw new IllegalAccessException("available() team not found");
            }
            return data.currentCapacity;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        ArtifactReserve ar = new ArtifactReserve(1000);
        System.out.println(ar.reserve("A", "r1", 400));
        System.out.println(ar.available("A"));
        ar.upload("r1", 350);
        System.out.println(ar.available("A"));
        System.out.println(ar.reserve("A", "r2", 200));
        System.out.println(ar.available("A"));
        ar.cancel("r2");
        System.out.println(ar.available("A"));

        try {
            ar.commit("r3", 100);
        }
        catch (Exception e) {
            System.out.println("bad request id commit(): " + e);
        }

        try {
            ar.reserve("A", "r3", -100);
        }
        catch (Exception e) {
            System.out.println("bad size reserve(): " + e);
        }

        System.out.println(ar.reserve("A", "r3", 100));
        System.out.println(ar.available("A"));
        try {
            ar.upload("r3", 101);
        }
        catch (Exception e) {
            System.out.println("bad size reserve(): " + e);
        }
        System.out.println(ar.available("A"));
    }
}