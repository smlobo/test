import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable {
    public void run() {
    	System.out.println(new Date());
        System.out.println("currentThreadName= " +
                           Thread.currentThread().getName());
    }
}

public class TimerTest {
    public static void main(String[] args) {
        final ScheduledExecutorService ses = 
        	Executors.newSingleThreadScheduledExecutor();
        /*ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Date());
            }
        }, 0, 1, TimeUnit.SECONDS);*/
        ses.scheduleWithFixedDelay(new MyRunnable(), 0, 1, TimeUnit.SECONDS);
    }
}
