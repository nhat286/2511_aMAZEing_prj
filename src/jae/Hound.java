package jae;

import eric.CoOrd;
import eric.InvincibilityPotion;
import niriksha.Character;
import niriksha.Potions;

public class Hound extends Enemy {
	CoOrd hunterCoOrd;
	
	/*public Hound(float speedX, float speedY, CoOrd currPos, CoOrd hunterCoOrd) {
		super(speedX, speedY, currPos, 'D');
		this.hunterCoOrd = hunterCoOrd;
	}
	
	public void enemyMovement(CoOrd target) {
		CoOrd houndLocation = calculateTargetLocation(target);
		//hound will move to the location it needs to go
		if(this.getCurrPos().getX() > houndLocation.getX()) {			//target.x is the 'x' location of the player character
			this.setSpeedX(-1);
		}
		if(this.getCurrPos().getX() < houndLocation.getX()) {			
			this.setSpeedX(1);
		}
		if(this.getCurrPos().getY() > houndLocation.getY()) {			
			this.setSpeedY(-1);
		}
		if(this.getCurrPos().getY() < houndLocation.getY()) {			
			this.setSpeedY(1);
		}
		//what if that location is beyond the walls???
	}
	
	public CoOrd calculateTargetLocation(CoOrd target) {
		int houndX = 0;
		int houndY = 0;
		if(hunterCoOrd.getX() > target.getX()) {
			houndX = target.getX() - (hunterCoOrd.getX() - target.getX());
		}else if(hunterCoOrd.getX() < target.getX()) {
			houndX = target.getX() + (target.getX()- hunterCoOrd.getX());
		}else {//if the target and hunter are in the same x coordinate
			houndX = target.getX();
		}
		//to get the y-coordinate of the hound
		if(hunterCoOrd.getY() > target.getY()) {
			houndY = target.getY() - (hunterCoOrd.getY() - target.getY());
		}else if(hunterCoOrd.getY() < target.getY()) {
			houndY = target.getY() + (target.getY()- hunterCoOrd.getY());
		}else {//if the target and hunter are in the same x coordinate
			houndY = target.getY();
		}
		CoOrd houndLocation = new CoOrd(houndX, houndY);
		//what if the target location is inside a wall?
		return houndLocation;
	}*/
	
	public Hound(CoOrd currPos) {//, CoOrd hunterCoOrd) {
		super(currPos, 'D');
		//this.hunterCoOrd = hunterCoOrd;
		this.hunterCoOrd = null;
	}
	
	public void linkHunter(CoOrd hunterCoOrd) {
		this.hunterCoOrd = hunterCoOrd;
	}
	
	public CoOrd getHunterCoOrd() {
		return this.hunterCoOrd;
	}
	
	/**
	 * Moves the Hound such that the character is between it and the Hound
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hound moves to the target location
	 */
	@Override
	public void enemyMovement(Character target, int border) {
		//this.setCurrPos(calculateTargetLocation(target.getCoordinates()));
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
		if (this.hunterCoOrd == null || this.hunterCoOrd.getX() < 0) {
			moveCloser(this.getCurrPos(), target.getCoordinates(), border);
			return;
		}
		/*
		 * Try to position itself so character is between it and the Hunter
		 */
		if (this.hunterCoOrd.getY() < ch.getY()) {
			if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			} else if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			}
		} else if (this.hunterCoOrd.getY() > ch.getY()) {
			if (me.getY() > ch.getY()) {
				this.getCurrPos().moveLeft();
			} else if (me.getY() < ch.getY()) {
				this.getCurrPos().moveRight(border);
			}
		} else if (this.hunterCoOrd.getX() < ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		} else if (this.hunterCoOrd.getX() > ch.getX()) {
			if (me.getX() < ch.getX()) {
				this.getCurrPos().moveDown(border);
			} else if (me.getX() > ch.getX()) {
				this.getCurrPos().moveUp();
			}
		}
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Hound";
	}
	
	/**
	 * Try to locate character's location and move towards that
	 */
	@Override
	public void moveCloser(CoOrd self, CoOrd target, int border) {
		int x_difference = self.getX() - target.getX();
		if (x_difference < 0) {
			if (this.getDirection() != 'v')
				this.setDirection('v');
			else
				this.getCurrPos().moveDown(border);
			return;
		} else if (x_difference > 0) {
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
		} else if (y_difference > 0) {
			if (this.getDirection() != '<')
				this.setDirection('<');
			else
				this.getCurrPos().moveLeft();
			return;
		}
	}
	
	/**
	 * Make a new copy of the Hound
	 * @return a new enenmy object which has the same information
	 */
	@Override
	public Enemy copy() {
		return new Hound(this.getCurrPos());
	}
}

