/*

You are given a class Solution and its main method in the editor. Your task is 
to create a class Prime. The class Prime should contain a single method 
checkPrime.

The locked code in the editor will call the checkPrime method with one or more 
integer arguments. You should write the checkPrime method in such a way that the 
code prints only the prime numbers.

Please read the code given in the editor carefully. Also please do not use 
method overloading!

Note: You may get a compile time error in this problem due to the statement 
below:
  BufferedReader br=new BufferedReader(new InputStreamReader(in));
This was added intentionally, and you have to figure out a way to get rid of 
the error.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;

import static java.lang.System.in;
import static java.lang.System.out;

class Prime {
	public void checkPrime(int ... a) {
		for (int i = 0; i < a.length; i++) {
			boolean isPrime = true;
			for (int j = 2; j < a[i]; j++) {
				if (a[i] % j == 0)
					isPrime = false;
			}
			if (isPrime && a[i] != 1)
				out.print(a[i] + " ");
		}
		out.println();
	}
}

public class PrimeChecker {

	public static void main(String[] args) {
		try{
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		int n1=Integer.parseInt(br.readLine());
		int n2=Integer.parseInt(br.readLine());
		int n3=Integer.parseInt(br.readLine());
		int n4=Integer.parseInt(br.readLine());
		int n5=Integer.parseInt(br.readLine());
		Prime ob=new Prime();
		ob.checkPrime(n1);
		ob.checkPrime(n1,n2);
		ob.checkPrime(n1,n2,n3);
		ob.checkPrime(n1,n2,n3,n4,n5);	
		Method[] methods=Prime.class.getDeclaredMethods();
		Set<String> set=new HashSet<>();
		boolean overload=false;
		for(int i=0;i<methods.length;i++)
		{
			if(set.contains(methods[i].getName()))
			{
				overload=true;
				break;
			}
			set.add(methods[i].getName());
			
		}
		if(overload)
		{
			throw new Exception("Overloading not allowed");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
}

