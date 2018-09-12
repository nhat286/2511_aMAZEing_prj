package eric;

import java.util.ArrayList;
import java.util.HashMap;

import niriksha.Character;
import niriksha.SpecialItems;
import niriksha.Weapon;

public class Maze {
	
	private Character player;
	private ArrayList<SpecialItems> item_drops;
	private ArrayList<Weapon> weapon_drops;
	ArrayList<Enemy> enemies;
	ArrayList<Obstacle> obstacles;
	
	public Maze() {
		this.item_drops = new ArrayList<SpecialItems>();
		this.weapon_drops = new ArrayList<Weapon>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
	}
	
	public void updateMap(char[][] map) {
		CoOrd player = this.player.getCoordinates();
		map[player.getX()][player.getY()] = this.player.getIcon();
		
		CoOrd entity = null;
		int count = 0;
		for (SpecialItems i : this.item_drops) {
			entity = i.getCoordinates();
			if (entity.getX() < 0) this.item_drops.remove(count);
			else map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		for (Weapon i : this.weapon_drops) {
			entity = i.getCoordinates();
			if (entity.getX() < 0) this.weapon_drops.remove(count);
			else map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		for (Enemy i : this.enemies) {
			entity = i.getCoordinates();
			if (entity.getX() < 0) this.enemies.remove(count);
			else map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		for (Obstacle i : this.obstacles) {
			entity = i.getCoordinates();
			if (entity.getX() < 0) this.obstacles.remove(count);
			else map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
	}
	
	public void addCharacter(Character c) {
		this.player = c;
	}
	
	public void addItemDrop(SpecialItems i) {
		this.item_drops.add(i);
	}
	
	public void addWeaponDrop(Weapon w) {
		this.weapon_drops.add(w);
	}
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
	}
	
	public void addObstacle(Obstacle o) {
		this.obstacles.add(o);
	}
	
	public Object getEntity(CoOrd co) {
		for (SpecialItems i : this.item_drops) {
			if (i.getCoordinates().equals(co)) return i;
		}
		for (Weapon w : this.weapon_drops) {
			if (w.getCoordinates().equals(co)) return w;
		}
		for (Enemy e : this.enemies) {
			if (e.getCoordinates().equals(co)) return e;
		}
		for (Obstacle o : this.obstacles) {
			if (o.getCoordinates().equals(co)) return o;
		}
		return null;
	}
	
	public HashMap<SpecialItems, Integer> itemStat() {
		HashMap<SpecialItems, Integer> dict = new HashMap<>();
		for (SpecialItems i : this.item_drops) {
			if (!dict.containsKey(i))
				dict.put(i, new Integer(0));
			dict.put(i, new Integer(dict.get(i).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<Weapon, Integer> weaponStat() {
		HashMap<Weapon, Integer> dict = new HashMap<>();
		for (Weapon i : this.weapon_drops) {
			if (!dict.containsKey(i))
				dict.put(i, new Integer(0));
			dict.put(i, new Integer(dict.get(i).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<Obstacle, Integer> obstacleStat() {
		HashMap<Obstacle, Integer> dict = new HashMap<>();
		for (Obstacle i : this.obstacles) {
			if (!dict.containsKey(i))
				dict.put(i, new Integer(0));
			dict.put(i, new Integer(dict.get(i).intValue() + 1));
		}
		return dict;
	}
	
	public HashMap<Enemy, Integer> enemyStat() {
		HashMap<Enemy, Integer> dict = new HashMap<>();
		for (Enemy i : this.enemies) {
			if (!dict.containsKey(i))
				dict.put(i, new Integer(0));
			dict.put(i, new Integer(dict.get(i).intValue() + 1));
		}
		return dict;
	}
}
