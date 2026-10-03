import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * To Start, build apm-agent-java repo and run the following command at repo root.
 * Configuration options are printed to stdout when this is started.
 *
 * Windows:
 *     java -cp agent\common\target\apm-java-agent-common-0.1-SNAPSHOT-tests.jar;agent\common\target\apm-java-agent-common-0.1-SNAPSHOT.jar;agent\common\target\apm-java-agent-system-0.1-SNAPSHOT.jar;agent\common\target\lib\apm-observations-0.5.1.jar;agent\common\target\lib\jackson-core-2.10.1.jar;agent\common\target\lib\jackson-annotations-2.10.1.jar;agent\common\target\lib\jackson-databind-2.10.1.jar com.oracle.apm.agent.utility.FakeCloud2
 * Linux:
 *     java -cp agent/common/target/apm-java-agent-common-0.1-SNAPSHOT-tests.jar:agent/common/target/apm-java-agent-common-0.1-SNAPSHOT.jar:agent/common/target/apm-java-agent-system-0.1-SNAPSHOT.jar:agent/common/target/lib/apm-observations-0.5.1.jar:agent/common/target/lib/jackson-core-2.10.1.jar:agent/common/target/lib/jackson-annotations-2.10.1.jar:agent/common/target/lib/jackson-databind-2.10.1.jar com.oracle.apm.agent.utility.FakeCloud2
 */
public class FakeCloud2 extends WebServer {
    public static final int DEFAULT_CLOUD_PORT = 9981;
    public static final String DEFAULT_CLOUD_LOG = FakeCloud2.class.getSimpleName() + ".log";

    public final String CLOUD_LOG_FILE_PATH = System.getProperty("cloud.log", DEFAULT_CLOUD_LOG);
    public final String OBSERVATION_LOG_FILE_PATH = System.getProperty("cloud.observation.log");
    public final long SPAN_STATUS_INTERVAL = Long.getLong("cloud.span.status.interval", 10 * 1000);

    final PrintStream observationStream;
    final PrintStream logStream;

    public FakeCloud2(int port, String uploadPath) throws Exception {
        super(port, uploadPath);

        logStream = getLogStream();
        log("Log started = " + new Date());
        log("Port = " + port);
        log("Upload Path = " + uploadPath);
        log("System properties = " + System.getProperties());

        observationStream = getObservationStream();
        log("Observation log print stream = " + observationStream);

        SpanHandler spanHandler = new SpanHandler();
        addContextHandler(spanHandler);
        log("Add handler = " + spanHandler);
    }

    public PrintStream getObservationStream() throws Exception {
        if (OBSERVATION_LOG_FILE_PATH == null) {
            return null;
        }

        File logFile = new File(OBSERVATION_LOG_FILE_PATH).getAbsoluteFile();
        File dir = logFile.getParentFile();
        if (dir.mkdirs()) {
            throw new IllegalStateException(String.format("Unable to create directory [%s]", dir.getAbsolutePath()));
        }

        System.out.println("Cloud observation log = " + logFile.getAbsolutePath());
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFile, false)));
        return out;
    }

    public PrintStream getLogStream() throws Exception {
        File logFile = new File(CLOUD_LOG_FILE_PATH);
        PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(logFile, true)), true);
        System.out.println("Cloud log = " + logFile.getAbsolutePath());
        return out;
    }

    public void log(String message) {
        log(message, null);
    }
    public void log(Throwable thrown) {
        log(null, thrown);
    }
    public void log(String message, Throwable thrown) {
        if (message != null) {
            logStream.println("<" + System.currentTimeMillis() + ">  " + message);
        }
        else {
            logStream.println("<" + System.currentTimeMillis() + ">  ");
        }

        if (thrown != null) {
            thrown.printStackTrace(logStream);
        }
    }

    class SpanHandler extends ContextHandler {
        final AtomicLong spanPayloadReceivedCount = new AtomicLong(0);
        final AtomicLong spanReceivedCount = new AtomicLong(0);
        final AtomicLong spanErrorCount = new AtomicLong(0);

        public SpanHandler() {
            new Thread() {
                {
                    setDaemon(false);
                }
                @Override
                public void run() {
                    for (;;) {
                        log(SpanHandler.class.getSimpleName() + ": Received [spanPayload=" + spanPayloadReceivedCount + "][span=" + spanReceivedCount + "][error=" + spanErrorCount + "]");
                        try {
                            Thread.sleep(SPAN_STATUS_INTERVAL);
                        } catch (InterruptedException e) {}
                    }
                }
            }.start();
        }

        @Override
        public String getContext() {
            return "private-span";
        }

        @Override
        public String handle(Map<String, String> requestParams, Map<String, String> requestHeaders, String requestBody) throws Throwable {
            try {
                spanPayloadReceivedCount.incrementAndGet();

                if (observationStream != null) {
                    observationStream.println(requestBody);
                }

                return null;
            } catch (Throwable t) {
                spanErrorCount.incrementAndGet();
                log(t);
                throw t;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("");
        System.out.println("Usage: only java system properties");
        System.out.println("  cloud.port -- Port number of cloud. Default 9981");
        System.out.println("  cloud.log -- Log file path. Default FakeCloud2.log");
        System.out.println("  cloud.observation.log -- Observation log file path. No default");
        System.out.println("  cloud.span.status.interval -- Frequency of status logging. Default 10000 [ms]");
        System.out.println("");

        int CLOUD_PORT = Integer.getInteger("cloud.port", DEFAULT_CLOUD_PORT);
        FakeCloud2 cloud = new FakeCloud2(CLOUD_PORT, PropertyNames.PROP_VALUE_TRANSPORT_UPLOAD_PATH);
        cloud.start();
    }
}