import java.util.ArrayList;
import java.util.List;

public class ArtifactGraph {
    ArrayList<Node> nodes;

    static enum State {
        UNSEEN,
        VISITING,
        COMPLETED
    }

    public static class Node {
        String artifactName;
        ArrayList<Node> dependencies;
        State state;

        public Node(String name) {
            this.artifactName = name;
            this.dependencies = new ArrayList<>();
            this.state = State.UNSEEN;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(artifactName + "[" + state + "] {");
            for (Node dep : dependencies) {
                sb.append(dep.artifactName + ", ");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public ArtifactGraph() {
        this.nodes = new ArrayList<>();
    }

    // return true if cycle detected
    private boolean dfs_postorder(List<Node> list, Node node) {
        // base cases
        // completed
        if (node.state == State.COMPLETED) {
            return false;
        }
        // cycle
        if (node.state == State.VISITING) {
            return true;
        }

        // mark
        node.state = State.VISITING;

        // post order
        for (Node dep : node.dependencies) {
            if (dfs_postorder(list, dep)) {
                return true;
            }
        }

        list.add(node);
        node.state = State.COMPLETED;

        return false;
    }

    public List<Node> order() {
        // reset previous graph state
        reset();
        List<Node> list = new ArrayList<>();

        // run dfs on every node
        for (Node node : nodes) {
            // cycle - return null
            if (dfs_postorder(list, node)) {
                return null;
            }
        }
        return list;
    }

    public void reset() {
        for (Node node : nodes) {
            node.state = State.UNSEEN;
        }
    }

    public static void main(String[] args) {
        ArtifactGraph ag = new ArtifactGraph();
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        a.dependencies.add(b);
        a.dependencies.add(d);
        b.dependencies.add(c);
        b.dependencies.add(d);
        ag.nodes.add(a);
        ag.nodes.add(b);
        ag.nodes.add(c);
        ag.nodes.add(d);

        List<Node> deployOrder = ag.order();
        if (deployOrder == null) {
            System.out.println("cycle detected");
        } else {
            System.out.println("Order: " + deployOrder);
        }

        // cycle
        d.dependencies.add(a);
        deployOrder = ag.order();
        if (deployOrder == null) {
            System.out.println("cycle detected");
        } else {
            System.out.println("Order: " + deployOrder);
        }
    }
}