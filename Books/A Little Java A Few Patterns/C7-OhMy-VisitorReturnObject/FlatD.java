public class FlatD extends TreeD {
    private FruitD fruit;
    private TreeD tree;
    public FlatD(FruitD fruit, TreeD tree) {
        this.fruit = fruit;
        this.tree = tree;
    }
    public Object accept(TreeVisitorI ask) {
        return ask.forFlat(fruit, tree);
    }
    public String toString() {
        return "Flat {" + fruit + "}, " + tree;
    }
}