public class Array {
    public static void main(String[] args) {
        System.out.printf("Hello : %s\n", args[0]);
        int[] myIntArray = new int[Integer.parseInt(args[0])];
        for (int i = 0; i < myIntArray.length; i++) {
            myIntArray[i] = i * 10;
            System.out.println("Set myIntArray[" + i + "] to: " + myIntArray[i]);
        }
    }
}