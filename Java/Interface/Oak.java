public class Oak implements Deciduous {
    private double height;

    public Oak(double h) {
        height = h;
    }
    
    public double height() {
        return height;
    }

    public int woodGrain() {
        return 10;
    }

    public boolean isWoody() {
        return true;
    }
}