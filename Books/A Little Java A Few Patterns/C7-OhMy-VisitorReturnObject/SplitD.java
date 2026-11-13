public class SplitD extends TreeD {
    private TreeD left;
    private TreeD right;
    public SplitD(TreeD left, TreeD right) {
        this.left = left;
        this.right = right;
    }
    public Object accept(TreeVisitorI ask) {
        return ask.forSplit(left, right);
    }
    public String toString() {
        return "Split [L: " + left + "][R: " + right + "]";
    }
}