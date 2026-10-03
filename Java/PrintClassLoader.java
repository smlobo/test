//package com.sheldon;

public class PrintClassLoader
{
	public static void main(String[] args)
	{
		ClassLoader loader = PrintClassLoader.class.getClassLoader();
		System.out.println("Loader: " + loader);
		System.out.println("Loader path to this file: " + loader.
			getResource("PrintClassLoader.class"));

		ClassLoader parent = loader.getParent();
		while (parent != null) {
			System.out.println("  Parent: " + parent);
			parent = parent.getParent();
		}
	}
}
