import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.MemoryMXBean;

public class Observer {
    public static void main(String[] args) {
        System.out.println("JVM: " + System.getProperty("java.version"));
        System.out.println("Hostname (system properties): " + System.getenv("HOSTNAME"));
        System.out.println("Hostname (env): " + System.getenv("HOSTNAME"));
        
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        System.out.println("OS Name: " + osBean.getName());
        System.out.println("OS Version: " + osBean.getVersion());

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        System.out.println("Heap used: " + memoryBean.getHeapMemoryUsage().getUsed());

        Scanner in = new Scanner(System.in);
        System.out.println("Enter string: ");
        String input = in.nextLine();
        System.out.println("You entered: " + input);
    }
}