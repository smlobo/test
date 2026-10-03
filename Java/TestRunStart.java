class MyRunnable implements Runnable{
    public void run(){   //overrides Runnable's run() method
        System.out.println("in run() method");
        System.out.println("currentThreadName= " +
                           Thread.currentThread().getName());
    }
}
 
public class TestRunStart {
    public static void main(String args[]){
        System.out.println("main currentThreadName= " +
                           Thread.currentThread().getName());
        MyRunnable runnable = new MyRunnable();
        
        Thread trun = new Thread(runnable);
        trun.run();

        try {
            trun.join();
        }
        catch (Exception e) {
            System.out.println("trun join failed");
        }
        System.out.println("trun joined");

        Thread tstart = new Thread(runnable);
        tstart.start();

        try {
            tstart.join();
        }
        catch (Exception e) {
            System.out.println("tstart join failed");
        }
        System.out.println("tstart joined");
    }
}
