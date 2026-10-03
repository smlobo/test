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
	//private ClassPool classPool;

	public void SampleTransformer() {
		//classPool = ClassPool.getDefault();
		//ClassPool.getDefault().insertClassPath(new ClassClassPath(YourCurrentClassName.class));
	}

	public byte[] transform(ClassLoader loader, String className, 
			Class classModified, ProtectionDomain protectionDomain, 
			byte[] classfileBuffer) {

		System.out.println(" -> Loading Class: " + className + ", size: " + 
			classfileBuffer.length + " bytes");

		try {
			ClassPool cPool = ClassPool.getDefault();

			//System.out.println("here: ");
			//URL cURL = classPool.find(className);
			//URL cURL = cPool.find(className);
			//System.out.println("Found: " + cURL);

			//classPool.insertClassPath(new ByteArrayClassPath(className, 
			//	classfileBuffer));
			CtClass cc = cPool.get(className);
			System.out.println("Found ctClass");
			CtMethod[] methods = cc.getMethods();
			System.out.println("Found CtMethods");
			for (int k=0; k<methods.length; k++) {
				System.out.println("  -> has Methods: " + 
					methods[k].getLongName());
			}

			//return null;
			for (int k=0; k<methods.length; k++) {
				if (methods[k].getLongName().startsWith(className)) {
					methods[k].insertBefore("System.out.println(\"Entering " + 
						methods[k].getLongName() + "\");");
					methods[k].insertAfter("System.out.println(\"Exiting " + 
						methods[k].getLongName() + "\");");
				}
			}

			// return the new bytecode array:
			byte[] newClassfileBuffer = cc.toBytecode();
			return newClassfileBuffer;
		}
		catch (IOException ioe) {
			System.err.println(ioe.getMessage() + " 1transforming class " + 
				className);
		}
		catch (NotFoundException nfe) {
			System.err.println(nfe.getMessage() + " 2transforming class " + 
				className);
		}
		catch (CannotCompileException cce) {
			System.err.println(cce.getMessage() + " 3transforming class " + 
				className);
		}
		/*catch (Exception e) {
			System.err.println(e.getMessage() + " transforming class: " + 
				className);
		}*/
		/*finally {
			System.out.println("Finally: " + className);
			return null;
		}*/
		return null;
	}
}
