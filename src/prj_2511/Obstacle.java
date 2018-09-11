package prj_2511;

public abstract class Obstacle {
	private CoOrd co_ord;
	private char icon;
	
	public Obstacle(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public abstract String getType();
}
