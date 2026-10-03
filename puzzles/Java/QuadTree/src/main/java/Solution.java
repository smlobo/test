import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Solution {
    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight,
                    Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    public static final Node trueLeafNode =
            new Node(true, true, null, null, null, null);
    public static final Node falseLeafNode =
            new Node(false, true, null, null, null, null);

    public static Node intersect(Node quadTree1, Node quadTree2) {
        // Null Nodes not permitted
        if (quadTree1 == null || quadTree2 == null) {
            return null;
        }

        Node topLeftResult = null;
        Node topRightResult = null;
        Node bottomLeftResult = null;
        Node bottomRightResult = null;

        // Both are leafs
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            boolean valResult = quadTree1.val || quadTree2.val;
            if (valResult) {
                return trueLeafNode;
            }
            else {
                return falseLeafNode;
            }
        }

        else if (quadTree1.isLeaf) {
            Node tree1Child = quadTree1.val ? trueLeafNode : falseLeafNode;
            topLeftResult = intersect(tree1Child, quadTree2.topLeft);
            topRightResult = intersect(tree1Child, quadTree2.topRight);
            bottomLeftResult = intersect(tree1Child, quadTree2.bottomLeft);
            bottomRightResult = intersect(tree1Child, quadTree2.bottomRight);
        }
        else if (quadTree2.isLeaf) {
            Node tree2Child = quadTree2.val ? trueLeafNode : falseLeafNode;
            topLeftResult = intersect(tree2Child, quadTree1.topLeft);
            topRightResult = intersect(tree2Child, quadTree1.topRight);
            bottomLeftResult = intersect(tree2Child, quadTree1.bottomLeft);
            bottomRightResult = intersect(tree2Child, quadTree1.bottomRight);
        }
        else {
            topLeftResult = intersect(quadTree1.topLeft, quadTree2.topLeft);
            topRightResult = intersect(quadTree1.topRight, quadTree2.topRight);
            bottomLeftResult = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            bottomRightResult = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        }

        // If all results are the same, short circuit
        if (topLeftResult.isLeaf && topRightResult.isLeaf && bottomLeftResult.isLeaf && bottomRightResult.isLeaf) {
            if (topLeftResult.val && topRightResult.val && bottomLeftResult.val && bottomRightResult.val) {
                return trueLeafNode;
            } else if (!(topLeftResult.val || topRightResult.val || bottomLeftResult.val || bottomRightResult.val)) {
                return falseLeafNode;
            }
        }

        return new Node(false, false, topLeftResult, topRightResult, bottomLeftResult, bottomRightResult);
    }

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String qT1 = "{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":\"true\",\"topLeft\":null," +
                "\"topRight\":null,\"val\":\"true\"}";
        Node qT1Node = objectMapper.readValue(qT1, Node.class);
        String qT2 = "{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":\"true\",\"topLeft\":null," +
                "\"topRight\":null,\"val\":\"false\"}";
        Node qT2Node = objectMapper.readValue(qT2, Node.class);
        Node qTR = intersect(qT1Node, qT2Node);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(qTR));

        qT1 = "{  \"bottomLeft\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":true},\n" +
                "  \"bottomRight\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":true},\n" +
                "  \"isLeaf\":false,\n" +
                "  \"topLeft\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":true},\n" +
                "  \"topRight\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":true},\n" +
                "  \"val\":false}";
        qT1Node = objectMapper.readValue(qT1, Node.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(qT1Node));
        qT2 = "{\"bottomLeft\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":false},\n" +
                "  \"bottomRight\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":false},\n" +
                "  \"isLeaf\":false,\n" +
                "  \"topLeft\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":false},\n" +
                "  \"topRight\":{\"bottomLeft\":null,\"bottomRight\":null,\"isLeaf\":true,\"topLeft\":null,\"topRight\":null,\"val\":false},\n" +
                "  \"val\":false\n}";
        qT2Node = objectMapper.readValue(qT2, Node.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(qT2Node));
        qTR = intersect(qT1Node, qT2Node);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(qTR));


    }
}
