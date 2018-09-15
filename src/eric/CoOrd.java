package eric;

public class CoOrd {
	private int x;
	private int y;
	
	public CoOrd(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveLeft() {
		if (this.y > 1) this.y--;
	}
	
	public void moveRight(int border) {
		if (this.y < border - 1) this.y++;
	}
	
	public void moveUp() {
		if (this.x > 1) this.x--;
	}
	
	public void moveDown(int border) {
		if (this.x < border - 1) this.x++;
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
		if (x >= 0 && y >= 0) {
			this.x = x;
			this.y = y;
		}
	}
	
	public void setCoOrd(CoOrd co) {
		setXY(co.getX(), co.getY());
	}

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof CoOrd)) return false;
		if (arg0 == this) return true;
		CoOrd co = (CoOrd) arg0;
		if (co.x == this.x && co.y == this.y) return true;
		return false;
	}
}
