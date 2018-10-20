package jae;

import java.io.Serializable;

import application.Sprite;
import eric.CoOrd;
import niriksha.Character;
import niriksha.HoverInvincibleCharacter;
import niriksha.InvincibleCharacter;

public abstract class Enemy implements Distance, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5897941209661989811L;
	private CoOrd currPos;
	private char icon;
	private char direction;
	private Sprite sprite;
	
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
		return this.currPos;
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

	public void enemyMovement(Character target, int border) {
		if (target.getState() instanceof InvincibleCharacter || 
				target.getState() instanceof HoverInvincibleCharacter)
			moveAway(this, target.getCoordinates(), border);
		else {
			// if return true then execute move closer
			if (specialMovement(target, border))
				moveCloser(this, target.getCoordinates(), border);
		}
	}
	
	public abstract boolean specialMovement(Character target, int border);
	public abstract String getEnemyType();
	public abstract void updateImage();
	
	public void enemyDies() {
		this.currPos.setXY(-1, -1);
		this.sprite = null;
	}
	
	public char getIcon() {
		return this.icon;
	}
	 /**
	  * Make a copy of enemy
	  * @return new enemy object which has same information
	  */
	public abstract Enemy copy();
	
	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Sprite s) {
		this.sprite = s;
	}
}

