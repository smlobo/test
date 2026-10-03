import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Starter {

    public static void main(final String[] args) {
        final ExecutorService service = Executors.newSingleThreadExecutor();

        try {
            final Future<Object> f = service.submit(() -> {
                // Do you long running calculation here
                Thread.sleep(10337); // Simulate some delay
                return "42";
            });

            System.out.println(f.get(10, TimeUnit.SECONDS));
        } catch (final TimeoutException e) {
            System.err.println("Calculation took to long");
        } catch (final Exception e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }
    }
}