public class Main {
    public static void main(String[] args) {
        TreeVisitorI v1 = new IsFlatV();
        System.out.println("v1: " + v1);
        TreeVisitorI v2 = new IsSplitV();
        System.out.println("v2: " + v2);
        TreeVisitorI v3 = new SubstituteV(new AppleD(), new PearD());
        System.out.println("v3: " + v3);

        TreeD t1 = 
            new FlatD(new AppleD(), 
                new SplitD(
                    new BudD(), 
                    new FlatD(new PearD(), 
                        new BudD())));
        System.out.println("t1: " + t1);
        System.out.println("t1.accept(v1): " + t1.accept(v1));
        System.out.println("t1.accept(v2): " + t1.accept(v2));
        System.out.println("t1.accept(v3): " + t1.accept(v3));

        TreeD t2 = 
            new SplitD(
                new SplitD(
                    new FlatD(new OrangeD(), 
                        new BudD()), 
                    new FlatD(new CherryD(), 
                        new BudD())), 
                new SplitD(
                    new FlatD(new AppleD(), 
                        new BudD()), 
                    new FlatD(new PearD(), 
                        new BudD())));
        System.out.println("t2: " + t2);
        System.out.println("t2.accept(v1): " + t2.accept(v1));
        System.out.println("t2.accept(v2): " + t2.accept(v2));
        System.out.println("t2.accept(v3): " + t2.accept(v3));

        TreeD t3 = 
            new FlatD(new OrangeD(), 
                new FlatD(new CherryD(), 
                    new FlatD(new PearD(), 
                        new FlatD(new AppleD(), 
                            new BudD()))));
        System.out.println("t3: " + t3);
        System.out.println("t3.accept(v1): " + t3.accept(v1));
        System.out.println("t3.accept(v2): " + t3.accept(v2));
        System.out.println("t3.accept(v3): " + t3.accept(v3));

        TreeD t4 = 
            new SplitD(
                new SplitD(new BudD(), new BudD()), 
                new SplitD(new BudD(), new BudD()));
        System.out.println("t4: " + t4);
        System.out.println("t4.accept(v1): " + t4.accept(v1));
        System.out.println("t4.accept(v2): " + t4.accept(v2));
        System.out.println("t4.accept(v3): " + t4.accept(v3));
    }
}
