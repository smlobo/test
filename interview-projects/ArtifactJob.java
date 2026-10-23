import java.lang.Runtime.Version;
import java.util.HashMap;
import java.util.Objects;

public class ArtifactJob {
    static enum State {
        STARTED,
        SUPERCEDED,
        BECAME_CURRENT
    }

    class VersionMetadata {
        String requestId;
        int version;
        State state;
        String digest;
        VersionMetadata(String rId) {
            this.requestId = rId;
            this.state = State.STARTED;
        }
    }

    class ArtifactTracker {
        HashMap<String, VersionMetadata> requestMap;
        String currentRequestId;
        int nextVersion;
        ArtifactTracker() {
            this.requestMap = new HashMap<>();
            this.nextVersion = 1;
        }
    }

    class CompleteResult {
        int version;
        State state;
        CompleteResult(int v, State s) {
            this.version = v;
            this.state = s;
        }
        @Override
        public String toString() {
            return "{" + version + "," + state + "}";
        }
    }

    class CurrentResult {
        int version;
        String digest;
        CurrentResult(int v, String d) {
            this.version = v;
            this.digest = d;
        }
    }

    HashMap<String, ArtifactTracker> artifactMap;
    HashMap<String, String> requestArtifact;

    public ArtifactJob() {
        this.artifactMap = new HashMap<>();
        this.requestArtifact = new HashMap<>();
    }

    public int start(String name, String requestId) {
        // get or create this tracker
        ArtifactTracker tracker = artifactMap.get(name);
        if (tracker == null) {
            artifactMap.put(name, new ArtifactTracker());
            tracker = artifactMap.get(name);
        }

        // lookup request id
        VersionMetadata metadata = tracker.requestMap.get(requestId);
        // new request
        if (metadata == null) {
            metadata = new VersionMetadata(requestId);
            metadata.version = tracker.nextVersion;
            tracker.nextVersion++;
            tracker.requestMap.put(requestId, metadata);
        }

        // map of request id
        requestArtifact.put(requestId, name);

        // new or retry version
        return metadata.version;
    }

    public CompleteResult complete(String requestId, String digest) {
        // get the artifact
        String artifactName = requestArtifact.get(requestId);
        // complete() called before start() - error
        if (artifactName == null) {
            throw new IllegalArgumentException("bad requestId");
        }

        // get the artifact tracker for this request (should not be null)
        ArtifactTracker tracker = artifactMap.get(artifactName);

        // now the version metadata for this request (should not be null)
        VersionMetadata metadata = tracker.requestMap.get(requestId);

        // retry
        if (metadata.state != State.STARTED) {
            // mismatch digest
            if (!digest.equals(metadata.digest)) {
                throw new IllegalStateException("mismatch digest");
            }
            // replay
            return new CompleteResult(metadata.version, metadata.state);
        }

        // save digest
        metadata.digest = digest;

        // no current, set current
        if (tracker.currentRequestId == null) {
            metadata.state = State.BECAME_CURRENT;
            tracker.currentRequestId = requestId;
            return new CompleteResult(metadata.version, metadata.state);
        }

        // get the current metadata (should not be null)
        VersionMetadata currentMetadata = tracker.requestMap.get(
            tracker.currentRequestId);

        // superceded
        if (currentMetadata.version > metadata.version) {
            metadata.state = State.SUPERCEDED;
        } else {
            metadata.state = State.BECAME_CURRENT;
            tracker.currentRequestId = requestId;
        }
        return new CompleteResult(metadata.version, metadata.state);
    }

    public CurrentResult current(String name) {
        // tracker
        ArtifactTracker tracker = artifactMap.get(name);

        // artifact not found or no current
        if (tracker == null || tracker.currentRequestId == null) {
            return null;
        }

        VersionMetadata currenMetadata = tracker.requestMap.get(
            tracker.currentRequestId);
        return new CurrentResult(currenMetadata.version, currenMetadata.digest);
    }

    public static void main(String[] args) {
        ArtifactJob j = new ArtifactJob();

        System.out.println("start(x, r1) : " + j.start("x", "r1"));
        System.out.println("start(x, r2) : " + j.start("x", "r2"));
        System.out.println("start(x, r1) : " + j.start("x", "r1"));
        System.out.println("complete(r2, d2) : " + j.complete("r2", "d2"));
        System.out.println("start(x, r1) : " + j.start("x", "r1"));
        System.out.println("complete(r2, d2) : " + j.complete("r2", "d2"));
        System.out.println("complete(r1, d1) : " + j.complete("r1", "d1"));
        System.out.println("complete(r1, d1) : " + j.complete("r1", "d1"));

        System.out.println("start(y, ry1) : " + j.start("y", "ry1"));
        System.out.println("start(y, ry2) : " + j.start("y", "ry2"));
        System.out.println("complete(ry1, d1) : " + j.complete("ry1", "d1"));
        System.out.println("complete(ry2, d2) : " + j.complete("ry2", "d2"));
        System.out.println("complete(ry1, d1) : " + j.complete("ry1", "d1"));

        try {
            System.out.println("complete(r1, dX) : " + j.complete("r1", "dX"));
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }

        try {
            System.out.println("complete(rX, dY) : " + j.complete("rX", "dY"));
        } catch (Exception e) {
            System.out.println("exception: " + e);
        }
    }
}