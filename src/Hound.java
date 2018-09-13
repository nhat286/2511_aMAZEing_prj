package ass2package;

public class Hound extends Enemy{
	CoOrd hunterCoOrd;
	String enemyType;
	
	public Hound(float speedX, float speedY, CoOrd currPos, String enemyType, CoOrd hunterCoOrd) {
		super(speedX, speedY, currPos);
		this.enemyType = enemyType;
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
	}
	
	
	public String getEnemyType() {
		return this.enemyType;
	}
}

