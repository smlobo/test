import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.io.IOException;
import java.net.URL;

import javassist.ClassPool;
import javassist.ByteArrayClassPath;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CannotCompileException;
import javassist.NotFoundException;

public class SampleTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, 
			Class classModified, ProtectionDomain protectionDomain, 
			byte[] classfileBuffer) {

		System.out.println(" -> Loading Class: " + className);

		// Do not instrument the Notifier class
		if ("Notifier".equals(className) == true) {
			System.out.println("    ===> Do not instrument: " + className);
			return null;
		}

		ClassPool cPool = ClassPool.getDefault();

		CtClass cc = null;
		try {
			cc = cPool.get(className);
		}
		catch (NotFoundException nfe) {
			System.err.println("Not found exception: " + className);
		}
		System.out.println("    ~> Found ctClass");

		CtMethod[] methods = cc.getMethods();
		System.out.println("    ~> Found CtMethods");
		for (int k=0; k<methods.length; k++) {
			try {
				methods[k].insertBefore("Notifier.methodEntry(\""
					+ className + "\", \"" + methods[k].getName() + "\");");
				methods[k].insertAfter("Notifier.methodExit(\""
					+ className + "\", \"" + methods[k].getName() + "\");");
			}
			catch (CannotCompileException cce) {
				System.err.println("Cannot compile exception: " + className 
					+ ", " + methods[k].getName() + ", msg: " + 
					cce.getMessage());
			}
		}

		// return the new bytecode array:
		byte[] newClassfileBuffer = null;
		try {
			newClassfileBuffer = cc.toBytecode();
		}
		catch (IOException ioe) {
			System.err.println("IOException: " + className);
		}
		catch (CannotCompileException cce) {
			System.err.println("Bytecode cannot compile exception: " + 
				className);
		}

		return newClassfileBuffer;
	}
}
