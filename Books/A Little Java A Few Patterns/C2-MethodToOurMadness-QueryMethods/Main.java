public class Main {
    public static void main(String[] args) {
        // 1
        Kebab k1 = 
            new Shrimp(
                new Raddish(
                    new Shallot(
                        new Holder(new Skewer()))));
        System.out.println("k1: " + k1);
        System.out.println("k1 isVeggie? " + k1.isVeggie());
        System.out.println("k1 Holder: " + k1.whatHolder());

        // 2
        Kebab k2 = 
            new Shallot(
                new Raddish(
                    new Shallot(
                        new Holder(new Sword()))));
        System.out.println("k2: " + k1);
        System.out.println("k2 isVeggie? " + k2.isVeggie());
        System.out.println("k2 Holder: " + k2.whatHolder());
    }
}