public class Holly implements Evergreen {
    private double height;

    public Holly(double h) {
        height = h;
    }

    public double height() {
        return height;
    }

    public int woodGrain() {
        return 5;
    }

    public boolean hasCones() {
        return false;
    }
}