public class StackSort<T extends Comparable<T>> {
    public static <T extends Comparable<T>> Stack<T> sort(Stack<T> input) {
        Stack<T> result = new Stack<>();

        // Pop the next value from the input stack
        T current;
        while ((current = input.pop()) != null) {
            // Pop the result stack until we find the right place to put the 
            // current value
            // Keep track of the count
            int count = 0;
            while (result.peek() != null && 
                result.peek().compareTo(current) < 0) {
                input.push(result.pop());
                count++;
            }

            // Push the current to the result stack
            result.push(current);

            // Push back to the result stack, from the temp input stack
            for (int i = 0; i < count; i++)
                result.push(input.pop());
        }
        return result;
    }

    public static void main(String[] args) {
        // Create a stack of chars
        Stack<Character> s1 = new Stack<>();
        s1.push('B');
        s1.push('E');
        s1.push('Q');
        s1.push('A');
        s1.push('D');

        System.out.println("s1 before: " + s1);
        Stack<Character> s1s = StackSort.sort(s1);
        System.out.println("sorted: " + s1s);
    }
}