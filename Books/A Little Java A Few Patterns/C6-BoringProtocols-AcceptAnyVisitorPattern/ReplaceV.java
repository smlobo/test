public class ReplaceV implements PieVisitorI {
    private ToppingD out;
    private ToppingD in;
    public ReplaceV(ToppingD out, ToppingD in) {
        this.out = out;
        this.in = in;
    }
    public PieD forBottom() {
        return new BottomD();
    }
    public PieD forTop(ToppingD topping, PieD pie) {
        if (out.getClass() == topping.getClass()) {
            return new TopD(in, pie.accept(this));
        }
        else {
            return new TopD(topping, pie.accept(this));
        }
    }
}