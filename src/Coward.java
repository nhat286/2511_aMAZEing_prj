package ass2package;
import static java.lang.Math.sqrt;



public class Coward extends Enemy{
	
	String enemyType;
	
	public Coward(float speedX, float speedY, CoOrd currPos, String enemyType) {
		super(speedX, speedY, currPos);
		this.enemyType = enemyType;
	}
	
	public void enemyMovement(CoOrd target) {
		double distance = calcDistance(target);
		if(distance >= 500) {
			chaseTarget(target);
		}else {
			fleeFromTarget(target);
		}
	}
	
	public void chaseTarget(CoOrd target) {
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
	
	public void fleeFromTarget(CoOrd target) {
		double distance  = calcDistance(target);
		int x = 1, y = 1;
		//we should calculate the orientation of the coward from using the target as the origin
		if(this.getCurrPos().getX() > target.getX()) {			//target.x is the 'x' location of the player character
			x = 1;
		}
		if(this.getCurrPos().getX() < target.getX()) {			
			x = -1;
		}
		if(this.getCurrPos().getY() > target.getY()) {			
			y = 1;
		}
		if(this.getCurrPos().getY() < target.getY()) {			
			y = -1;
		}
		//not sure if you need to use the while loop here or the update in the mazesystem does the job
		if(distance < 500) {					//while the coward is within a 500 pixel radius
			if(x == 1) {
				this.setSpeedX(1);   			//the coward will run in the distance immediately away from the character
				if(y == 1) {
					this.setSpeedY(1);
				}else if(y == -1) {
					this.setSpeedX(-1);
				}
			}else if(x == -1) {
				this.setSpeedX(-1);
				if(y == 1) {
					this.setSpeedY(1);
				}else if(y == -1) {
					this.setSpeedY(-1);
				}
			}
			distance = calcDistance(target);
		}
	}
	
	public double calcDistance(CoOrd target) {
		double distance = sqrt((this.getCurrPos().getX()-target.getX())*(this.getCurrPos().getX()-target.getX())+(this.getCurrPos().getY()-target.getY())*(this.getCurrPos().getY()-target.getY()));
		return distance;
	}
	
	public String getEnemyType() {
		return enemyType;
	}
	
	
}
