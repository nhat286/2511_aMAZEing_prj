package prj_2511;

public class Enemy {
	
	private CoOrd co_ord;
	private char icon;
	private String type;
	
	public Enemy(int x, int y, char c, String type) {
		this.co_ord = new CoOrd(x, y);
		this.icon = c;
		this.type = type;
	}

	public CoOrd getCoordinates() {
		return this.co_ord;
	}
	
	public char getIcon() {
		return this.icon;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void destroyEnemy() {
		// TODO Auto-generated method stub
	}

}
