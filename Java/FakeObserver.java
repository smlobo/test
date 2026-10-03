import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class FakeObserver {

    public static void main(String[] args) throws Exception {
        String inputFile = 
            "/home/smlobo/wireshark/agent-observer/observer-data.dat";
        InputStream inputStream = new FileInputStream(inputFile);
        int fileSize = (int) new File(inputFile).length();
        byte[] data1 = new byte[fileSize];
        inputStream.read(data1);
        inputStream.close();

        inputFile = 
            "/home/smlobo/wireshark/agent-observer/observer-data2.dat";
        inputStream = new FileInputStream(inputFile);
        fileSize = (int) new File(inputFile).length();
        byte[] data2 = new byte[fileSize];
        inputStream.read(data2);
        inputStream.close();
        

        Socket socket = new Socket("127.0.0.1", 12321);
        System.out.println("Started FakeObserver on port: 12321");

        DataOutputStream outSocket = new DataOutputStream
            (socket.getOutputStream());
        outSocket.write(data1, 0, data1.length);
        outSocket.write(data2, 0, data2.length);
        outSocket.flush();

        //Thread.sleep(100);
        socket.close();
    }
}
