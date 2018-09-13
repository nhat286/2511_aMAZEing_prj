package jae;

import eric.CoOrd;

public abstract class Enemy {
	private float speedX, speedY;
	private CoOrd currPos; 	//position of the enemy
	private CoOrd startPos;
	private int width, height;
	
	//Eric's
	private char icon;
	private CoOrd co_ord;
	//
	
	public Enemy(float speedX, float speedY, CoOrd currPos, CoOrd startPos, int width, int height) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.currPos = currPos;
		this.startPos = startPos;
		this.width = width;
		this.height = height;
	}
	
	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public CoOrd getCurrPos() {
		return currPos;
	}

	public void setCurrPos(CoOrd currPos) {
		this.currPos = currPos;
	}

	public CoOrd getStartPos() {
		return startPos;
	}

	public void setStartPos(CoOrd startPos) {
		this.startPos = startPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	// Eric's
	public Enemy(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void move() {
		
	}
	
	public String getType() {
		return "Enenmy";
	}
	
	public void enemyDestroy() {
		this.co_ord.setXY(-1, -1);
	}
	//
	public Enemy() {
		
	}

	public abstract void enemyMovement(CoOrd target);
	
	public void enemyDies() {
		//how to delete the enemy???
		currPos.setXY(-1, -1);
	}
}

