public class BudD extends TreeD {
    public Object accept(TreeVisitorI ask) {
        return ask.forBud();
    }
    public String toString() {
        return "Bud";
    }
}