import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;

public class ReadByteBinaryFile {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ReadByteBinaryFile <filename>");
            System.exit(1);
        }

        int count = 0;
        try {
            //InputStream inputStream = new FileInputStream(args[0]);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(args[0]));
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                System.out.print(byteRead + ",");
                count++;
                if (count%37 == 0)
                    System.out.println();
            }
        }
        catch (IOException e) {
            System.out.println("Read of " + args[0] + " failed");
        }

        System.out.println("\nRead " + count + " ubyte numbers");
    }
}