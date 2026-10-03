//package other;

import java.lang.annotation.*;

import static java.lang.System.out;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE)
@interface APMInstrumentRuntime {}

@Retention(RetentionPolicy.CLASS)
@interface APMInstrumentClass {}

@Retention(RetentionPolicy.SOURCE)
@interface APMInstrumentSource {}

//@APMInstrumentRuntime
@APMInstrumentClass
@APMInstrumentSource
public class TestCase {
	private int x;
	public double y;

	@APMInstrumentRuntime
	@APMInstrumentClass
	@APMInstrumentSource
	public void foo() {
		x += 1;
		y -= 1.11;
	}

	public String toString() {
		return "{" + x + ";" + y + "}";
	}

	public static void main(String[] args) {
		TestCase t1 = new TestCase();
		t1.x = 10;
		t1.y = 2.22;
		out.println("t1 = " + t1);
		t1.foo();
		out.println("t1 (post foo) = " + t1);

		TestCase t2 = new TestCase();
		t2.x = 20;
		t2.y = 3.33;
		out.println("t2 = " + t2);
		t2.foo();
		out.println("t2 (post foo) = " + t2);
	}
}