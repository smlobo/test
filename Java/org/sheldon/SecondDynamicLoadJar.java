package org.sheldon;

public class SecondDynamicLoadJar
{
	public static void bar() {
		System.out.println("In " + SecondDynamicLoadJar.class.getName() + 
			", bar()");
		ClassLoader loader = SecondDynamicLoadJar.class.getClassLoader();
		System.out.println("~~~ " + loader.
			getResource("org/sheldon/SecondDynamicLoadJar.class"));
		System.out.println("~~~ " + loader);
	}

	public static void main(String[] args)
	{
		ClassLoader loader = SecondDynamicLoadJar.class.getClassLoader();
		System.out.println(loader.
			getResource("org/sheldon/SecondDynamicLoadJar.class"));
		System.out.println(loader);
		bar();
	}
}
