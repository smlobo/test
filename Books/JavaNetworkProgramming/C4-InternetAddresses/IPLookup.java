import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPLookup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("Enter DNS name: ");
            String input = in.nextLine();
            try {
                InetAddress[] addresses = InetAddress.getAllByName(input);
                System.out.print("Found: ");
                for (InetAddress address : addresses)
                    System.out.println("\t" + address + "; ");
            }
            catch (UnknownHostException unknownHostException) {
                System.out.println("Could not find: " + input);
            }
        }
    }
}
