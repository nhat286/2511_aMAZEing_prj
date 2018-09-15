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
	 * Win Condition:
	 * 00001 -> MazeRunner (just find the door)
	 * 00010 -> TreasureHunter (collect all treasures)
	 * 00100 -> EnemySlayer (kill all enemies)
	 * 01000 -> Detective (find the key to unlock door)
	 * 10000 -> Brainer (solve puzzle to unlock door)
	 */
	public static final int RUNNER    = 0b00001;
	public static final int COLLECTOR = 0b00010;
	public static final int SLAYER    = 0b00100;
	public static final int DETECTIVE = 0b01000;
	public static final int BRAINER   = 0b10000;
	
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
	
	public void updateCharacterBag() {
		Iterator<Weapon> wp = this.player.getBag().getWeaponList().iterator();
		while (wp.hasNext()) {
			Weapon w = wp.next();
			if (w.getCoordinates().getX() == -1) {
				wp.remove();
				if (this.player.weaponEquipped() && w == this.player.equip_weapon)
					this.player.removeEquipped();
				w.destroyWeapon(w);
			}
		}
		
		Iterator<Potions> pt = this.player.getBag().getPotionList().iterator();
		ArrayList<Potions> active = this.player.getActivePotion();
		while (pt.hasNext()) {
			Potions p = pt.next();
			if (p.getCoordinates().getX() == -1) {
				pt.remove();
				active.remove(p);
				p.destroyPotion(p);
			}
		}
	}
	
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
			if (entity.getX() < 0) k_iter.remove();
			else map[entity.getX()][entity.getY()] = k.getIcon();
		}
		
		entity = null;
		Iterator<Exit> ex_iter = this.exits.iterator();
		while (ex_iter.hasNext()) {
			Exit ex = ex_iter.next();
			entity = ex.getCoordinates();
			map[entity.getX()][entity.getY()] = ex.getIcon();
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
	
	public void addCharacter(Character c) {
		this.player = c;
	}
	
	public void addWeaponDrop(Weapon w) {
		this.weapon_drops.add(w);
		if (w instanceof Arrow)
			this.available_arrows.add((Arrow) w);
	}
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
	}
	
	public void addObstacle(Obstacle o) {
		this.obstacles.add(o);
		if (o instanceof FloorSwitch)
			this.switches.add((FloorSwitch) o);
	}
	
	public void addPotion(Potions p) {
		this.potion_drops.add(p);
	}
	
	public void addTreasure(Treasure t) {
		this.loots.add(t);
	}
	
	public void addExit(Exit e) {
		this.exits.add(e);
	}
	
	public void addKey(Key k) {
		this.keys.add(k);
	}
	
	public void deleteWeaponDrop(Weapon w) {
		this.weapon_drops.remove(w);
	}
	
	public void deleteEnemy(Enemy e) {
		this.enemies.remove(e);
	}
	
	public void deleteObstacle(Obstacle o) {
		this.obstacles.remove(o);
	}
	
	public void deletePotion(Potions p) {
		this.potion_drops.remove(p);
	}
	
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
	
	public int getWinCond() {
		return this.goal;
	}
	
	public int checkGoal() {
		if (this.current_cond == -1)
			return -1;
		if ((this.goal & RUNNER) > 0) {
			for (Exit ex : this.exits) {
				if (ex.getCoordinates().equals(this.player.getCoordinates())) {
					this.current_cond += RUNNER;
					break;
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
	
	public void resetCharCoOrd(int x, int y) {
		this.player.setCoordinates(x, y);
	}
	
	public ArrayList<Enemy> getEnemyList() {
		return this.enemies;
	}
}
