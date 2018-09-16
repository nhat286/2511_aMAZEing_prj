package jae;

import eric.CoOrd;
import eric.InvincibilityPotion;
import niriksha.Character;
import niriksha.Potions;

public class Hunter extends Enemy {
	
	/*public Hunter(float speedX, float speedY, CoOrd currPos) {
		super(speedX, speedY, currPos, 'H');
	}
	
	public void enemyMovement(CoOrd target) {
		if(this.getCurrPos().getX() > target.getX()) {			//target.x is the 'x' location of the player character
			this.setSpeedX(-1);
		}
		if(this.getCurrPos().getX() < target.getX()) {			
			this.setSpeedX(1);
		}
		if(this.getCurrPos().getY() > target.getY()) {			
			this.setSpeedY(-1);
		}
		if(this.getCurrPos().getY() < target.getY()) {			
			this.setSpeedY(1);
		}
	}*/
	
	public Hunter(CoOrd currPos) {
		super(currPos, 'H');
	}
	
	/**
	 * Prompts the movement of the Hunter towards the character
	 * 
	 * @param the character's information and the border of the maze
	 * @post the hunter moves closer to character
	 */
	@Override
	public void enemyMovement(Character target, int border) {
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		moveCloser(this.getCurrPos(), target.getCoordinates(), border);
	}
	
	/**
	 * Returns the type of enemy 
	 * 
	 * @post type of enemy
	 */
	@Override
	public String getEnemyType() {
		return "Hunter";
	}
	
	/**
	 * Hunter moves closer based on the movement of the character
	 *  
	 * @param coordinates of the character and itself, and, the border of the maze
	 * @post the hunter moves closer to character
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
	 * Make a new copy of the Hunter
	 * @return a new enemy object that has the same information
	 */
	@Override
	public Enemy copy() {
		return new Hunter(this.getCurrPos());
	}
}

