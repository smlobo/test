public class Main {
    public static void main(String[] args) {
        // 2
        PizzaD p1 = 
            new CheeseD(
                new SausageD(
                    new AnchovyD(
                        new SausageD(
                            new CrustD()))));
        System.out.println("p1: " + p1);
        System.out.println("p1.removeAnchovies(): " + p1.removeAnchovies());
        System.out.println("p1.addCheeseToAnchovies(): " + 
            p1.addCheeseToAnchovies());
        System.out.println("p1.substituteSausagesWithCheese(): " + 
            p1.substituteSausagesWithCheese());
        System.out.println("p1 All Cheese!! : " + 
            p1.substituteSausagesWithCheese().addCheeseToAnchovies().
                removeAnchovies());
    }
}
