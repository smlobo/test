public class SubstituteV implements TreeVisitorI {
    private FruitD out;
    private FruitD in;
    public SubstituteV(FruitD out, FruitD in) {
        this.out = out;
        this.in = in;
    }
    public Object forBud() {
        return new BudD();
    }
    public Object forFlat(FruitD fruit, TreeD tree) {
        if (fruit.getClass() == out.getClass())
            return new FlatD(in, (TreeD) tree.accept(this));
        else
            return new FlatD(fruit, (TreeD) tree.accept(this));
    }
    public Object forSplit(TreeD left, TreeD right) {
        return new SplitD((TreeD) left.accept(this), (TreeD) right.accept(this));
    }
}