public class BottomD extends PieD {
    public PieD accept(PieVisitorI ask) {
        return ask.forBottom();
    }
    public String toString() {
        return "Bottom";
    }
}