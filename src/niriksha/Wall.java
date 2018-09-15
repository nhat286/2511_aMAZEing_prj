package niriksha;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
	}
	
	@Override
	public String getType() {
		return "Wall";
	}

}
