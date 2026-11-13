public class ChassisD extends CarD {
    public CarD add(CarPartI carPartI, CarPartI before) {
        return addFunction.forChassis(carPartI, before);
    }
    public CarD remove(CarPartI carPartI) {
        return removeFunction.forChassis(carPartI);
    }
    public String toString() {
        return "ChassisD";
    }
}