package jae;

import eric.CoOrd;
import niriksha.Character;

public abstract class Enemy {
	//private float speedX, speedY;
	private CoOrd currPos; //position of the enemy
	private char icon;
	private char direction;
	
	/*public Enemy(float speedX, float speedY, CoOrd currPos, char icon) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.currPos = currPos;
		this.icon = icon;
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
	//speed will change the coordinates of the enemy
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	*/
	public Enemy(CoOrd currPos, char icon) {
		this.currPos = currPos;
		this.icon = icon;
		this.direction = 'v';
	}
	
	public CoOrd getCurrPos() {
		return currPos;
	}

	public void setCurrPos(CoOrd currPos) {
		this.currPos = currPos;
	}
	
	public char getDirection() {
		return this.direction;
	}
	
	public void setDirection(char direction) {
		this.direction = direction;
	}

	public abstract void enemyMovement(Character target, int border);
	public abstract String getEnemyType();
	
	public void enemyDies() {
		//how to delete the enemy???
		currPos.setXY(-1, -1);
	}
	
	public char getIcon() {
		return this.icon;
	}
}

