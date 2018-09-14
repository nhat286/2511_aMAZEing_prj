package jae;

import eric.CoOrd;
import niriksha.Character;

public class Hunter extends Enemy implements Distance {
	
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
	
	@Override
	public void enemyMovement(Character target, int border) {
		moveCloser(this.getCurrPos(), target.getCoordinates(), border);
	}
	
	@Override
	public String getEnemyType() {
		return "Hunter";
	}
	//the abstract method MUST have the same input variables.... but the input variables needed by different enemies are different..........
	
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
	
}
