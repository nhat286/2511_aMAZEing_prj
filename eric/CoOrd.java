package eric;

public class CoOrd {
	private int x;
	private int y;
	
	public CoOrd(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets the coordinate of object one position to the left
	 */
	public void moveLeft() {
		if (this.y > 1) this.y--;
	}
	
	/**
	 * Sets the coordinate of object one position to the right
	 */
	public void moveRight(int border) {
		if (this.y < border - 1) this.y++;
	}
	
	/**
	 * Sets the coordinate of object one position up
	 */
	public void moveUp() {
		if (this.x > 1) this.x--;
	}
	
	/**
	 * Sets the coordinate of object one position down
	 */
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
		this.x = x;
		this.y = y;
	}
	
	public void setCoOrd(CoOrd co) {
		setXY(co.getX(), co.getY());
	}
	
	/**
	 * Determines if the coordinates are equal
	 * 
	 * @return return true of the coordinates are equal else false
	 */
	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof CoOrd)) return false;
		if (arg0 == this) return true;
		CoOrd co = (CoOrd) arg0;
		if (co.x == this.x && co.y == this.y) return true;
		return false;
	}
}