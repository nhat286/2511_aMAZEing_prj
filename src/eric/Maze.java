package eric;

import java.util.ArrayList;
import java.util.HashMap;

import niriksha.Character;
import niriksha.Potions;
import niriksha.SpecialItems;
import niriksha.Weapon;
import niriksha.Obstacle;

import jae.Enemy;

public class Maze {
	
	private Character player;
	private ArrayList<SpecialItems> item_drops;
	private ArrayList<Weapon> weapon_drops;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Potions> potion_drops;
	private int goal;
	/*
	 * Win Condition:
	 * 00001 -> MazeRunner (just find the door)
	 * 00010 -> TreasureHunter (collect all treasures)
	 * 00100 -> EnemySlayer (kill all enemies)
	 * 01000 -> Detective (find the key to unlock door)
	 * 10000 -> Brainer (solve puzzle to unlock door)
	 */
	
	public Maze(int winning_goal) {
		this.item_drops = new ArrayList<SpecialItems>();
		this.weapon_drops = new ArrayList<Weapon>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
		this.potion_drops = new ArrayList<Potions>();
		this.goal = winning_goal;
	}
	
	public void updateMap(char[][] map) {
		CoOrd entity = null;
		int count = 0;
		for (SpecialItems i : this.item_drops) {
			entity = i.getCoordinates();
			if (entity.getX() == -1) this.item_drops.remove(count);
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		entity = null;
		for (Potions i : this.potion_drops) {
			entity = i.getCoordinates();
			if (entity.getX() == -1) this.potion_drops.remove(count);
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		entity = null;
		for (Weapon i : this.weapon_drops) {
			entity = i.getCoordinates();
			if (entity.getX() == -1) this.weapon_drops.remove(count);
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		count = 0;
		entity = null;
		for (Enemy i : this.enemies) {
			entity = i.getCurrPos();
			if (entity.getX() == -1) this.enemies.remove(count);
			else if (entity.getX() >= 0) {
				i.enemyMovement(this.player, map.length);
				map[entity.getX()][entity.getY()] = i.getIcon();
			}
			count++;
		}
		
		count = 0;
		entity = null;
		for (Obstacle i : this.obstacles) {
			entity = i.getCoordinates();
			if (entity.getX() == -1) this.obstacles.remove(count);
			else if (entity.getX() >= 0) map[entity.getX()][entity.getY()] = i.getIcon();
			count++;
		}
		
		CoOrd player = this.player.getCoordinates();
		map[player.getX()][player.getY()] = this.player.getIcon();
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
	
	public void addPotion(Potions p) {
		this.potion_drops.add(p);
	}
	
	public Object getEntity(CoOrd co) {
		for (SpecialItems i : this.item_drops) {
			if (i.getCoordinates().equals(co)) return i;
		}
		for (Weapon w : this.weapon_drops) {
			if (w.getCoordinates().equals(co)) return w;
		}
		for (Enemy e : this.enemies) {
			if (e.getCurrPos().equals(co)) return e;
		}
		for (Obstacle o : this.obstacles) {
			if (o.getCoordinates().equals(co)) return o;
		}
		for (Potions p : this.potion_drops) {
			if (p.getCoordinates().equals(co)) return p;
		}
		return null;
	}
	
	public HashMap<String, Integer> itemStat() {
		HashMap<String, Integer> dict = new HashMap<>();
		for (SpecialItems i : this.item_drops) {
			if (!dict.containsKey(i.getType()))
				dict.put(i.getType(), new Integer(0));
			dict.put(i.getType(), new Integer(dict.get(i.getType()).intValue() + 1));
		}
		return dict;
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
	
	public void resetCharCoOrd() {
		this.player.setCoordinates(1, 1);
	}
}
