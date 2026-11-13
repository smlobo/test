public class AnchovyD implements PizzaD {
    private PizzaD p;
    public AnchovyD(PizzaD p) {
        this.p = p;
    }
    public PizzaD removeAnchovies() {
        return p.removeAnchovies();
    }
    public PizzaD addCheeseToAnchovies() {
        return new CheeseD(new AnchovyD(p.addCheeseToAnchovies()));
    }
    public PizzaD substituteSausagesWithCheese() {
        return new AnchovyD(p.substituteSausagesWithCheese());
    }
    public String toString() {
        return "AnchovyD, " + p.toString();
    }
}
