import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SpamCheck {
    //private static final String BLACKHOLE = "sbl.spamhaus.org";
    private static final String BLACKHOLE = "b.barracudacentral.org";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter IP address: ");
            String input = in.nextLine();
            if (isSpammer(input))
                System.out.println("  " + input + " is a known spammer");
            else
                System.out.println("  " + input + " appears legitimate");
        }
    }

    private static boolean isSpammer(String ip) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            byte[] quad = address.getAddress();
            String query = "";
            for (int i = quad.length-1; i >= 0; i--) {
                int unsignedByte = quad[i] < 0 ? quad[i] + 256 : quad[i];
                query += unsignedByte + ".";
            }
            query += BLACKHOLE;
            InetAddress.getByName(query);
            return true;
        }
        catch (UnknownHostException unknownHostException) {
            return false;
        }
    }
}
