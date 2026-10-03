public class Hydrangea implements Deciduous {
    private double height;

    public Hydrangea(double h) {
        height = h;
    }

    public double height() {
        return height;
    }

    public int woodGrain() {
        return 0;
    }

    public boolean isWoody() {
        return false;
    }
}