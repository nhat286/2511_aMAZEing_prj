package prj_2511;

public class Maze {
	/*
	 * Character player;
	 * ArrayList<Item> item_drops;
	 * ArrayList<Weapon> weapon_drops;
	 * ArrayList<Enemy> enemies;
	 * ArrayList<Obstacle> obstacles;
	 */
	
	private char[][] map;
	private int size;
	
	public Maze(int size) {//Character c, int size) {
		//this.player = c;
		this.size = size;
		this.map = new char[size][size];
	}
	
	public void printMap() {
		updateMap();
		this.map = drawMap();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public char[][] drawMap() {
		/*
		 * read co-ords
		 */
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				if (i == 0 || i == this.size - 1 || j == 0 || j == this.size - 1)
					this.map[i][j] = '#';
				else
					this.map[i][j] = ' ';
			}
		}
		return this.map;
	}
	
	public void updateMap() {
		
	}
	
	/*
	 * public HashMap<Enemy : Integer> enemyStat() {
	 *     //doing sth here
	 * }
	 */
}
