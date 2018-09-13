package ass2package;

public abstract class Enemy{
	private float speedX, speedY;
	private CoOrd currPos; 	//position of the enemy

	
	public Enemy(float speedX, float speedY, CoOrd currPos) {
		this.speedX = speedX;
		this.speedY = speedY;
		this.currPos = currPos;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}
	//speed will change the coordinates of the enemy
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

	public CoOrd getCurrPos() {
		return currPos;
	}

	public void setCurrPos(CoOrd currPos) {
		this.currPos = currPos;
	}

	public abstract void enemyMovement(CoOrd target);
	public abstract String getEnemyType();
	
	public void enemyDies() {
		//how to delete the enemy???
		currPos.setXY(-1, -1);
	}
}

