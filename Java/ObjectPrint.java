public class ObjectPrint {
    public static void main(String... args) {
        String x = new String("xxx");
        Integer y = new Integer(10);

        System.out.println("x = " + x + " - " + (Object) x);
        System.out.println("y = " + y + " - " + (Object) y);

        HelloWorld hw = new HelloWorld();
        System.out.println("hw = " + hw + " - " + (Object) hw);        
    }
}