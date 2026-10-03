    public class MyGZIPOutputStream extends GZIPOutputStream {

        public MyGZIPOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        public long getBytesRead() {
            return def.getBytesRead();
        }

        public long getBytesWritten() {
            return def.getBytesWritten();
        }

        public void setLevel(int level) {
            def.setLevel(level);
        }

        public void write(byte[] b, int off, int len) throws IOException {
            super.write(b, off, len);
            logger.info("Sheldon: GZIP asked to write: buf: " + b.length + ", len: " + len);
        }
    }
