package jae;

import eric.CoOrd;
import eric.InvincibilityPotion;
import niriksha.Character;
import niriksha.Potions;

public class Strategist extends Enemy {
	//start off from the starting position and this enemy will position itself mid way between the player and the exit
	
	/*public Strategist(float speedX, float speedY, CoOrd currPos) {
		super(speedX, speedY, currPos, 'S');
	}
	
	public void enemyMovement(CoOrd predictedTarget) {
		if(this.getCurrPos().getX() > predictedTarget.getX()) {			//target.x is the 'x' location of the player character
			this.setSpeedX(-1);
		}
		if(this.getCurrPos().getX() < predictedTarget.getX()) {			
			this.setSpeedX(1);
		}
		if(this.getCurrPos().getY() > predictedTarget.getY()) {			
			this.setSpeedY(-1);
		}
		if(this.getCurrPos().getY() < predictedTarget.getY()) {			
			this.setSpeedY(1);
		}
		
	}*/
	
	public Strategist(CoOrd currPos) {
		super(currPos, 'S');
	}
	
	@Override
	public void enemyMovement(Character target, int border) {
		for (Potions p : target.getActivePotion()) {
			if (p instanceof InvincibilityPotion && ((InvincibilityPotion) p).turnsRemaining() > 0) {
				moveAway(this, target.getCoordinates(), border);
				return;
			}
		}
		CoOrd ch = new CoOrd(target.getCoordinates().getX(), target.getCoordinates().getY());
		if (target.getIcon() == '<') {
			ch.moveLeft();
		} else if (target.getIcon() == '>') {
			ch.moveRight(border);
		} else if (target.getIcon() == '^') {
			ch.moveUp();
		} else if (target.getIcon() == 'v') {
			ch.moveDown(border);
		}
		moveCloser(this.getCurrPos(), ch, border);
	}
	
	@Override
	public String getEnemyType() {
		return "Strategist";
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
	
	@Override
	public Enemy copy() {
		return new Strategist(this.getCurrPos());
	}
}
