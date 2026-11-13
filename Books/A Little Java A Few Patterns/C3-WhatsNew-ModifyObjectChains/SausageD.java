public class SausageD implements PizzaD {
    private PizzaD p;
    public SausageD(PizzaD p) {
        this.p = p;
    }
    public PizzaD removeAnchovies() {
        return new SausageD(p.removeAnchovies());
    }
    public PizzaD addCheeseToAnchovies() {
        return new SausageD(p.addCheeseToAnchovies());
    }
    public PizzaD substituteSausagesWithCheese() {
        return new CheeseD(p.substituteSausagesWithCheese());
    }
    public String toString() {
        return "SausageD, " + p.toString();
    }
}
