package jae;

import eric.CoOrd;

public class Hunter extends Enemy{
	public Hunter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Hunter(int x, int y) {
		super(x, y, 'H');
	}

	boolean isResting;
	
	@Override
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
	}
	

}

