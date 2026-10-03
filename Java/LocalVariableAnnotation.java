import java.lang.annotation.*;

import static java.lang.System.out;

//@Target(ElementType.LOCAL_VARIABLE)
//@Retention(RetentionPolicy.CLASS)
@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
@Target({ElementType.TYPE_USE})
@interface SampleAnnotation {
	String value() default "Foo";
}

public class LocalVariableAnnotation {

	public void qqq(int a, int b) {
		String df1 = "dfoo1";
		String df2 = "dfoo2";
		out.println(df1 + " : " + df2);

		@SampleAnnotation("YF1")
		String vf1 = "XF1";

		String df3 = "dfoo3";

		if (a > 20) {
			vf1 = "a20";
			@SampleAnnotation("YF3")
			String vf3 = "XF3-1";
			out.println("a < 20  - 1: " + vf3);
		}
		else {
			String df4 = "dfoo4";
			out.println("blah");
			@SampleAnnotation("YF3")
			String vf3 = "XF3-2";
			out.println(df4 + " -> a > 20 - 2 : " + vf3);
		}

		@SampleAnnotation("YF2")
		String vf2 = "XF2";

		out.println(a + ", " + b + ", " + vf1 + ", " + vf2);
		out.println(df1 + df2 + df3);


	}

	public static void main(String[] args) {
		out.println("Hello local variable annotation");

		String dummy1 = "d1";
		String dummy2 = "d2";
		out.println(dummy1 + " : " + dummy2);

		@SampleAnnotation("Y1")
		String var1 = "X1";
		@SampleAnnotation("Y2")
		String var2 = "X2";

		LocalVariableAnnotation lva = new LocalVariableAnnotation();
		lva.qqq(10, 20);

		@Nullable("Y3")
		String var3 = "X3";

		out.println("var1 = " + var1 + "; var2 = " + var2 + "; var3 = " + var3);

		//@NonNull
		Object ref = null;
		out.println("ref = " + ref);

		out.println("var3 antn: " + var3.getClass().getAnnotations().length);
		Nullable aaa = var3.getClass().getAnnotation(Nullable.class);
		out.println("nullable: " + aaa);

	}
}