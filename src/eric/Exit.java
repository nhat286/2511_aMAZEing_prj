package eric;

public class Exit {
	private CoOrd co_ord;
	private char icon;
	
	public Exit(int x, int y) {
		this.co_ord = new CoOrd(x, y);
		this.icon = 'F';
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public void setCoordinates(int x, int y) {
		this.co_ord.setXY(x, y);
	}
	
	public char getIcon() {
		return this.icon;
	}
}
