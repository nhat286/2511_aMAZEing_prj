
public abstract class SpecialItems {
	
	int[][] coordinates = new int[1][2];
	
	
	public SpecialItems(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
	}

	public int[][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int x, int y) {
		this.coordinates[0][0] = x;
		this.coordinates[0][1] = y;
	}
	
	public void special_effect() {
		
	}
}
