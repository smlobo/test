public class RemoveV implements PieVisitorI {
    private ToppingD remove;
    public RemoveV(ToppingD remove) {
        this.remove = remove;
    }
    public PieD forBottom() {
        return new BottomD();
    }
    public PieD forTop(ToppingD topping, PieD pie) {
        if (remove.getClass() == topping.getClass()) {
            return pie.accept(this);
        }
        else {
            return new TopD(topping, pie.accept(this));
        }
    }
}