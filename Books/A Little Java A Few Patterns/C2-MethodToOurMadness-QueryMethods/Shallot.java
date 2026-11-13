public class Shallot extends Kebab {
    private Kebab k;

    public Shallot(Kebab k) {
        this.k = k;
    }
    public boolean isVeggie() {
        return k.isVeggie();
    }
    public Object whatHolder() {
        return k.whatHolder();
    }
    public String toString() {
        return "Shallot, " + k.toString();
    }
}