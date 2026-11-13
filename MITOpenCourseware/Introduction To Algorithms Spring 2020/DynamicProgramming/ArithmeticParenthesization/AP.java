import java.util.Arrays;

public class AP {
    static class MinMaxTuple {
        int min;
        int max;

        MinMaxTuple(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public String toString() {
            return "<" + min + ":" + max + ">";
        }
    }

    static int calculate(int left, int right, char op) {
        if (op == '+')
            return left + right;
        return left * right;
    }

    static MinMaxTuple arithmeticParenthesization(int[] n, char[] o, int l, int r) {
        // Base case - last operator
        if (l == r) {
            int value = calculate(n[l], n[l+1], o[l]);
            return new MinMaxTuple(value, value);
        }

        MinMaxTuple returnTuple = new MinMaxTuple(Integer.MAX_VALUE, 
            Integer.MIN_VALUE);

        // Iterate over every operator
        for (int i = l; i <= r; i++) {
            MinMaxTuple left = null;
            if (i == l)
                left = new MinMaxTuple(n[i], n[i]);
            else
                left = arithmeticParenthesization(n, o, l, i-1);
            MinMaxTuple right = null;
            if (i == r)
                right = new MinMaxTuple(n[i+1], n[i+1]);
            else
                right = arithmeticParenthesization(n, o, i+1, r);

            // All combinations
            int x = calculate(left.min, right.min, o[i]);
            returnTuple.min = Math.min(returnTuple.min, x);
            returnTuple.max = Math.max(returnTuple.max, x);
            x = calculate(left.min, right.max, o[i]);
            returnTuple.min = Math.min(returnTuple.min, x);
            returnTuple.max = Math.max(returnTuple.max, x);
            x = calculate(left.max, right.min, o[i]);
            returnTuple.min = Math.min(returnTuple.min, x);
            returnTuple.max = Math.max(returnTuple.max, x);
            x = calculate(left.max, right.max, o[i]);
            returnTuple.min = Math.min(returnTuple.min, x);
            returnTuple.max = Math.max(returnTuple.max, x);
        }

        return returnTuple;
    }

    public static void main(String[] args) {
        int[] numbers = new int[] {7, 4, 3, 5};
        char[] operators = new char[] {'+', '*', '+'};
        MinMaxTuple mmt = arithmeticParenthesization(numbers, operators, 0, 
            operators.length-1);
        System.out.println(Arrays.toString(numbers) + " : " + 
            Arrays.toString(operators) + " = " + mmt);

        numbers = new int[] {7, -4, 3, -5};
        operators = new char[] {'+', '*', '+'};
        mmt = arithmeticParenthesization(numbers, operators, 0, operators.length-1);
        System.out.println(Arrays.toString(numbers) + " : " + 
            Arrays.toString(operators) + " = " + mmt);
    }
}