package eric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import niriksha.Arrow;
import niriksha.Character;
import niriksha.FloorSwitch;
import niriksha.Key;
import niriksha.Potions;
import niriksha.Treasure;
import niriksha.Weapon;
import niriksha.Obstacle;
import niriksha.Pit;
import jae.Enemy;
import jae.Hound;

public class Maze {
	
	private Character player;
	private ArrayList<Weapon> weapon_drops;
	private ArrayList<Arrow> available_arrows;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Potions> potion_drops;
	private ArrayList<FloorSwitch> switches;
	private ArrayList<Treasure> loots;
	private ArrayList<Exit> exits;
	private ArrayList<Key> keys;
	private int goal;
	private int current_cond;
	
	/*
	 * Win Condition (can be a combination):
	 * 00001 -> MazeRunner (just find the exit)
	 * 00010 -> TreasureHunter (collect all treasures)
	 * 00100 -> EnemySlayer (kill all enemies)
	 * 01000 -> Detective (find the key to unlock door)
	 * 10000 -> Brainer (solve puzzle to trigger all floor switches)
	 */
	
	public static final int RUNNER    = 0b00001;
	public static final int COLLECTOR = 0b00010;
	public static final int SLAYER    = 0b00100;
	public static final int DETECTIVE = 0b01000;
	public static final int BRAINER   = 0b10000;
	
	/**
	 * Constructor to instantiate a new maze, with all information of entities on the map
	 * @param winning_goal goal of the maze to win if pass all (or some) conditions
	 */
	public Maze(int winning_goal) {
		this.weapon_drops = new ArrayList<Weapon>();
		this.available_arrows = new ArrayList<Arrow>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
		this.switches = new ArrayList<FloorSwitch>();
		this.loots = new ArrayList<Treasure>();
		this.potion_drops = new ArrayList<Potions>();
		this.exits = new ArrayList<Exit>();
		this.keys = new ArrayList<Key>();
		this.goal = winning_goal;
		this.current_cond = 0;
	}
	
	/**
	 * Method to remove weapons and potions if weapons run out of durability or potions run out of time
	 */
	public void updateCharacterBag() {
		Iterator<Weapon> wp = this.player.getBag().getWeaponList().iterator();
		while (wp.hasNext()) {
			Weapon w = wp.next();
			if (w.getCoordinates().getX() == -1) {
				wp.remove();
				if (this.player.weaponEquipped() && w == this.player.equip_weapon)
					this.player.removeEquipped();
			}
		}
		
		Iterator<Potions> pt = this.player.getBag().getPotionList().iterator();
		ArrayList<Potions> active = this.player.getActivePotion();
		while (pt.hasNext()) {
			Potions p = pt.next();
			if (p.getCoordinates().getX() == -1) {
				pt.remove();
				active.remove(p);
			}
		}
	}
	
	/**
	 * Method to render the map for each action of character
	 * Update all entities status
	 * Remove entities from the map if co-ordinates are negative
	 * @param map the map of the current maze
	 */
	public void updateMap(char[][] map) {
		CoOrd entity = null;
		updateCharacterBag();
		CoOrd player = this.player.getCoordinates();
		
		entity = null;
		Iterator<Potions> pt_iter = this.potion_drops.iterator();
		while (pt_iter.hasNext()) {
			Potions p = pt_iter.next();
			entity = p.getCoordinates();
			if (entity.getX() < 0) pt_iter.remove();
			else map[entity.getX()][entity.getY()] = p.getIcon();
		}
		
		entity = null;
		Iterator<Weapon> wp_iter = this.weapon_drops.iterator();
		while (wp_iter.hasNext()) {
			Weapon w = wp_iter.next();
			entity = w.getCoordinates();
			if (entity.getX() < 0) {
				wp_iter.remove();
			}
			else map[entity.getX()][entity.getY()] = w.getIcon();
		}
		
		entity = null;
		Iterator<Treasure> ts_iter = this.loots.iterator();
		while (ts_iter.hasNext()) {
			Treasure t = ts_iter.next();
			entity = t.getCoord();
			if (entity.getX() < 0) ts_iter.remove();
			else map[entity.getX()][entity.getY()] = t.getIcon();
		}
		
		entity = null;
		Iterator<Key> k_iter = this.keys.iterator();
		while (k_iter.hasNext()) {
			Key k = k_iter.next();
			entity = k.getCoordinates();
			if (entity.getX() == -1) k_iter.remove();
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = k.getIcon();
		}
		
		entity = null;
		Iterator<Exit> ex_iter = this.exits.iterator();
		while (ex_iter.hasNext()) {
			Exit ex = ex_iter.next();
			entity = ex.getCoordinates();
			if (entity.getX() == -1) ex_iter.remove();
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = ex.getIcon();
		}
		
		entity = null;
		Iterator<Enemy> e_iter = this.enemies.iterator();
		while (e_iter.hasNext()) {
			Enemy e = e_iter.next();
			entity = e.getCurrPos();
			if (entity.getX() == -1) e_iter.remove();
			else if (entity.getX() >= 0) {
				e.enemyMovement(this.player, map.length);
				if (e.getCurrPos().equals(player))
					this.current_cond = -1;
				boolean die = false;
				for (Obstacle o : this.obstacles) {
					if (o instanceof Pit && e.getCurrPos().equals(((Pit) o).getCoordinates())) {
						e_iter.remove();
						die = true;
					}
				}
				if (!die) map[entity.getX()][entity.getY()] = e.getIcon();
			}
		}
		
		entity = null;
		Iterator<Obstacle> o_iter = this.obstacles.iterator();
		while (o_iter.hasNext()) {
			Obstacle o = o_iter.next();
			entity = o.getCoordinates();
			if (entity.getX() < 0) o_iter.remove();
			else map[entity.getX()][entity.getY()] = o.getIcon();
		}

		entity = null;
		Iterator<Arrow> a_iter = this.available_arrows.iterator();
		while (a_iter.hasNext()) {
			Arrow a = a_iter.next();
			if (a.isUsed()) {
				CoOrd in_front = a.getInfront();
				if (a.moving(getEntity(in_front), map.length) == 1) {
					map[in_front.getX()][in_front.getY()] = ' ';
				}
			}
			entity = a.getCoordinates();
			if (entity.getX() == -1) a_iter.remove();
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = a.getIcon();
		}
		
		map[player.getX()][player.getY()] = this.player.getIcon();
	}
	
