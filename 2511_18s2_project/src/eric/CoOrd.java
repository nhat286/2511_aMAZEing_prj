package eric;

import application.Sprite;

public class CoOrd {
	private int x;
	private int y;
	private int baseVel;
	
	public CoOrd(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CoOrd(int x, int y, int vel) {
		this.x = x;
		this.y = y;
		this.baseVel= vel;
	}
	
	public int getVelocity() {
		return this.baseVel;
	}
	
	public void setVelocity(int vel) {
		this.baseVel = vel;
	}
	
	/**
	 * Sets the coordinate of object one position to the left
	 * Doesn't allow moving onto or beyond maze border
	 */
	public void moveLeft() {
		if (this.x > 0) this.x--;
	}
	
	public void moveLeft(Sprite s) {
		if (this.x > 0) s.setVelocity(-baseVel, 0);
		
		this.setXY((int) (s.getPositionX())/32,
				(int) (s.getPositionY())/32);
	}
	
	/**
	 * Sets the coordinate of object one position to the right
	 * Doesn't allow moving onto or beyond maze border
	 */
	public void moveRight(int border) {
		if (this.x < border - 1) this.x++;
	}
	
	public void moveRight(int border, Sprite s) {
		if (this.x < border - 1) s.setVelocity(baseVel, 0);
		
		this.setXY((int) (s.getPositionX())/32+1,
				(int) (s.getPositionY())/32);
	}
	
	/**
	 * Sets the coordinate of object one position up
	 * Doesn't allow moving onto or beyond maze border
	 */
	public void moveUp() {
		if (this.y > 0) this.y--;
	}
	
	public void moveUp(Sprite s) {
		if (this.y > 0) s.setVelocity(0, -baseVel);
		
		this.setXY((int) (s.getPositionX())/32,
				(int) (s.getPositionY())/32);
	}
	
	/**
	 * Sets the coordinate of object one position down
	 * Doesn't allow moving onto or beyond maze border
	 */
	public void moveDown(int border) {
		if (this.y < border - 1) this.y++;
	}
	
	public void moveDown(int border, Sprite s) {
		if (this.y < border - 1) s.setVelocity(0, baseVel);
		
		this.setXY((int) (s.getPositionX())/32,
				(int) (s.getPositionY())/32+1);
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
	
	@Override
	public String toString() {
		return "x: " + this.x + ", y: " + this.y;
	}
}
