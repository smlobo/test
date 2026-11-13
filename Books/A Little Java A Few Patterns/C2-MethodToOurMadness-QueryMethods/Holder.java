public class Holder extends Kebab {
    private Object o;

    public Holder(Object o) {
        this.o = o;
    }
    public boolean isVeggie() {
        return true;
    }
    public Object whatHolder() {
        return o;
    }
    public String toString() {
        return "Holder : " + o.toString();
    }
}