public class Shrimp extends Kebab {
    private Kebab k;

    public Shrimp(Kebab k) {
        this.k = k;
    }
    public boolean isVeggie() {
        return false;
    }
    public Object whatHolder() {
        return k.whatHolder();
    }
    public String toString() {
        return "Shrimp, " + k.toString();
    }
}