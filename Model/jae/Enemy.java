package jae;

import eric.CoOrd;
import niriksha.Character;

public abstract class Enemy implements Distance{
	//private float speedX, speedY;
	private CoOrd currPos; //position of the enemy
	private char icon;
	private char direction;
	
	/**
	 * Constructor to instantiate enemy object, by default always facing downwards
	 * @param currPos the valid starting position of the enemy in the maze
	 * @param icon representing the enemy on the map, passed in by subclasses
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
		this.currPos.setXY(-1, -1);
	}
	
	public char getIcon() {
		return this.icon;
	}
	 /**
	  * Make a copy of enemy
	  * @return new enemy object which has same information
	  */
	public abstract Enemy copy();
}

