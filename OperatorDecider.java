public class OperatorDecider {
	static boolean current_operation(int[][] puzzle, Block block) {
		switch (block.operator) {
		case ADD:
			int add = 0;
			for (Coordinate cord : block.getCoordinates()) {
				add = add + puzzle[cord.getX()][cord.getY()];

			}
			if (add == block.getCalc_ttl()) {
				return true;
			} else {
				return false;
			}
		case SUB:
			Coordinate cord1 = block.getCoordinates().get(0);
			Coordinate cord2 = block.getCoordinates().get(1);

			int cordinate1 = puzzle[cord1.getX()][cord1.getY()];
			int cordinate2 = puzzle[cord2.getX()][cord2.getY()];

			if (((cordinate1 - cordinate2) == block.getCalc_ttl())
					|| ((cordinate2 - cordinate1) == block.getCalc_ttl())) {
				return true;
			} else {
				return false;
			}
		case MUL:
			int multiplication = 1;
			for (Coordinate cord3 : block.getCoordinates()) {
				multiplication = multiplication * puzzle[cord3.getX()][cord3.getY()];

			}
			if (multiplication == block.getCalc_ttl()) {
				return true;
			} else {
				return false;
			}

		case DIV:
			Coordinate cord4 = block.getCoordinates().get(0);
			Coordinate cord5 = block.getCoordinates().get(1);
			int coordinate1 = puzzle[cord4.getX()][cord4.getY()];
			int coordinate2 = puzzle[cord5.getX()][cord5.getY()];
			if (((coordinate1 / coordinate2) == block.getCalc_ttl())
					|| ((coordinate2 / coordinate1) == block.getCalc_ttl())) {
				return true;
			} else {
				return false;
			}
		case EQL:
			Coordinate cord6 = block.getCoordinates().get(0);
			if ((block.getCalc_ttl() == puzzle[cord6.getX()][cord6.getY()])) {
				return true;
			} else {
				return false;
			}
		default:
			return false;
		}
	}

	static MathDoku.Operators match_piece2(char O) {
		MathDoku.Operators operation = null;
		switch (O) {
		case '+':
			operation = MathDoku.Operators.ADD;
			break;
		case '-':
			operation = MathDoku.Operators.SUB;
			break;
		case '*':
			operation = MathDoku.Operators.MUL;
			break;
		case '/':
			operation = MathDoku.Operators.DIV;
			break;
		default:
		}
		return operation;
	}

}
