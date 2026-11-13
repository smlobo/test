public class Main {
    public static void main(String[] args) {
        CarD c1 = 
            new FrameD(new HeadlightsD(), 
                new FrameD(new WheelsD(), 
                    new FrameD(new WindshieldD(), 
                        new ChassisD())));
        System.out.println("c1: " + c1);
        System.out.println("c1.remove(new WheelsD()): " + c1.remove(new WheelsD()));
        System.out.println("c1.add(new WheelsD(), new HeadlightsD()): " + 
            c1.add(new WheelsD(), new HeadlightsD()));
    }
}
