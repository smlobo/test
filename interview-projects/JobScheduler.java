import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;

public class JobScheduler {
    class Job {
        String team;
        String artifact;
        public Job(String t, String a) {
            this.team = t;
            this.artifact = a;
        }
        @Override
        public String toString() {
            return "{" + team + "," + artifact + "}";
        }
    }

    class TeamQueue {
        ArrayDeque<Job> jobQueue;
        Job running;

        public TeamQueue() {
            this.jobQueue = new ArrayDeque<>();
        }
    }

    Lock lock;
    HashMap<String, TeamQueue> teamMap;
    ArrayList<String> roundRobin;
    int nextRoundRobin;
    int jobCounter;
    int maxJobs;

    public JobScheduler(int m) {
        this.lock = new ReentrantLock();
        this.teamMap = new HashMap<>();
        this.roundRobin = new ArrayList<>();
        this.maxJobs = m;
    }

    public void enqueue(String team, String artifact) {
        lock.lock();

        try {
            TeamQueue teamQueue = teamMap.get(team);
            if (teamQueue == null) {
                teamQueue = new TeamQueue();
                teamMap.put(team, teamQueue);
                roundRobin.add(team);
            }
            teamQueue.jobQueue.addLast(new Job(team, artifact));
        }
        finally {
            lock.unlock();
        }
    }

    public Job next() {
        lock.lock();

        try {
            // maxed out jobs
            if (jobCounter == maxJobs) {
                return null;
            }

            // get next eligible round robin
            int rrIterator = nextRoundRobin;
            while (rrIterator < roundRobin.size()) {
                String team = roundRobin.get(rrIterator);
                TeamQueue teamQueue = teamMap.get(team);
                // eligible
                if (!teamQueue.jobQueue.isEmpty() && teamQueue.running == null) {
                    Job eligibleJob = teamQueue.jobQueue.removeFirst();
                    teamQueue.running = eligibleJob;
                    // set next
                    rrIterator++;
                    if (rrIterator == roundRobin.size()) {
                        rrIterator = 0;
                    }
                    nextRoundRobin = rrIterator;

                    jobCounter++;
                    return eligibleJob;
                }
                rrIterator++;
                if (rrIterator == roundRobin.size()) {
                    rrIterator = 0;
                }
                if (rrIterator == nextRoundRobin) {
                    break;
                }       
            }

            // no eligible job
            return null;
        }
        finally {
            lock.unlock();
        }
    }

    public void complete(Job job) throws IllegalAccessException {
        lock.lock();

        try {
            TeamQueue teamQueue = teamMap.get(job.team);
            if (teamQueue == null) {
                throw new IllegalAccessException("complete job not found");
            }
            if (!teamQueue.running.equals(job)) {
                throw new IllegalAccessException("complete job not running");                
            }
            jobCounter--;
            teamQueue.running = null;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        JobScheduler js = new JobScheduler(1);
        js.enqueue("a", "x1");
        js.enqueue("b", "x2");
        Job j1 = js.next();
        System.out.println("[1] next: " + j1);
        System.out.println("[2] next: " + js.next());
        js.complete(j1);
        Job j2 = js.next();
        System.out.println("[2 retry] next: " + j2);
    }
}