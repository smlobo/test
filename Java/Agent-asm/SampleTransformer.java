import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

public class SampleTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, 
			Class classModified, ProtectionDomain protectionDomain, 
			byte[] classfileBuffer) {

		System.out.println(" -> Loading Class: " + className);

		// Do not instrument the Notifier class
		if (!("HelloWorld".equals(className))) {
			return null;
		}

		System.out.println("    ===> instrument: " + className);

		ClassReader reader = new ClassReader(classfileBuffer);
		ClassWriter writer = new ClassWriter(reader, 0);
		ClassPrinter visitor = new ClassPrinter(writer);
		reader.accept(visitor, 0);
		return writer.toByteArray();
	}
}
