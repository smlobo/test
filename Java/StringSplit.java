public class StringSplit {
    public static void main(String args[]) {
        String[] splitted = args[0].trim().split(":");
        for (int i = 0; i < splitted.length; i++) {
            System.out.println("[" + i + "] " + splitted[i] + " -> " + 
                               splitted[i].length());
        }
    }
}
