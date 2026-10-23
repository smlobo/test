import java.util.ArrayList;
import java.util.List;

class GenericCopy {
    static class Animal {
        final String name;

        Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" + name + ")";
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

    static <T> void copy(List<? super T> destination, 
        List<? extends T> source) {

        if (destination.size() < source.size()) {
            throw new IndexOutOfBoundsException("Destination is too small");
        }

        for (int i = 0; i < source.size(); i++) {
            destination.set(i, source.get(i));
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4));
        System.out.println("nums = " + nums);
        List<Number> dest = new ArrayList<>(List.of(11, 22, 33, 44));
        System.out.println("before dest = " + dest);
        copy(dest, nums);
        System.out.println("after dest = " + dest);

        dest = new ArrayList<>(List.of(1.1, 2.2, 3.3, 4.4));
        System.out.println("before dest = " + dest);
        copy(dest, nums);
        System.out.println("after dest = " + dest);

        List<Cat> cats = List.of(new Cat("Milo"), new Cat("Luna"));
        List<Animal> animals = new ArrayList<>(List.of(
                new Dog("Rex"), new Dog("Spot")));
        copy(animals, cats);
        System.out.println("after animals = " + animals);

        List<Dog> dogs = new ArrayList<>(List.of(
                new Dog("Rex"), new Dog("Spot")));
        // copy(dogs, cats); // Does not compile: a Cat is not a Dog.
        System.out.println("dogs = " + dogs);
    }
}
