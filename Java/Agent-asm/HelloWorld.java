public class HelloWorld {
	public void printMsg(String s) {
		System.out.println("Hi - " + s);
	}

    public static void main(String[] args) {
        System.out.println("Hello World");
        HelloWorld hw1 = new HelloWorld();
        hw1.printMsg("Amelia");
        hw1.printMsg("Aparna");
        HelloWorld hw2 = new HelloWorld();
        hw2.printMsg("Sheldon");
        hw2.printMsg("Ryan");
    }
}
