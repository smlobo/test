    public class MyCountingOutputStream extends OutputStream {
        private OutputStream out;
        private byte[] byteArray;
        private int validCount;

        public MyCountingOutputStream(OutputStream out) {
            this.out = out;
            byteArray = new byte[50000];
        }

        public void write(byte[] b, int off, int len) throws IOException {
            //logger.info("Sheldon: MyCountingOutputStream off: " +  off + "; length: " + len);
            for (int i = 0; i < len; i++) {
                byteArray[validCount++] = b[i];
            }
        }

        @Override
        public void write(int b) throws IOException {
            logger.info("Sheldon: MyCountingOutputStream write(int) called: " + b);
        }

//        public void flush() throws IOException {
//            logger.info("Sheldon: MyCountingOutputStream count before: " + validCount);
//            out.write(byteArray, 0, validCount);
//        }

        public void close() throws IOException {
            logger.info("Sheldon: MyCountingOutputStream count during close: " + validCount);
            out.write(byteArray, 0, validCount);
            out.flush();
            out.close();
        }
    }
