package ass2package;

public class Strategist extends Enemy{
	//start off from the starting position and this enemy will position itself mid way between the player and the exit
	String enemyType;
	//boolean isResting;
	
	public Strategist(float speedX, float speedY, CoOrd currPos, String enemyType) {
		super(speedX, speedY, currPos);
		this.enemyType = enemyType;
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
		
	}
	
	public String getEnemyType() {
		return enemyType;
	}
}

