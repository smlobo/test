package com.sheldon;

public class PrintClassLoader
{
	public static void foo() {
		System.out.println("In " + PrintClassLoader.class.getName() + 
			", foo()");
		ClassLoader loader = PrintClassLoader.class.getClassLoader();
		System.out.println("--- " + loader.
			getResource("com/sheldon/PrintClassLoader.class"));
		System.out.println("--- " + loader);
	}

	public static void main(String[] args)
	{
		ClassLoader loader = PrintClassLoader.class.getClassLoader();
		System.out.println(loader.
			getResource("com/sheldon/PrintClassLoader.class"));
		System.out.println(loader);
		foo();
	}
}
