
public abstract class SpecialItems {
	
	CoOrd coordinates;
	
	public SpecialItems(int x, int y) {
		this.coordinates = new CoOrd(x, y);
	}

	public CoOrd get_item_coordinates() {
		return this.coordinates;
	}

	public void set_item_coordinates(int x, int y) {
		this.coordinates.setXY(x, y);
	}
	
	public void set_item_coordinates(CoOrd co) {
		this.coordinates.setCoOrd(co);
	}
	
	public abstract int special_effect(Weapon w);
	public abstract int special_effect(Obstacle o);
	public abstract int special_effect(Enemy e);
}
