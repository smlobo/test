public class Notifier {

	public static void methodEntry(String className, String methodName) {
		System.out.println("Thread '" + Thread.currentThread().getName() + 
			"' entering method '" + methodName + "' of class '" + className 
			+ "'");
	}

	public static void methodExit(String className, String methodName) {
		System.out.println("Thread '" + Thread.currentThread().getName() + 
			"' exiting method '" + methodName + "' of class '" + className 
			+ "'");
	}
}