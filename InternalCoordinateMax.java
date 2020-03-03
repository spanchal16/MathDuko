public class InternalCoordinateMax {
	static void findMaximumPoints() {
		int init1 = 0;
		int init2 = 0;
		Computation.initialization();
		for (Block particular_cord : MathDoku.puzzle) {
			Coordinate comapre_cord = new Coordinate(init1, init2);
			for (Coordinate point : particular_cord.coordinates) {
				if (comapre_cord.getX() == point.getX()) {
					if (comapre_cord.getY() < point.getY()) {
						comapre_cord.y = point.getY();
					}
				} else if (comapre_cord.getX() < point.getX()) {
					comapre_cord.x = point.getX();
					comapre_cord.y = point.getY();
				}
			}
			MathDoku.maximumPointsStored[comapre_cord.x][comapre_cord.y] = new CurrentLocation(
					MathDoku.comp_cord_track2, particular_cord);
		}
	}
}
