import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomHash {
    final static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Person)) return false;
            Person that = (Person) obj;
            return age == that.age && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return 31 * Objects.hashCode(name) + Integer.hashCode(age);
        }

        @Override
        public String toString() {
            return "{" + name + "," + age + "}";
        }
    }

    public static void main(String[] args) {
        Map<Person, String> map = new HashMap<>();
        Person p = new Person("Ada", 36);
        map.put(p, "found");
        System.out.println(p + " -> " + map.get(p));
        p = new Person("Bob", 30);
        System.out.println(p + " -> " + map.get(p));
    }
}
