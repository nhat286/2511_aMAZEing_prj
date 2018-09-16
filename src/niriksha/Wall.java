package niriksha;

public class Wall extends Obstacle {
	
	public Wall(int x, int y) {
		super(x, y, '#');
	}
	
	@Override
	public String getType() {
		return "Wall";
	}
	
	@Override
	public Obstacle copy() {
		return new Wall(this.getCoordinates().getX(), this.getCoordinates().getY());
	}

}
