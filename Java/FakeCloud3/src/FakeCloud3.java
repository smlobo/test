import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.zip.GZIPInputStream;

public class FakeCloud3 {
    private static boolean writeToFile = true;
    private static int port = 5678;
    private static String keyFile;

    public static void main(String[] args) throws Exception {
        // Parse options
        for (String arg : args) {
            // Do not write observations to file if this is a performance test
            if ("performanceOnly".equals(arg)) {
                writeToFile = false;
            }

            // Custom port option
            else if (arg.startsWith("-port=")) {
                port = Integer.parseInt(arg.split("=")[1]);
            }

            // Custom SSL key file
            else if (arg.startsWith("-key-file=")) {
                keyFile = arg.split("=")[1];
            }
        }

        System.out.println("Starting FakeCloud (tm)");
        startHttpServer(port);
        startHttpsServer(port+1);
    }

    private static void startHttpServer(int port) throws Exception {
        final HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 100);
        httpServer.setExecutor(Executors.newFixedThreadPool(100));
        httpServer.start();
        httpServer.createContext("/20200101/observations", new CloudHandler());
        System.out.println("  - HTTP server started on: " + httpServer.getAddress());
    }

    private static void startHttpsServer(int port) throws Exception {
        final HttpsServer httpsServer = HttpsServer.create(new InetSocketAddress(port), 100);

        // Use the default keyfile from the jar
        InputStream keyFileStream;
        if (keyFile == null) {
            keyFile = "Default keyfile from the jar (fake-cloud.keys)";
            keyFileStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ssl/fake-cloud.keys");
        }
        else {
            keyFileStream = new FileInputStream(keyFile);
        }

        SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

        KeyStore ks = KeyStore.getInstance("JKS");
        char[] password = "welcome".toCharArray();
        ks.load(keyFileStream, password);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        sslContext.init(kmf.getKeyManagers(), null, null);

        httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                try {
                    SSLContext context = getSSLContext();
                    SSLEngine engine = context.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());
                    params.setSSLParameters(context.getSupportedSSLParameters());
                }
                catch (Exception e) {
                    System.err.println("Failed to configure HttpsConfigurator: " + e);
                    e.printStackTrace();
                }
            }
        });
        httpsServer.setExecutor(Executors.newFixedThreadPool(100));
        httpsServer.start();
        httpsServer.createContext("/20200101/observations", new CloudHandler());
        System.out.println("  - HTTPS server started on: " + httpsServer.getAddress());
    }

    static class CloudHandler implements HttpHandler {
        private static AtomicInteger numConnections = new AtomicInteger();
        private static AtomicLong totalBytes = new AtomicLong();
        private static AtomicLong totalInflatedBytes = new AtomicLong();

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            int bodySize = 0;
            int uncompressedBodySize = 0;
            String compression = null;
            int connectionNum = numConnections.get();

            // Read headers
            try {
                Headers headers = httpExchange.getRequestHeaders();
                for (Map.Entry<String, List<String>> en : headers.entrySet()) {
                    if ("Content-length".equalsIgnoreCase(en.getKey()))
                        bodySize = Integer.parseInt(en.getValue().get(0));
                    if ("Content-encoding".equalsIgnoreCase(en.getKey()))
                        compression = en.getValue().get(0);
                }
            } catch (Exception e) {
                httpExchange.sendResponseHeaders(400, -1);
                return;
            }

            // Read body
            if ("POST".equalsIgnoreCase(httpExchange.getRequestMethod())) {
                try {
                    uncompressedBodySize = readRequestBody(httpExchange, connectionNum, compression);
                } catch (Exception e) {
                    httpExchange.sendResponseHeaders(400, -1);
                    return;
                }
            }

            httpExchange.sendResponseHeaders(200, -1);
            numConnections.incrementAndGet();

            System.out.print("[" + connectionNum + "]\t" + httpExchange.getRemoteAddress() + " Size: " + bodySize);
            if (compression != null)
                System.out.print("; Inflated: " + uncompressedBodySize);
            System.out.println();

            httpExchange.close();
        }

        public static int readRequestBody(HttpExchange httpExchange, int connectionNum, String compression) throws IOException {
            final byte[] buffer = new byte[4096];

            // Read Gzip or non-gzip data
            InputStream is = "gzip".equals(compression) ? new GZIPInputStream(httpExchange.getRequestBody()) :
                    httpExchange.getRequestBody();

            int count;
            int size = 0;
            FileOutputStream fileOutputStream = null;
            if (writeToFile)
                fileOutputStream = new FileOutputStream(connectionNum + ".data");
            while ((count = is.read(buffer)) != -1) {
                size += count;
                if (writeToFile)
                    fileOutputStream.write(buffer, 0, count);
            }
            is.close();
            if (writeToFile)
                fileOutputStream.close();

            return size;
        }
    }
}
