package niriksha;

public class Pit extends Obstacle {
	
	public Pit(int x, int y) {
		super(x, y, 'B');
	}
	
	@Override
	public String getType() {
		return "Pit";
	}
	
	@Override
	public Obstacle copy() {
		return new Pit(this.getCoordinates().getX(), this.getCoordinates().getY());
	}
}
