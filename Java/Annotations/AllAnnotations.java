import java.lang.annotation.*;

import static java.lang.System.out;

@Retention(RetentionPolicy.RUNTIME)
@interface APMInstrumentRuntime {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@interface APMInstrumentType {}

@APMInstrumentRuntime
public class AllAnnotations {
	@APMInstrumentRuntime
	private int x;
	public double y;

	@APMInstrumentRuntime
	public void foo(
		@APMInstrumentRuntime
		int a) {
		x += 1 + a;
		y -= 1.11 + a;
	}

	public @APMInstrumentType String toString() {
		return "{" + x + ";" + y + "}";
	}

	public static void main(String[] args) {
		AllAnnotations t1 = new AllAnnotations();
		t1.x = 10;
		t1.y = 2.22;
		out.println("t1 = " + t1);
		t1.foo(99);
		out.println("t1 (post foo) = " + t1);

		AllAnnotations t2 = new AllAnnotations();
		t2.x = 20;
		t2.y = 3.33;
		out.println("t2 = " + t2);
		t2.foo(88);
		out.println("t2 (post foo) = " + t2);
	}
}