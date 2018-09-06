
public class CoOrd {
	private int x;
	private int y;
	
	public CoOrd(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveLeft() {
		this.x--;
	}
	
	public void moveRight() {
		this.x++;
	}
	
	public void moveUp() {
		this.y++;
	}
	
	public void moveDown() {
		this.y--;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public CoOrd getCoOrd() {
		return this;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		// or call Constructor?
	}
	
	public void setCoOrd(CoOrd co) {
		setXY(co.getX(), co.getY());
	}
}
