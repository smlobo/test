public class Bar {
    public int foo(int param) {
        return 5;
    }
    public static void main(String[] args) {
        Bar b = new Bar();
        System.out.println("foo: " + b.foo(1));
    }
}