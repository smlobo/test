public class AddV {
    public CarD forChassis(CarPartI add, CarPartI before) {
        return new ChassisD();
    }
    public CarD forFrame(CarPartI original, CarD car, CarPartI add, 
        CarPartI before) {
        if (original.getClass() == before.getClass())
            return new FrameD(add, 
                new FrameD(original, car.add(add, before)));
        else
            return new FrameD(original, car.add(add, before));
    }
}