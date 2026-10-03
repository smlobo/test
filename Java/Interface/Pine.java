public class Pine implements Evergreen {
    private double height;

    public Pine(double h) {
        height = h;
    }

    public double height() {
        return height;
    }

    public int woodGrain() {
        return 5;
    }

    public boolean hasCones() {
        return true;
    }
}