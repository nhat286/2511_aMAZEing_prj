
package ass2package;

public class Hunter extends Enemy{
	String enemyType;
	public Hunter(float speedX, float speedY, CoOrd currPos, String enemyType) {
		super(speedX, speedY, currPos);
		this.enemyType = enemyType;
	}
	//boolean isResting;
	
	
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
	

	public String getEnemyType() {
		return enemyType;
	}
	//the abstract method MUST have the same input variables.... but the input variables needed by different enemies are different..........

	
}

