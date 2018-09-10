package prj_2511;

public class Enemy {
	
	private CoOrd co_ord;
	private char icon;
	
	public Enemy(int x, int y, char c) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
	}
	
	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public void destroyEnemy() {
		// TODO Auto-generated method stub
	}

}
