public interface TreeVisitorI {
    public Object forBud();
    public Object forFlat(FruitD fruit, TreeD tree);
    public Object forSplit(TreeD left, TreeD right);
}