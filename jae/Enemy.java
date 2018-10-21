package jae;

import eric.CoOrd;
import niriksha.Character;

public abstract class Enemy {
	
	private CoOrd currPos;
	private char icon;
	private char direction;
			
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
		currPos.setXY(-1, -1);
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public abstract Enemy copy();
}