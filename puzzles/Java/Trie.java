/*
Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true

Note:
* You may assume that all inputs are consist of lowercase letters a-z.
* All inputs are guaranteed to be non-empty strings.

*/

import java.util.ArrayList;

class Trie {

	private ArrayList<ArrayList> head;

	// Create a new array of size 27 (for the null terminator), and return 
	// a pointer to it. Initialize it to null.
	private ArrayList<ArrayList> newList() {
		ArrayList<ArrayList> r = new ArrayList<>(27);
		for (int i = 0; i < 27; i++) {
			r.add(null);
		}
		return r;
	}
	
	/** Initialize your data structure here. */
	public Trie() {
		// Data structure is a 27 (end str) array. If array index content 
		// is 'null', the char does not exist.
		head = newList();
	}

	/** Inserts a word into the trie. */
	@SuppressWarnings("unchecked")
	public void insert(String word) {
		ArrayList<ArrayList> current = head;

		// Iterate over the chars in the word, going down the 27-way trie
		//System.out.println("Insert: ");
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			//System.out.println(word.charAt(i) + " (" + index + ") ");

			// If the word (current char) does not exist, add it
			ArrayList<ArrayList> next = current.get(index);
			if (next == null) {
				//System.out.println("  -> creating new for: " + word.charAt(i));
				next = newList();
				current.set(index, next);
			}
			current = next;
		}
		//System.out.println();

		// Add the end of string dummy list
		current.add(26, new ArrayList(0));
	}

	/** Returns if the word is in the trie. */
	@SuppressWarnings("unchecked")
	public boolean search(String word) {
		ArrayList<ArrayList> current = head;

		//System.out.println("Search: ");		
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			//System.out.println(word.charAt(i) + " (" + index + ") ");
			ArrayList<ArrayList> next = current.get(index);
			if (next == null) {
				return false;
			}
			current = next;
		}

		// Check for end of string marker
		if (current.get(26) == null) {
			return false;
		}
		return true;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	@SuppressWarnings("unchecked")
	public boolean startsWith(String prefix) {
		ArrayList<ArrayList> current = head;
		
		for (int i = 0; i < prefix.length(); i++) {
			int index = prefix.charAt(i) - 'a';
			ArrayList<ArrayList> next = current.get(index);
			if (next == null) {
				return false;
			}
			current = next;
		}
		return true;
	}

	public static void main(String[] args) {
		Trie a = new Trie();
		String x;
		boolean b;

		// test 1
		x = "foo";
		b = a.search(x);
		System.out.println(x + " NOT inserted, search -> " + b);
		assert !b;
		a.insert(x);
		b = a.search(x);
		System.out.println(x + " inserted, search -> " + b);
		assert b;

		x = "fo";
		b = a.startsWith(x);
		System.out.println("startsWith " + x + " ~~> " + b);
		assert b;

		// test 2
		x = "bar";
		b = a.search(x);
		System.out.println(x + " NOT inserted, search -> " + b);
		assert !b;
		a.insert(x);
		b = a.search(x);
		System.out.println(x + " inserted, search -> " + b);
		assert b;

		x = "b";
		b = a.startsWith(x);
		System.out.println("startsWith " + x + " ~~> " + b);
		assert b;

		// test 3
		x = "apple";
		b = a.search(x);
		System.out.println(x + " NOT inserted, search -> " + b);
		assert !b;
		a.insert(x);
		b = a.search(x);
		System.out.println(x + " inserted, search -> " + b);
		assert b;

		x = "app";
		b = a.search(x);
		System.out.println("search " + x + " ~~> " + b);
		assert !b;
		b = a.startsWith(x);
		System.out.println("startsWith " + x + " ~~> " + b);
		assert b;

		// test 4
		a = new Trie();
		a.insert("app");
		a.insert("apple");
		a.insert("beer");
		a.insert("add");
		assert a.search("app");
		a.insert("jam");
		a.insert("rental");
		assert !a.search("apps");
		assert a.search("app");
		assert !a.search("ad");
		assert !a.search("applepie");
		assert !a.search("rest");
		assert !a.search("jan");
		assert !a.search("rent");
		assert a.search("beer");
		assert a.search("jam");
		assert !a.startsWith("apps");
		assert a.startsWith("app");
		assert a.startsWith("ad");
		assert !a.startsWith("applepie");
		assert !a.startsWith("rest");
		assert !a.startsWith("jan");
		assert a.startsWith("rent");
		assert a.startsWith("beer");
		assert a.startsWith("jam");

		a.insert("xx");
		a.insert("xz");
		assert a.search("xx");
	}
}