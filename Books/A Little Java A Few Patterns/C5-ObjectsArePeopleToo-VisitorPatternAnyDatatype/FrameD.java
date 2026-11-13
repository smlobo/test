public class FrameD extends CarD {
    private CarPartI carPartI;
    private CarD carD;
    public FrameD(CarPartI carPartI, CarD carD) {
        this.carPartI = carPartI;
        this.carD = carD;
    }
    public CarD add(CarPartI carPartI, CarPartI before) {
        return addFunction.forFrame(this.carPartI, carD, carPartI, before);
    }
    public CarD remove(CarPartI carPartI) {
        return removeFunction.forFrame(this.carPartI, carD, carPartI);
    }
    public String toString() {
        return "FrameD {" + carPartI + "}, " + carD;
    }
}
