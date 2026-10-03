
class ByteCode {
    private int x;
    private int y;
    private int z;

    ByteCode(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static byte add(byte a, byte b) {
        return (byte) (a + b);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public int deref(ByteCode bc) {
        return bc.x;
    }

    public int intObjToPrimitive(Integer x) {
        return x;
    }

    public int derefAdd(ByteCode bc) {
        return bc.z + bc.y;
    }

    public int arrayAdd(ByteCode[] bcArray, int n) {
        return bcArray[n].y + bcArray[n].z;
    }

    public static int controlFlow(int n, int m) {
        int x;
        if (n > m)
            x = n;
        else
            x = m;
        return x;
    }

    public static String locals(ByteCode a, ByteCode b) {
        String x = "foo";
        if (a.x > b.x) {
            String p = "--" + b.y;
            p += "~~";
            x = p + a.z;
        }
        return x;
    }

}