	/**
	 * Method to link the maze with the player's character
	 * @param c the character of the player to be linked and played in the maze
	 */
	public void addCharacter(Character c) {
		this.player = c;
	}
	
	/**
	 * Method to add weapon drops on the map to be picked up by character
	 * @param w the weapon drop that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addWeaponDrop(Weapon w) {
		this.weapon_drops.add(w);
		if (w instanceof Arrow)
			this.available_arrows.add((Arrow) w);
	}
	
	/**
	 * Method to add enemies on the map to chase or try to kill the character
	 * @param e the enemy that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
	}
	
	/**
	 * Method to add obstacles on the map for more difficulty, also update floorswitch list for easier goal checking
	 * @param o the obstacle that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addObstacle(Obstacle o) {
		this.obstacles.add(o);
		if (o instanceof FloorSwitch)
			this.switches.add((FloorSwitch) o);
	}
	
	/**
	 * Method to add potion drops on the map to be picked up by character
	 * @param p the potion drop that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addPotion(Potions p) {
		this.potion_drops.add(p);
	}
	
	/**
	 * Method to add treasure on the map to be picked up by character
	 * @param t the treasure that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addTreasure(Treasure t) {
		this.loots.add(t);
	}
	
	/**
	 * Method to add exits on the map to satisfy goal if character finds and moves to an exit's co-ordinates
	 * @param e the exit that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addExit(Exit e) {
		this.exits.add(e);
	}
	
	/**
	 * Method to add keys on the map to be picked up by character, and a key is linked to a door
	 * @param k the key that has co-ordinates on the map (between 1 and map size - 1)
	 */
	public void addKey(Key k) {
		this.keys.add(k);
	}
	
	/**
	 * Method to get the object on the map with specific co-ordinates
	 * @param co valid co-ordinates of entity (between 1 and map size - 1)
	 * @return null if no entity exists at that location, else the entity with the corresponding co-ordinates
	 * 			also, return entity on top if there are multiple entities at that location
	 */
	public Object getEntity(CoOrd co) {
		for (Enemy e : this.enemies) {
			if (e.getCurrPos().equals(co)) return e;
		}
		for (Obstacle o : this.obstacles) {
			if (!(o instanceof FloorSwitch) && o.getCoordinates().equals(co)) return o;
		}
		for (Weapon w : this.weapon_drops) {
			if (w.getCoordinates().equals(co)) return w;
		}
		for (Potions p : this.potion_drops) {
			if (p.getCoordinates().equals(co)) return p;
		}
		for (FloorSwitch fs : this.switches) {
			if (fs.getCoordinates().equals(co)) return fs;
		}
		for (Treasure t : this.loots) {
			if (t.getCoord().equals(co)) return t;
		}
		for (Key k : this.keys) {
			if (k.getCoordinates().equals(co)) return k;
		}
		for (Exit e : this.exits) {
			if (e.getCoordinates().equals(co)) return e;
		}
		return null;
	}
	
