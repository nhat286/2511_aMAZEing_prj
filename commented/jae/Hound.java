package jae;

import eric.CoOrd;
import niriksha.Character;

public class Hound extends Enemy{
	
	CoOrd hunterCoOrd;
	
	public Hound(CoOrd currPos, CoOrd hunterCoOrd) {
		super(currPos, 'D');
		this.hunterCoOrd = hunterCoOrd;
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
		
		CoOrd ch = target.getCoordinates();
		CoOrd me = this.getCurrPos();
		
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