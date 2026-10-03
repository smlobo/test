import java.util.Scanner;
import java.util.Arrays;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.DigestUtils;

public class Solution {

    public static void main(String[] args) {
        // Enter your code here. Read input from STDIN. Print output to STDOUT.
        Scanner scan = new Scanner(System.in);
        String inputString = scan.nextLine();
        //System.out.println(inputString);
        scan.close();

        // Add the new line back
        inputString += "\n";

        try {
			// Static getInstance method is called with hashing MD5
    	    MessageDigest md = MessageDigest.getInstance("MD5");

        	// digest() method is called to calculate message digest 
        	// of an input digest() return array of bytes
        	//byte[] messageDigest = md.digest(inputString.getBytes("UTF-8"));
            byte[] messageDigest = md.digest(inputString.getBytes());
        	//System.out.println("byteArray: " + Arrays.toString(messageDigest));

            System.out.print("java.security MD5: ");
        	for (int i = 0; i < messageDigest.length; i++) {
        		System.out.format("%02x", messageDigest[i]);
        	}
        	System.out.println();
        }
        //catch (NoSuchAlgorithmException e) {
        catch (Exception e) {
        	System.out.println("MD5 not found");
        }

        // Apache commons
        System.out.println("Apache commons: " + DigestUtils.md5Hex(inputString));
    }
}

