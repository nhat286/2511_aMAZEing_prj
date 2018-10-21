package jae;

import eric.CoOrd;
import eric.InvincibilityPotion;
import niriksha.Character;
import niriksha.Potions;

public class Hound extends Enemy implements Distance{
	
	CoOrd hunterCoOrd;
	
	public Hound(CoOrd currPos) {
		super(currPos, 'D');
		this.hunterCoOrd = null;
	}
	
	public void linkHunter(CoOrd hunterCoOrd) {
		this.hunterCoOrd = hunterCoOrd;
	}
	
	public CoOrd getHunterCoOrd() {
		return this.hunterCoOrd;
	}
	
	/*
	 * Calculates coordinates of the Hound using the Hunter's location
	 * 
	 * @param coordinates of the character
	 * @post coordinates of the target location of the Hound
	 */
	public CoOrd calculateTargetLocation(CoOrd target) {
		
		int houndX = 0;
		int houndY = 0;
		
		if(hunterCoOrd.getX() > target.getX()) {
			houndX = target.getX() - (hunterCoOrd.getX() - target.getX());
		}
		else if(hunterCoOrd.getX() < target.getX()) {
			houndX = target.getX() + (target.getX()- hunterCoOrd.getX());
		}
		else {
			houndX = target.getX();
		}
		
		if(hunterCoOrd.getY() > target.getY()) {
			houndY = target.getY() - (hunterCoOrd.getY() - target.getY());
		}
		else if(hunterCoOrd.getY() < target.getY()) {
			houndY = target.getY() + (target.getY()- hunterCoOrd.getY());
		}
		else {
			houndY = target.getY();
		}
		
		CoOrd houndLocation = new CoOrd(houndX, houndY);
		
		return houndLocation;
	}
	
	/*
	 * Moves the Hound such that the character is between it and the Hound
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hound moves to the target location
	 */
	@Override
	public void enemyMovement(Character target, int border) {
		
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		
		CoOrd ch = target.getCoordinates();
		CoOrd me = this.getCurrPos();
		
		/*
		 * If no hunter is linked, use Hunter's logic to follow player
		 */
		if (this.hunterCoOrd == null) {
			moveCloser(this.getCurrPos(), target.getCoordinates(), border);
			return;
		}
		
		if (this.hunterCoOrd.getY() < ch.getY()) {
			if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			} else if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			}
		} 
		else if (this.hunterCoOrd.getY() > ch.getY()) {
			if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			} else if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			}
		} 
		else if (this.hunterCoOrd.getX() < ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		} 
		else if (this.hunterCoOrd.getX() > ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		}
	}
	
	@Override
	public void moveCloser(CoOrd self, CoOrd target, int border) {
		int x_difference = self.getX() - target.getX();
		
		if (x_difference < 0) {
			if (this.getDirection() != 'v')
				this.setDirection('v');
			else
				this.getCurrPos().moveDown(border);
			return;
		} 
		
		else if (x_difference > 0) {
			if (this.getDirection() != '^')
				this.setDirection('^');
			else
				this.getCurrPos().moveUp();
			return;
		}
		
		int y_difference = self.getY() - target.getY();
		
		if (y_difference < 0) {
			if (this.getDirection() != '>')
				this.setDirection('>');
			else
				this.getCurrPos().moveRight(border);
			return;
		} 
		else if (y_difference > 0) {
			if (this.getDirection() != '<')
				this.setDirection('<');
			else
				this.getCurrPos().moveLeft();
			return;
		}
	}
	
	@Override
	public Enemy copy() {
		return new Hound(this.getCurrPos());
	}
	
	/*
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Hound";
	}
	
}