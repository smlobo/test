import java.util.ArrayDeque;

public class TestArrayDeque {
  private static void printAD(Iterable<Integer> x) {
    for (int i : x)
      System.out.print(i + ":");
  }

  public static void main(String[] args) {
    ArrayDeque<Integer> ad = new ArrayDeque<>();

    ad.push(10);
    ad.push(20);
    ad.push(30);
    ad.push(40);

    printAD(ad);

    System.out.println("Popping: " + ad.pop());
    printAD(ad);
  }
}

