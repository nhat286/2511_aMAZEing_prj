
public abstract class Weapon {
	
	CoOrd coordinates;
	boolean picked_up;
		 
	public Weapon(int x, int y) {
		this.coordinates = new CoOrd(x, y); 
	}

	public CoOrd get_weapon_coordinates() {
		return this.coordinates;
	}

	public void set_weapon_coordinates(int x, int y) {
		this.coordinates.setXY(x, y);
	}
	
	public void set_weapon_coordinates(CoOrd co) {
		this.coordinates.setCoOrd(co);
	}
	
	public abstract int action();

}
