import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;

public class GenericMax {
    static class Animal implements Comparable<Animal> {
        final String name;

        Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + name + ")";
        }

        @Override
        public int compareTo(Animal that) {
            return name.compareTo(that.name);
        }
    }

    static class Cat extends Animal {
        Cat(String name) {
            super(name);
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            super(name);
        }
    }

    static <T extends Comparable<? super T>>
    T max(List<? extends T> items) {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("empty list");
        }

        Iterator<? extends T> itr = items.iterator();
        T max = itr.next();
        while (itr.hasNext()) {
            T item = itr.next();
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        List<Integer> il = new ArrayList<>(List.of(5, 3, 7, 1));
        Integer im = max(il);
        System.out.println("max of: " + il + " = " + im);

        List<String> sl = new ArrayList<>(List.of("p", "w", "c"));
        String sm = max(sl);
        System.out.println("max of: " + sl + " = " + sm);

        List<Animal> al = new ArrayList<>(List.of(new Dog("r"), new Cat("g"), 
            new Dog("u")));
        Animal am = max(al);
        System.out.println("max of: " + al + " = " + am);

        List<Cat> cl = new ArrayList<>(List.of(new Cat("r"), new Cat("g")));
        Cat cm = max(cl);
        System.out.println("max of: " + cl + " = " + cm);
    }
}