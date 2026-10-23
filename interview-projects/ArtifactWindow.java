import java.util.ArrayDeque;
import java.util.HashMap;

public class ArtifactWindow {
    HashMap<String, ArrayDeque<Integer>> queues;
    int maxRequests;
    int windowDuration;

    public ArtifactWindow(int mR, int wD) {
        this.maxRequests = mR;
        this.windowDuration = wD;
        this.queues = new HashMap<>();
    }

    public boolean allow(String teamId, int timestamp) {
        // Get the q for the team
        if (!queues.containsKey(teamId)) {
            queues.put(teamId, new ArrayDeque<>());
        }
        ArrayDeque<Integer> teamQueue = queues.get(teamId);

        // queue not full
        if (teamQueue.size() < maxRequests) {
            teamQueue.addLast(timestamp);
            return true;
        }

        // queue full, check for expiry of the oldest event
        int oldest = teamQueue.peekFirst();
        if (oldest <= (timestamp - windowDuration)) {
            teamQueue.removeFirst();
            teamQueue.addLast(timestamp);
            return true;
        }

        // not expired
        return false;
    }

    public static void main(String[] args) {
        ArtifactWindow aw = new ArtifactWindow(3, 10);
        System.out.println("A, 0 = " + aw.allow("A", 0));
        System.out.println("A, 2 = " + aw.allow("A", 2));
        System.out.println("A, 4 = " + aw.allow("A", 4));
        System.out.println("A, 10 = " + aw.allow("A", 10));
        System.out.println("A, 11 = " + aw.allow("A", 11));
        System.out.println("A, 12 = " + aw.allow("A", 12));
        System.out.println("B, 0 = " + aw.allow("B", 0));
        System.out.println("B, 0 = " + aw.allow("B", 0));
        System.out.println("B, 0 = " + aw.allow("B", 0));
        System.out.println("B, 0 = " + aw.allow("B", 0));
        System.out.println("B, 10 = " + aw.allow("B", 10));
        System.out.println("B, 10 = " + aw.allow("B", 10));
    }
}