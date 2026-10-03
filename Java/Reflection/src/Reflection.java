import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {

    public int foo() {
        return 100;
    }

    public int[] bar() {
        return new int[] {10, 20, 30};
    }

    public static void main(String[] args) {
        Integer x = 10;
        System.out.println("x = " + x + "; it is: " + x.getClass().getName());

        Reflection r = new Reflection();
        System.out.println("r.foo() -> " + r.foo());
        System.out.println("r.bar() -> " + Arrays.toString(r.bar()));

        Class<Reflection> reflectionClass = Reflection.class;
        Method[] reflectionMethods = reflectionClass.getMethods();
        for (Method method : reflectionMethods) {
            System.out.println("Method: " + method.getName());
            System.out.println("  returns: " + method.getReturnType().getName());
        }
    }
}
