public class StackHeap {
    static class LocalObject {
        int x;
        int y;
    }

    private static void foo() {
        LocalObject fooLO = new LocalObject();
        System.out.println("fooLO: " + fooLO);
    }

    public static void main(String[] args) {
        LocalObject mainLO = new LocalObject();
        System.out.println("mainLO: " + mainLO);
        Integer mainI = new Integer(1);
        System.out.println("mainI: " + mainI.hashCode());
        String mainS = new String("bar");
        System.out.println("mainS: " + mainS.hashCode());
        LocalObject mainLO2 = new LocalObject();
        System.out.println("mainLO2: " + mainLO2);
        foo();
    }
}