public class IsSplitV implements TreeVisitorI {
    public Object forBud() {
        return new Boolean(true);
    }
    public Object forFlat(FruitD fruit, TreeD tree) {
        return new Boolean(false);
    }
    public Object forSplit(TreeD left, TreeD right) {
        return ((Boolean) left.accept(this)).booleanValue() && 
            ((Boolean) right.accept(this)).booleanValue();
    }
}