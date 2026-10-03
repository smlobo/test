import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SampleTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader, String className,
                            Class classModified,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        System.out.println(" -> Loading Class: " + className + ", size: " + 
        	classfileBuffer.length + " bytes");
        return null;
    }
}
