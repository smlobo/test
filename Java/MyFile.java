import java.io.File;

class MyFile extends File {

    public MyFile() {
        super("foo.txt");
    }

    public static void main(String[] args) {
        System.out.println("MyFile");
    }
}