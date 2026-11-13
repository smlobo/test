public class RemoveV {
    public CarD forChassis(CarPartI remove) {
        return new ChassisD();
    }
    public CarD forFrame(CarPartI original, CarD car, CarPartI remove) {
        if (original.getClass() == remove.getClass())
            return car.remove(remove);
        else
            return new FrameD(original, car.remove(remove));
    }
}