import java.util.Vector;

public class CurrentLocationMaitainance {
	static CurrentCheckedLocation[][] ccl;
	static int[] filled_hor;
	static int[] filled_ver;
	static int choices = 0;
	static int[][] sol;

	static int[][] stateMaintain(int start, int end, Vector<Block> puzzle, int matrix_cross_by_cross) {
		choices++;
		Computation.initialization3(ccl, start, end, matrix_cross_by_cross);

		int[] occupied = ccl[start][end].getValues_can_used();
		int[] occupied_hor = ccl[start][end].getTrack_used_column_values();
		int[] occupied_ver = ccl[start][end].getTrack_used_row_values();

		filled_hor = filled_ver = MathDoku.value_at_first;
		if (start != 0) {
			filled_hor = ccl[start - 1][end].getTrack_used_column_values();
		}
		if (end != 0) {
			filled_ver = ccl[start][end - 1].getTrack_used_row_values();
		}

		Computation.col_row_computation(matrix_cross_by_cross, filled_ver, filled_hor, occupied_hor, occupied_ver,
				occupied);

		for (int i = 0; i < matrix_cross_by_cross; i++) {
			if (occupied[i] == 0) {
				continue;
			}
			MathDoku.plbMatrix[start][end] = i + 1;

			if (puzzle != null) {
				if (MathDoku.maximumPointsStored[start][end].existanceCheck) {
					boolean isValid = OperatorDecider.current_operation(MathDoku.plbMatrix,
							MathDoku.maximumPointsStored[start][end].currentBlock);
					if (!isValid) {
						MathDoku.plbMatrix[start][end] = 0;
						continue;
					}
				}
			}

			occupied_hor[i] = 0;
			occupied_ver[i] = 0;

			if (end + 1 == matrix_cross_by_cross && start + 1 != matrix_cross_by_cross) {
				stateMaintain(start + 1, 0, puzzle, matrix_cross_by_cross);
			} else if (end + 1 != matrix_cross_by_cross) {
				stateMaintain(start, end + 1, puzzle, matrix_cross_by_cross);
			} else {
				sol = new int[matrix_cross_by_cross][matrix_cross_by_cross];
				Computation.initialization4(MathDoku.plbMatrix, sol, matrix_cross_by_cross);
				MathDoku.printReady(sol);
			}

			MathDoku.plbMatrix[start][end] = 0;
			occupied_hor[i] = 1;
			occupied_ver[i] = 1;
		}
		return sol;
	}

	public static int undoOperation() {
		return choices;
	}
}
