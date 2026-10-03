/*
A Tic-Tac-Toe board is given as a string array board. Return True if and only 
if it is possible to reach this board position during the course of a valid 
tic-tac-toe game.

The board is a 3 x 3 array, and consists of characters " ", "X", and "O".  
The " " character represents an empty square.

Here are the rules of Tic-Tac-Toe:

* Players take turns placing characters into empty squares (" ").
* The first player always places "X" characters, while the second player 
  always places "O" characters.
* "X" and "O" characters are always placed into empty squares, never filled 
  ones.
* The game ends when there are 3 of the same (non-empty) character filling 
  any row, column, or diagonal.
* The game also ends if all squares are non-empty.
* No more moves can be played if the game is over.
*/

public class TicTacToe {

	public static boolean victoryFor(String[] b, char a) {
		// Check rows
		String a3 = new String(new char[3]).replace('\0', a);
		for (String row: b) {
			if (row.equals(a3))
				return true;
		}

		// Check columns
		for (int i = 0; i < 3; i++) {
			if (b[0].charAt(i) == a && 
				b[1].charAt(i) == a && 
				b[2].charAt(i) == a)
			return true;
		}

		// Check \
		if (b[0].charAt(0) == a && 
			b[1].charAt(1) == a && 
			b[2].charAt(2) == a)
			return true;

		// Check /
		if (b[0].charAt(2) == a && 
			b[1].charAt(1) == a && 
			b[2].charAt(0) == a)
			return true;

		return false;
	}

	public static boolean validTicTacToe(String[] board) {
		// Count X & O
		int xC = 0;
		int oC = 0;
		for (String row: board) {
			for (int i = 0; i < row.length(); i++) {
				if (row.charAt(i) == 'X')
					xC++;
				else if (row.charAt(i) == 'O')
					oC++;
			}
		}

		// Out of order turns
		if (oC > xC || xC > (oC+1))
			return false;

		boolean xWon = false;
		if (xC > 2)
			xWon = victoryFor(board, 'X');
		boolean oWon = false;
		if (oC > 2)
			oWon = victoryFor(board, 'O');

		// X can only win with more turns than O
		if (xC == oC && xWon)
			return false;

		// O can only win with equal turns to X
		if (xC > oC && oWon)
			return false;

		return true;
	}

	public static boolean printResult(String[] board) {
		for (String row: board)
			System.out.println(" | " + row);
		boolean retVal = validTicTacToe(board);
		System.out.println(" +----> " + retVal);
		return retVal;
	}

	public static void main(String[] args) {
		// Test 1
		String[] b1 = {"XOX",
		               "OXO",
		               "X  "};
		assert printResult(b1) == true;
		// Test 2
		String[] b2 = {"X  ",
		               "O O",
		               "   "};
		assert printResult(b2) == false;
		// Test 3
		String[] b3 = {"XXX",
		               "OXO",
		               "XO "};
		assert printResult(b3) == false;
		// Test 
		String[] b4 = {"XXX",
		               "   ",
		               "OOO"};
		assert printResult(b4) == false;
		// Test 
		String[] b5 = {"XXX",
		               "O  ",
		               "O  "};
		assert printResult(b5) == true;
		// Test 
		String[] b6 = {"XOO",
		               "OX ",
		               "  X"};
		assert printResult(b6) == false;
		// Test 
		String[] b7 = {"  O",
		               "XOX",
		               "OXX"};
		assert printResult(b7) == false;
	}
}