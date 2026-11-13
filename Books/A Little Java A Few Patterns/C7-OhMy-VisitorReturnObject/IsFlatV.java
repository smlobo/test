public class IsFlatV implements TreeVisitorI {
    public Object forBud() {
        return new Boolean(true);
    }
    public Object forFlat(FruitD fruit, TreeD tree) {
        return tree.accept(this);
    }
    public Object forSplit(TreeD left, TreeD right) {
        return new Boolean(false);
    }
}