import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class MathDoku {

	// To keep track of methods called
	static boolean isPuzzleSolveable = false;
	static boolean isPuzzleLoaded = false;
	static boolean isReadyToSolve = false;
	static boolean isPuzzleSolved = false;
	static boolean comp_cord_track1 = false;
	static boolean comp_cord_track2 = true;
	int dummy_sol[][];

	// Variables used in initialization and keeping track of their dimension
	static int start = 0;
	static int end = 0;
	static int matrix_cross_by_cross = 0;
	static int[][] plbMatrix;
	static int[] value_at_first;

	// Responsible for handling operations and loading puzzle and returning answer
	enum Operators {
		EQL, MUL, DIV, SUB, ADD
	}

	static Vector<Block> puzzle = new Vector<Block>();
	static CurrentLocation[][] maximumPointsStored;
	static String finalAnswer = "";

	// this function makes the string ready for the retrieval
	static void printReady(int[][] solution) {
		try {
			for (int i = 0; i < matrix_cross_by_cross; i++) {
				for (int j = 0; j < matrix_cross_by_cross; j++) {
					finalAnswer = finalAnswer + solution[i][j];
				}
				finalAnswer = finalAnswer + "\\n";
			}
		} catch (Exception e) {

		}
	}

	// this method returns the final mathdoku answer in string format
	public String print() {
		try {
			if (isPuzzleSolved) {
				return finalAnswer;
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	// This method performs the calculation of the given puzzle
	public boolean solve() {
		isPuzzleSolved = false;
		if (isPuzzleLoaded && isReadyToSolve && isPuzzleSolveable) {
			try {
				plbMatrix = new int[matrix_cross_by_cross][matrix_cross_by_cross];
				InternalCoordinateMax.findMaximumPoints();
				Computation.initialization1();
				Computation.initialization2();
				dummy_sol = CurrentLocationMaitainance.stateMaintain(start, end, puzzle, matrix_cross_by_cross);
				if (dummy_sol.length <= 0) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
			isPuzzleSolved = true;
			return true;
		} else {
			return false;
		}
	}

	// This methods check is the mathdoku is ready to solve
	public boolean readyToSolve() {
		try {
			if (isPuzzleLoaded) {
				isReadyToSolve = true;
				return isPuzzleSolveable;
			} else {
				isReadyToSolve = false;
				return isPuzzleSolveable;
			}
		} catch (Exception e) {
			return false;
		}

	}

	// This method takes the stream and checks if is loaded or not
	public boolean loadPuzzle(BufferedReader stream) {
		isPuzzleLoaded = true;
		boolean containsLine = false;
		boolean isOperatorBreak = false;
		int col = 0;
		int row = 0;
		String line = "";
		try {
			/*
			 * Will create a map that helps us in tracking the character and its all
			 * coordinates where it is present
			 */
			Map<Character, ArrayList<Coordinate>> map = new HashMap<Character, ArrayList<Coordinate>>();

			Scanner sc = new Scanner(stream);
			while (sc.hasNextLine()) { // read each row
				containsLine = true;
				line = sc.nextLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '+' || line.charAt(i) == '-' || line.charAt(i) == '/' || line.charAt(i) == '*'
							|| line.charAt(i) == '=') {
						isOperatorBreak = true;
					}
				}

				if (isOperatorBreak) {
					break;
				}
				char[] ch = line.toCharArray();

				for (char c : ch) {
					if (c == ' ') {
						continue;
					}
					// We will add the coordinates to the character if already present
					// If the character is not present we will add it
					ArrayList<Coordinate> coordinates = map.get(c);
					if (coordinates == null) {
						coordinates = new ArrayList<Coordinate>();
					}
					Coordinate coordinate = new Coordinate(row, col);
					coordinates.add(coordinate);
					map.put(c, coordinates);
					col++;
				}
				col = 0;
				row++;
			}

			matrix_cross_by_cross = row;
			if (isOperatorBreak) {
				String linearr1[] = line.split(" ");
				String linearr[] = removeBlank(linearr1);
				char alphabet = linearr[0].charAt(0);
				int num = Integer.parseInt(linearr[1]);
				Operators op = null;
				if (linearr[2].equalsIgnoreCase("="))
					op = Operators.EQL;
				else {
					String o = linearr[2];
					char Operator = o.charAt(0);
					op = OperatorDecider.match_piece2(Operator);
				}
				ArrayList<Coordinate> coordinates = map.get(alphabet);

				Block p = new Block(op, num, coordinates);
				puzzle.add(p);
				while (sc.hasNextLine()) {
					line = sc.nextLine();
					if (null == line || line.trim().isEmpty()) {
						continue;
					}
					String linearr3[] = line.split(" ");
					String linearr4[] = removeBlank(linearr3);
					char A1 = linearr4[0].charAt(0);
					int num1 = Integer.parseInt(linearr4[1]);
					Operators op1 = null;
					if (linearr4[2].equalsIgnoreCase("="))
						op1 = Operators.EQL;
					else {
						String o = linearr4[2];
						char Operator = o.charAt(0);
						op1 = OperatorDecider.match_piece2(Operator);
					}
					ArrayList<Coordinate> coordinates2 = map.get(A1);

					Block p2 = new Block(op1, num1, coordinates2);
					puzzle.add(p2);
				}
			} else {
				if (!containsLine) {
					sc.close();
					return false;
				}
			}

			if (!isOperatorBreak && containsLine) {
				sc.close();
				isPuzzleSolveable = false;
				return true;
			}

			sc.close();
		} catch (Exception e) {
			isPuzzleSolveable = false;
			return false;
		}
		isPuzzleSolveable = true;
		return true;
	}

	private String[] removeBlank(String[] linearr2) {
		// https://www.tutorialspoint.com/remove-null-value-from-a-String-array-in-Java
		List<String> val = new ArrayList<String>();
		for (String data : linearr2) {
			if (data.trim().equalsIgnoreCase("") || data.trim().equalsIgnoreCase(" ") || data == null) {

			} else {
				val.add(data);
			}
		}
		String[] target = val.toArray(new String[val.size()]);
		return target;
	}

	// This method returns the number of choices puzzle has to make
	public int choices() {
		int choices = 0;
		if (isPuzzleSolved) {
			choices = CurrentLocationMaitainance.undoOperation();
			return choices;
		} else {
			return choices;
		}

	}
}