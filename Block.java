import java.util.ArrayList;

public class Block {
	MathDoku.Operators operator;

	public MathDoku.Operators getOperator() {
		return operator;
	}

	public void setOperator(MathDoku.Operators operator) {
		this.operator = operator;
	}

	int calc_ttl;

	public int getCalc_ttl() {
		return calc_ttl;
	}

	public void setCalc_ttl(int calc_ttl) {
		this.calc_ttl = calc_ttl;
	}

	ArrayList<Coordinate> coordinates;

	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	Block(MathDoku.Operators perform, int value, ArrayList<Coordinate> c) {
		operator = perform;
		calc_ttl = value;
		coordinates = c;
	}
}