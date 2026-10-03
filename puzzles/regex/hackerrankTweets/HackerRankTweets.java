import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HackerRankTweets {
    public static void main(String[] args) {
        Scanner stdinScanner = new Scanner(System.in);

        // Number of lines
        int n = stdinScanner.nextInt();
        stdinScanner.nextLine();

        // Regex
        //Pattern pattern = Pattern.compile(".*hackerrank.*", Pattern.CASE_INSENSITIVE);
        //Pattern pattern = Pattern.compile(".*[hH][aA][cC][kK][eE][rR]{2}[aA][nN][kK].*");
        //Pattern pattern = Pattern.compile(".*h|Ha|Ac|Ck|Ke|Er|R{2}a|An|Nk|K.*");
        // | has to be in brackets
        Pattern pattern = Pattern.compile(".*(h|H)(a|A)(c|C)(k|K)(e|E)(r|R){2}(a|A)(n|N)(k|K).*");

        // Count of matches
        int count = 0;

        // Iterate over the input
        for (int i = 0; i < n; i++) {
            String line = stdinScanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find())
                count++;
            System.out.println("[" + i + "] " + line + " -> " + count);
        }

        System.out.println(count);
    }
}