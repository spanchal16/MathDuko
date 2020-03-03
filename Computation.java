public class Computation {

	public static void initialization() {
		int row_col_square = MathDoku.matrix_cross_by_cross;
		MathDoku.maximumPointsStored = new CurrentLocation[row_col_square][row_col_square];
		for (int i = 0; i < row_col_square; i++) {
			for (int j = 0; j < row_col_square; j++) {
				MathDoku.maximumPointsStored[i][j] = new CurrentLocation(MathDoku.comp_cord_track1, null);
			}
		}
	}

	public static void initialization1() {
		int row_col_count = MathDoku.matrix_cross_by_cross;
		CurrentLocationMaitainance.ccl = new CurrentCheckedLocation[row_col_count][row_col_count];
		for (int i = 0; i < row_col_count; i++) {
			for (int j = 0; j < row_col_count; j++) {
				CurrentLocationMaitainance.ccl[i][j] = new CurrentCheckedLocation();
			}
		}
	}

	public static void initialization2() {
		int row_col_square = MathDoku.matrix_cross_by_cross;
		MathDoku.value_at_first = new int[row_col_square];
		for (int i = 0; i < row_col_square; i++) {
			MathDoku.value_at_first[i] = 1;
		}
	}

	public static void col_row_computation(int matrix_cross_by_cross, int[] filled_ver, int[] filled_hor,
			int[] col_avail, int[] row_avail, int[] avail) {
		try {
			for (int k = 0; k < matrix_cross_by_cross; k++) {
				avail[k] = 0;
				if (filled_ver[k] == 1 && filled_hor[k] == 1) {
					avail[k] = 1;
				}
				col_avail[k] = filled_hor[k];
				row_avail[k] = filled_ver[k];
			}
		} catch (Exception e) {

		}
	}

	public static void initialization3(CurrentCheckedLocation[][] ccl, int start, int end, int matrix_cross_by_cross) {
		if (ccl[start][end].isCovered == false) {
			ccl[start][end].values_can_used = new int[matrix_cross_by_cross];
			ccl[start][end].track_used_column_values = new int[matrix_cross_by_cross];
			ccl[start][end].track_used_row_values = new int[matrix_cross_by_cross];
			ccl[start][end].isCovered = true;
		}
	}

	public static void initialization4(int[][] start, int[][] end, int matrix_cross_by_cross) {
		for (int i = 0; i < matrix_cross_by_cross; i++) {
			for (int j = 0; j < matrix_cross_by_cross; j++)
				end[i][j] = start[i][j];
		}
	}

}
