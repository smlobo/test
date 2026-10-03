

public class MultiplesThreeFive {
    public static void main(String[] args) {
        int threeMultiple = 3;
        int fiveMultiple = 5;
        int sum = 0;
        int nextMultiple = 0;
        while (nextMultiple < 1000) {
            sum += nextMultiple;
            if (threeMultiple < fiveMultiple) {
                nextMultiple = threeMultiple;
                threeMultiple += 3;
            }
            else if (threeMultiple > fiveMultiple) {
                nextMultiple = fiveMultiple;
                fiveMultiple += 5;
            }
            else {
                nextMultiple = fiveMultiple;
                threeMultiple += 3;
                fiveMultiple += 5;                
            }
        }
        System.out.println(sum);
    }
}