	public HashMap<String, Integer> weaponStat() {
		HashMap<String, Integer> dict = new HashMap<>();
		for (Weapon i : this.weapon_drops) {
			if (!dict.containsKey(i.getType()))
				dict.put(i.getType(), new Integer(0));
			dict.put(i.getType(), new Integer(dict.get(i.getType()).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<String, Integer> obstacleStat() {
		HashMap<String, Integer> dict = new HashMap<>();
		for (Obstacle i : this.obstacles) {
			if (!dict.containsKey(i.getType()))
				dict.put(i.getType(), new Integer(0));
			dict.put(i.getType(), new Integer(dict.get(i.getType()).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<String, Integer> enemyStat() {
		HashMap<String, Integer> dict = new HashMap<>();
		for (Enemy i : this.enemies) {
			if (!dict.containsKey(i.getEnemyType()))
				dict.put(i.getEnemyType(), new Integer(0));
			dict.put(i.getEnemyType(), new Integer(dict.get(i.getEnemyType()).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<String, Integer> potionStat() {
		HashMap<String, Integer> dict = new HashMap<>();
		for (Potions i : this.potion_drops) {
			if (!dict.containsKey(i.getType()))
				dict.put(i.getType(), new Integer(0));
			dict.put(i.getType(), new Integer(dict.get(i.getType()).intValue() + 1));
		}
		return dict;
	}
	
	/**
	 * Getter method to return the winning goal of the maze
	 * @return the winning goal of the maze
	 */
	public int getWinCond() {
		return this.goal;
	}
	
	/**
	 * Method to keep track of player's current state according to the winning goal of the maze
	 * @return the current condition of the player, or -1 if character dies, or 0 if not satisfy goal(s)
	 */
	public int checkGoal() {
		if (this.current_cond == -1)
			return -1;
		if ((this.goal & RUNNER) > 0) {
			for (Exit ex : this.exits) {
				if (ex.getCoordinates().equals(this.player.getCoordinates())) {
					this.current_cond += RUNNER;
					return this.goal;
				}
			}
		}
		if ((this.goal & COLLECTOR) > 0) {
			if (this.loots.size() == 0)
				this.current_cond += COLLECTOR;
			else
				return 0;
		}
		if ((this.goal & SLAYER) > 0) {
			if (this.enemies.size() == 0)
				this.goal += SLAYER;
			else
				return 0;
		}
		if ((this.goal & DETECTIVE) > 0) {
			if (this.keys.size() == 0)
				this.current_cond += DETECTIVE;
			else
				return 0;
		}
		if ((this.goal & BRAINER) > 0) {
			for (FloorSwitch fs : this.switches) {
				if (!fs.triggered())
					return 0;
			}
			this.current_cond += BRAINER;
		}
		return this.current_cond;
	}
	
	/**
	 * Method to change character's co-ordinates
	 * @param x valid x co-ordinate of character (between 1 and map size - 1)
	 * @param y valid y co-ordinate of character (between 1 and map size - 1)
	 */
	public void resetCharCoOrd(int x, int y) {
		this.player.setCoordinates(x, y);
	}
	
	/**
	 * Getter method to return enemies currently alive on the map
	 * @return enemy list of the maze
	 */
	public ArrayList<Enemy> getEnemyList() {
		return this.enemies;
	}
	
	public void copyMaze(Maze copy, Maze old) {
//		private ArrayList<Weapon> weapon_drops;
		for (Weapon w : old.weapon_drops) {
			copy.weapon_drops.add(w.copy());
		}
//		private ArrayList<Arrow> available_arrows;
		for (Arrow a : old.available_arrows) {
			copy.available_arrows.add((Arrow) a.copy());
		}
//		private ArrayList<Enemy> enemies;
		for (Enemy e : old.enemies) {
			Enemy check_hound = e.copy();
			if (check_hound instanceof Hound) {
				((Hound) check_hound).linkHunter(((Hound) e).getHunterCoOrd());
			}
			copy.enemies.add(check_hound);
		}
//		private ArrayList<Obstacle> obstacles;
		for (Obstacle o : old.obstacles) {
			copy.obstacles.add(o.copy());
		}
//		private ArrayList<Potions> potion_drops;
		for (Potions p : old.potion_drops) {
			copy.potion_drops.add(p.copy());
		}
//		private ArrayList<FloorSwitch> switches;
		for (FloorSwitch s : old.switches) {
			copy.switches.add((FloorSwitch) s.copy());
		}
//		private ArrayList<Treasure> loots;
		for (Treasure t : old.loots) {
			copy.loots.add(t.copy());
		}
//		private ArrayList<Exit> exits;
		for (Exit x : old.exits) {
			copy.exits.add(x.copy());
		}
//		private ArrayList<Key> keys;
		for (Key k : old.keys) {
			Key new_copy = k.copy();
			new_copy.linkDoor(k.getDoorLinked());
			copy.keys.add(new_copy);
		}
	}
}