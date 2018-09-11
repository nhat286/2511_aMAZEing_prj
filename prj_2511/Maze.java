package prj_2511;

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
	
	private char[][] map;
	private int size;
	
	public Maze(int size) {
		this.size = size;
		this.map = new char[size][size];
		drawMap();
		this.item_drops = new ArrayList<SpecialItems>();
		this.weapon_drops = new ArrayList<Weapon>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
	}
	
	public void printMap() {
		updateMap();
		//this.map = drawMap();
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
		CoOrd player = this.player.getCoordinates();
		this.map[player.getX()][player.getY()] = this.player.getIcon();
		
		CoOrd entity = null;
		for (SpecialItems i : this.item_drops) {
			entity = i.getCoordinates();
			this.map[entity.getX()][entity.getY()] = i.getIcon();
		}
		
		for (Weapon i : this.weapon_drops) {
			entity = i.getCoordinates();
			this.map[entity.getX()][entity.getY()] = i.getIcon();
		}
		
		for (Enemy i : this.enemies) {
			entity = i.getCoordinates();
			this.map[entity.getX()][entity.getY()] = i.getIcon();
		}
		
		for (Obstacle i : this.obstacles) {
			entity = i.getCoordinates();
			this.map[entity.getX()][entity.getY()] = i.getIcon();
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
