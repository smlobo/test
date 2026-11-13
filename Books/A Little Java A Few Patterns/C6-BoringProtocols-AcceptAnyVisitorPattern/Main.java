public class Main {
    public static void main(String[] args) {
        PieD p1 = 
            new TopD(new PepperoniD(), 
                new TopD(new BasilD(), 
                    new TopD(new OnionD(), 
                        new BottomD())));
        System.out.println("p1: " + p1);
        System.out.println("p1.accept(new RemoveV(new BasilD())): " + 
            p1.accept(new RemoveV(new BasilD())));
        System.out.println("p1.accept(new ReplaceV(new OnionD(), new PepperoniD())): " + 
            p1.accept(new ReplaceV(new OnionD(), new PepperoniD())));
    }
}
