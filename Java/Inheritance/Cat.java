public class Cat extends Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
        System.out.print("  calling ... ");
        super.testInstanceMethod();
    }
    // public void testFinalMethod() {
    //     System.out.println("The final method in Cat");
    // }
    private void testPrivateMethod() {
        System.out.println("The private instance method in Cat");
    }
    protected void testProtectedMethod() {
        System.out.println("The protected instance method in Cat");
        System.out.print("  calling ... ");
        super.testProtectedMethod();
    }
    void testDefaultMethod() {
        System.out.println("The protected default access modifier method in Cat");
        System.out.print("  calling ... ");
        super.testDefaultMethod();
    }

    public static void main(String[] args) {
        Animal.testClassMethod();
        Cat.testClassMethod();

        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        myAnimal.testInstanceMethod();
        // myAnimal.testPrivateMethod();
        myAnimal.testProtectedMethod();
        myAnimal.testDefaultMethod();

        myAnimal.testFinalMethod();

        Dog myDog = new Dog();  
    }
}
