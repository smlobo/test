

class Aux {
	public String auxString(String y) {
		return y + "aux";
	}
}

public class Normal {

	public String normalString(String x) {
		return x + "normal";
	}

	class Nested {
		public String nestedString(String z) {
			return z + "nest";
		}
	}
}
