public class StringBytes {
	public static void main(String[] args) {

		String[] strs = {"abcdefg", "英雄", "シン・ゴジラ", "Astérix"};

		for (String str: strs) {
			byte[] bytes = str.getBytes();
			System.out.println("String: " + str + " ; length: " + 
				str.length() + ", bytes: " + bytes.length);
			for (int x: bytes) {
				System.out.format("'%c' %x, ", (char) x, (byte) x);
			}
			System.out.println();
		}
	}
}