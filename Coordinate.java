public class Coordinate{ 
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	int x; 
	int y;
	Coordinate(int a, int b)
	{  
		x = a; y = b;
	}
}