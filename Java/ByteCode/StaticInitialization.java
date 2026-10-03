public class StaticInitialization {
  static int x = 10;

  static {
  	System.out.println("In static init code area");
  }

  public static void main(String[] args) {
  	
  }
}

class Foo {
  static {
	System.out.println("In static init code area for Foo");  		
  }
}
