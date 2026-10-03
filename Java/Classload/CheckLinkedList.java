public class CheckLinkedList {
    public static void main(String[] args) {
        System.out.println("Checking String class loaded ...");
        ClassLoader system = ClassLoader.getSystemClassLoader();
        try {
            Class stringClass = system.findClass("java.lang.String");
            System.out.println("Found String class: " + stringClass);
        }
        catch (Exception e) {
            System.out.println("While finding String, caught: " + e);
        }

    }
}