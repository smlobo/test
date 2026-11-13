public class CrustD implements PizzaD {
    public PizzaD removeAnchovies() {
        return new CrustD();
    }
    public PizzaD addCheeseToAnchovies() {
        return new CrustD();
    }
    public PizzaD substituteSausagesWithCheese() {
        return new CrustD();
    }
    public String toString() {
        return "CrustD";
    }
}
