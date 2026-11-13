import java.util.Arrays;

public class Bowling {
    private static int index2Score = 0;

    public static int bowlingMemoization(int[] x, int index) {
        // Base case
        if (index == x.length)
            return 0;

        int missScore = bowlingMemoization(x, index+1);
        int hitScore = missScore + x[index];
        int hit2Score = Integer.MIN_VALUE;
        if (index < x.length-1) {
            hit2Score = index2Score + x[index]*x[index+1];
        }

        // Save calculated value of calculated value
        index2Score = missScore;

        int maxScore = (missScore > hitScore) ? missScore : hitScore;
        maxScore = (maxScore > hit2Score) ? maxScore : hit2Score;

        return maxScore;
    }

    public static int bowling(int[] x, int index) {
        // Base case
        if (index == x.length)
            return 0;

        int missScore = bowling(x, index+1);
        int hitScore = missScore + x[index];
        int hit2Score = Integer.MIN_VALUE;
        if (index < x.length-1) {
            hit2Score = bowling(x, index+2) + x[index]*x[index+1];
        }

        int maxScore = (missScore > hitScore) ? missScore : hitScore;
        maxScore = (maxScore > hit2Score) ? maxScore : hit2Score;

        return maxScore;
    }

    public static void main(String[] args) {
        int[] test = new int[] {-1, 1 , 1 , 1 , 9, 9 , 3 , -3, -5 , 2, 2};
        int score = bowling(test, 0);
        int mScore = bowlingMemoization(test, 0);
        System.out.println(Arrays.toString(test) + " = " + score + "/" + mScore);

        test = new int[] {-5, -5, 3, 3};
        score = bowling(test, 0);
        mScore = bowlingMemoization(test, 0);
        System.out.println(Arrays.toString(test) + " = " + score + "/" + mScore);
    }
}