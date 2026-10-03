public class Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
    public final void testFinalMethod() {
        System.out.println("The final method in Animal");
    }
    private void testPrivateMethod() {
        System.out.println("The private instance method in Animal");
    }
    protected void testProtectedMethod() {
        System.out.println("The protected instance method in Animal");
    }
    void testDefaultMethod() {
        System.out.println("The default access modifier instance method in Animal");
    }
}
