package eric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import niriksha.Character;
import niriksha.Potions;
import niriksha.Weapon;
import niriksha.Obstacle;
import niriksha.Pit;
import jae.Enemy;

public class Maze {
	
	private Character player;
	private ArrayList<Weapon> weapon_drops;
	private ArrayList<Enemy> enemies;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Pit> pits;
	private ArrayList<Potions> potion_drops;
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
	
	public Maze(int winning_goal) {
		this.weapon_drops = new ArrayList<Weapon>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
		this.pits = new ArrayList<Pit>();
		this.potion_drops = new ArrayList<Potions>();
		this.goal = winning_goal;
		this.current_cond = 0;
	}
	
	public void updateCharacterBag() {
		/*ArrayList<Weapon> wp = this.player.getBag().getWeaponList();
		for (Weapon w : wp) {
			if (w.getCoordinates().getX() == -1) {
				this.player.getBag().deleteWeapon(w);
				if (this.player.weaponEquipped() && w == this.player.equip_weapon)
					this.player.removeEquipped();
				w.destroyWeapon(w);
			}
		}*/
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
		
		/*ArrayList<Potions> pt = this.player.getBag().getPotionList();
		for (Potions p : pt) {
			if (p.getCoordinates().getX() == -1) {
				this.player.getBag().deletePotion(p);
				p.destroyPotion(p);
			}
		}*/
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
			if (entity.getX() < 0) wp_iter.remove();
			else map[entity.getX()][entity.getY()] = w.getIcon();
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
				for (Pit p : this.pits) {
					if (e.getCurrPos().equals(p.getCoordinates())) {
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
		
		map[player.getX()][player.getY()] = this.player.getIcon();
	}
	
	public void addCharacter(Character c) {
		this.player = c;
	}
	
	public void addWeaponDrop(Weapon w) {
		this.weapon_drops.add(w);
	}
	
	public void addEnemy(Enemy e) {
		this.enemies.add(e);
	}
	
	public void addObstacle(Obstacle o) {
		this.obstacles.add(o);
		if (o instanceof Pit) {
			this.pits.add((Pit) o);
		}
	}
	
	public void addPotion(Potions p) {
		this.potion_drops.add(p);
	}
	
	public void deleteWeaponDrop(Weapon w) {
		this.weapon_drops.remove(w);
	}
	
	public void deleteEnemy(Enemy e) {
		this.enemies.remove(e);
	}
	
	public void deleteObstacle(Obstacle o) {
		this.obstacles.remove(o);
		if (o instanceof Pit) {
			this.pits.remove((Pit) o);
		}
	}
	
	public void deletePotion(Potions p) {
		this.potion_drops.remove(p);
	}
	
	public Object getEntity(CoOrd co) {
		for (Weapon w : this.weapon_drops) {
			if (w.getCoordinates().equals(co)) return w;
		}
		for (Potions p : this.potion_drops) {
			if (p.getCoordinates().equals(co)) return p;
		}
		for (Enemy e : this.enemies) {
			if (e.getCurrPos().equals(co)) return e;
		}
		for (Obstacle o : this.obstacles) {
			if (o.getCoordinates().equals(co)) return o;
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
		if ((this.goal & 0b00001) > 0) {
			
		}
		if ((this.goal & 0b00010) > 0) {
			
		}
		if ((this.goal & 0b00100) > 0) {
			if (this.enemies.size() == 0)
				goal = 1;
			else
				return 0;
		}
		if ((this.goal & 0b01000) > 0) {
			
		}
		if ((this.goal & 0b10000) > 0) {
			
		}
		return goal;
	}
	
	public void resetCharCoOrd(int x, int y) {
		this.player.setCoordinates(x, y);
	}
	
	public ArrayList<Enemy> getEnemyList() {
		return this.enemies;
	}
}