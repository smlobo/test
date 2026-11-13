public class CheeseD implements PizzaD {
    private PizzaD p;
    public CheeseD(PizzaD p) {
        this.p = p;
    }
    public PizzaD removeAnchovies() {
        return new CheeseD(p.removeAnchovies());
    }
    public PizzaD addCheeseToAnchovies() {
        return new CheeseD(p.addCheeseToAnchovies());
    }
    public PizzaD substituteSausagesWithCheese() {
        return new CheeseD(p.substituteSausagesWithCheese());
    }
    public String toString() {
        return "CheeseD, " + p.toString();
    }
}
