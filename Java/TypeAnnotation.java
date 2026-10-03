@interface ReadOnly {}

public class TypeAnnotation {
	public String someString(@ReadOnly String x) {
		return x + "hi";
	}
}
