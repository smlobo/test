public class Willow implements Deciduous {
    private double height;

    public Willow(double h) {
        height = h;
    }

    public double height() {
        return height;
    }

    public int woodGrain() {
        return 3;
    }

    public boolean isWoody() {
        return true;
    }
}