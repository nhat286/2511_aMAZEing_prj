package jae;

import eric.CoOrd;

public abstract class Enemy {
	private float speedX, speedY;
	private CoOrd currPos; 	//position of the enemy
	private CoOrd startPos;
	private int width, height;
	
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

	public Enemy() {
		
	}

	public abstract void enemyMovement(CoOrd target);
	
	public void enemyDies() {
		//how to delete the enemy???
		currPos.setXY(-1, -1);
	}
}

