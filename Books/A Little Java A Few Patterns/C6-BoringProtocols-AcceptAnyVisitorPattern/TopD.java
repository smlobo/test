public class TopD extends PieD {
    private ToppingD topping;
    private PieD pie;
    public TopD(ToppingD topping, PieD pie) {
        this.topping = topping;
        this.pie = pie;
    }
    public PieD accept(PieVisitorI ask) {
        return ask.forTop(topping, pie);
    }
    public String toString() {
        return "Top {" + topping + "}; " + pie;
    }
}