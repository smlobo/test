
public class LCS {
    private static StringBuilder lcs(String a, int i, String b, int j) {
        // Base case - either string is empty
        if (i == a.length() || j == b.length())
            return new StringBuilder();

        // Characters match
        if (a.charAt(i) == b.charAt(j)) {
            return lcs(a, i+1, b, j+1).append(a.charAt(i));
        }

        // Do not match
        StringBuilder ap = lcs(a, i+1, b, j);
        StringBuilder bp = lcs(a, i, b, j+1);
        return ap.length() > bp.length() ? ap : bp;
    }

    public static void main(String[] args) {
        String a = "hieroglyphology";
        String b = "michaelangelo";
        StringBuilder s = lcs(a, 0, b, 0);
        System.out.println("LCS of: " + a + ", " + b + " = " + s.reverse().toString());
    }
}