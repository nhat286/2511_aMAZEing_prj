package ass2package;

public class Hunter extends Enemy{
	public Hunter() {
		super();
		// TODO Auto-generated constructor stub
	}

	boolean isResting;
	
	
	void enemyMovement() {
		if(this.getCurrPos().getX() > target.x) {			//target.x is the 'x' location of the player character
			this.setSpeedX(-1);
		}
		if(this.getCurrPos().getX() < target.x) {			
			this.setSpeedX(1);
		}
		if(this.getCurrPos().getY() > target.y) {			
			this.setSpeedY(-1);
		}
		if(this.getCurrPos().getY() < target.y) {			
			this.setSpeedY(1);
		}
	}
	

}

