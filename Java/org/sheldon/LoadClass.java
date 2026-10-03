package org.sheldon;

//import HelloWorld;
import com.sheldon.PrintClassLoader;
import org.sheldon.SecondDynamicLoadJar;

import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.util.Arrays;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.MalformedURLException;
import java.io.IOException;

public class LoadClass {
	public static void loadAllClasses(String jarName) {

		try {
			JarFile jarFile = new JarFile(jarName);
			Enumeration<JarEntry> jarEntries = jarFile.entries();

			//URL[] urls = { new URL("jar:file:" + jarName + "!/") };
			//URLClassLoader cl = URLClassLoader.newInstance(urls);
			//System.out.println("URLClassLoader: " + cl);

			ClassLoader loader = LoadClass.class.getClassLoader();

			while (jarEntries.hasMoreElements()) {
    			JarEntry je = jarEntries.nextElement();
    			if (je.isDirectory() || !je.getName().endsWith(".class"))
	        		continue;

		    	// -6 because of .class
		    	String className = je.getName().
		    		substring(0, je.getName().length() - 6);
	    		className = className.replace('/', '.');
	    		System.out.println("Load class: " + className);
	    		//Class c = cl.loadClass(className);
	    		Class c = loader.loadClass(className);
			}
		}
		catch (Exception e) {
			System.out.println("Caught: " + e);
		}
	}

	public static void loadLibrary(String jarName) {
		System.out.println("Loading library: " + jarName);
        try {
            /* We are using reflection here to circumvent encapsulation; addURL 
            is not public */
            URLClassLoader loader = (URLClassLoader) ClassLoader.
            	getSystemClassLoader();
            //URL url = jar.toURI().toURL();
			URL url = new URL("jar:file:" + jarName + "!/");
            System.out.println("Jarfile URL: " + url);
            /*Disallow if already loaded*/
            for (URL it : Arrays.asList(loader.getURLs())) {
            	System.out.println("Previous: " + it);
                if (it.equals(url)) {
                    return;
                }
            }
            java.lang.reflect.Method method = URLClassLoader.class.
            	getDeclaredMethod("addURL", new Class[] {URL.class});
            method.setAccessible(true); /*promote the method to public access*/
            method.invoke(loader, new Object[]{url});
        } catch (final java.lang.NoSuchMethodException | 
            java.lang.IllegalAccessException | 
            java.net.MalformedURLException | 
            java.lang.reflect.InvocationTargetException e) {
        	System.out.println("Caught: " + e);
        }
    }

	public static void main(String[] args) {
		/* Cannot try to load - closes(?) system class loader
		try {
			PrintClassLoader.foo();
		}
		catch (NoClassDefFoundError e) {
			System.out.println("Expected - PrintClassLoader not added");
		}*/

		if (args.length >= 1)
			//loadAllClasses(args[0]);
			loadLibrary(args[0]);

		ClassLoader loader = LoadClass.class.getClassLoader();
		System.out.println("Main ClassLoader: " + loader);
		System.out.println(loader.
			getResource("org/sheldon/LoadClass.class"));

		System.out.println("In " + LoadClass.class.getName() + ", main()");
		PrintClassLoader.foo();

		/* see above
		try {
			SecondDynamicLoadJar.bar();
		}
		catch (NoClassDefFoundError e) {
			System.out.println("Expected - SecondDynamicLoadJar not added");
		}*/

		if (args.length == 2)
			loadLibrary(args[1]);

        /*URLClassLoader urlLoader = (URLClassLoader) ClassLoader.
            getSystemClassLoader();
		for (URL it : Arrays.asList(urlLoader.getURLs()))
            System.out.println("Previous (after): " + it);*/

        SecondDynamicLoadJar.bar();
	}
}