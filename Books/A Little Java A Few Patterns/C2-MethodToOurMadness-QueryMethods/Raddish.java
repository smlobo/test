public class Raddish extends Kebab {
    private Kebab k;

    public Raddish(Kebab k) {
        this.k = k;
    }
    public boolean isVeggie() {
        return k.isVeggie();
    }
    public Object whatHolder() {
        return k.whatHolder();
    }
    public String toString() {
        return "Raddish, " + k.toString();
    }
}