package eric;

import java.util.ArrayList;

import niriksha.Character;
import niriksha.Door;
import niriksha.FloorSwitch;
import niriksha.HoverPotion;
import niriksha.Key;
import niriksha.NormalCharacter;
import niriksha.Obstacle;
import niriksha.Pit;
import niriksha.Potion;
import niriksha.STATE;
import niriksha.InvincibilityPotion;
import niriksha.Sword;
import niriksha.Treasure;
import niriksha.Wall;
import niriksha.Weapon;
import niriksha.ACTION;
import niriksha.Arrow;
import niriksha.Boulder;
import jae.Coward;
import jae.Enemy;
import jae.Hound;
import jae.Hunter;
import jae.Strategist;
import javafx.scene.canvas.GraphicsContext;

public class PlaySystem {

	private int map_size;
	private char[][] map;
	private Maze curr;
	private Character user;
	private static String buffer_cmd = null;
	
	public PlaySystem() {		
		//drawMap();
	}
	
	public Character getUser() {
		return this.user;
	}
	
	/**
	 * Instantiate a new maze, with specific goal
	 * @param size, the maze size (map is size x size matrix)
	 * @param goal, the objective of the maze (5 different goals, can be combined)
	 * @throws IllegalArgumentException if goal <= 0 or goal > 0b11111, and size <= 2
	 */
	public void start(int size, int goal) {
		this.map_size = size;
		this.curr = new Maze(size, goal);
		this.map = new char[size][size];
		this.user = new Character(1, 1);
		this.user.setState((STATE) new NormalCharacter(this.user));
		this.curr.addCharacter(this.user);
		for (int i = 0; i < size; i++) {
			this.curr.addObstacle(new Wall(0, i));
			this.curr.addObstacle(new Wall(size - 1, i));
		}
		for (int i = 1; i < size - 1; i++) {
			this.curr.addObstacle(new Wall(i, 0));
			this.curr.addObstacle(new Wall(i, size - 1));
		}
	}
	
	/**
	 * Method to clear out terminal screen for easier view
	 */
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	
	/**
	 * Method to create new empty map
	 * @return size x size matrix filled with ' '
	 */
	public char[][] drawMap() {
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				this.map[i][j] = ' ';
			}
		}
		return this.map;
	}
	
	/**
	 * Method to always print out goal of the maze and the map, with some information
	 * regarding character's current equip/in-used items
	 */
	public void printMap() {
		clearScreen();
		System.out.println("To win, you have to complete the option(s) below");
		int cond = this.curr.getWinCond();
		if ((cond & Maze.RUNNER) > 0)
			System.out.println("\tFind the exit to win!");
		if ((cond & Maze.COLLECTOR) > 0)
			System.out.println("\tCollect all treasures to finish the maze!");
		if ((cond & Maze.SLAYER) > 0)
			System.out.println("\tKill all enemies to pass!");
		if ((cond & Maze.DETECTIVE) > 0)
			System.out.println("\tFind the key to open the door(s)!");
		if ((cond & Maze.BRAINER) > 0)
			System.out.println("\tSolve the puzzle to complete the maze!");
		System.out.println("Press i to check inventory!");
		if (this.user.weaponEquipped()) {
			System.out.println("\tCurrently equip " + this.user.getEquippedWeapon().getType());
		}
		System.out.println("Press p to pause the game and open menu!");
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Method to create a maze with goal to destroy all enemies to win
	 * Contains all types of enemy, with arrow and sword as weapon drops on the map, and a pit
	 * Character's bag already contains HoverPotion and InvincibilityPotion
	 */
	public void level1Initiate() {
		this.start(20, Maze.SLAYER);
		
		//Enemy e1 = new Hunter(new CoOrd(3, 4));
		Enemy e1 = new Hunter(new CoOrd(3, 4, 50));
		this.curr.addEnemy(e1);
		//Enemy e2 = new Strategist(new CoOrd(7, 12));
		Enemy e2 = new Strategist(new CoOrd(7, 12, 50));
		this.curr.addEnemy(e2);
		//Enemy e3 = new Hound(new CoOrd(11, 17));
		Enemy e3 = new Hound(new CoOrd(11, 17, 50));
		((Hound) e3).linkHunter(e1.getCurrPos());
		this.curr.addEnemy(e3);
		//Enemy e4 = new Coward(new CoOrd(16, 2));
		Enemy e4 = new Coward(new CoOrd(16, 2, 50));
		this.curr.addEnemy(e4);
		Obstacle o1 = new Pit(10, 10);
		this.curr.addObstacle(o1);
		Weapon w1 = new Sword(8, 1);
		this.curr.addWeaponDrop(w1);
		Weapon w2 = new Arrow(5, 1, this.user);
		this.curr.addWeaponDrop(w2);
		this.user.pickUpPotion(new HoverPotion(-2,-2));
		this.user.pickUpPotion(new HoverPotion(-2,-2));
		this.user.pickUpPotion(new InvincibilityPotion(-2,-2));
		
		// random weapon equipped to character
//		this.user.equipWeapon(new Sword(-2, -2));
//		Weapon w3 = new Arrow(-2, -2, this.user);
//		this.curr.addWeaponDrop(w3);
//		this.user.equipWeapon(w3);
	}
	
	/**
	 * A method to create a map with goal to activate all floor switches to win
	 * Contains boulders and switches to interact, pits, and arrow, bomb
	 */
	public void level2Initiate() {
		this.start(17, Maze.BRAINER);
		
		Obstacle o1 = new Pit(10, 10);
		this.curr.addObstacle(o1);
		Obstacle o2 = new Pit(13, 4);
		this.curr.addObstacle(o2);
		Obstacle o3 = new Pit(3, 8);
		this.curr.addObstacle(o3);
		Potion p1 = new HoverPotion(8, 14);
		this.curr.addPotion(p1);
		Potion p2 = new InvincibilityPotion(4, 5);
		this.curr.addPotion(p2);
		Obstacle o4 = new FloorSwitch(3, 2);
		this.curr.addObstacle(o4);
		Obstacle o5 = new FloorSwitch(9, 11);
		this.curr.addObstacle(o5);
		Obstacle o6 = new Boulder(3, 5);
		this.curr.addObstacle(o6);
		Obstacle o7 = new Boulder(15, 6);
		this.curr.addObstacle(o7);
		Obstacle o8 = new Boulder(7, 13);
		this.curr.addObstacle(o8);
		Weapon ar = new Arrow(6, 4, this.user);
		this.curr.addWeaponDrop(ar);
		Weapon bm = new Bomb(12, 9, this.user.getCoordinates());
		this.curr.addWeaponDrop(bm);
		
		// random weapon equipped to character
//		Weapon tmp = new Bomb(-2, -2, this.user.getCoordinates());
//		Weapon tmp = new Arrow(-2, -2, this.user);
//		this.curr.addWeaponDrop(tmp);
//		this.user.equipWeapon(tmp);
		
		// randomly use potion since the start
//		this.user.equipPotion(new HoverPotion(-2, -2));
//		this.user.equipPotion(new InvincibilityPotion(-2, -2));
	}
	
	/**
	 * A method to create a map with goal to collect treasures and find the exit to win
	 * The maze can also be completed by only finding the exit without passing other goals
	 * Contains doors, a key, and an exit
	 */
	public void level3Initiate() {
		this.start(20, Maze.RUNNER + Maze.COLLECTOR + Maze.DETECTIVE);
		
		Door d1 = new Door(5, 8);
		this.curr.addObstacle(d1);
		Exit e = new Exit(18, 18);
		this.curr.addExit(e);
		for (int i = 14; i < this.map_size - 1; i++) {
			if (i == 17) continue;
			this.curr.addObstacle(new Wall(16, i));
		}
		Door d2 = new Door(16, 17);
		this.curr.addObstacle(d2);
		this.curr.addObstacle(new Wall(17, 14));
		this.curr.addObstacle(new Wall(18, 14));
		Key k1 = new Key(4, 7);
		k1.linkDoor(d2);
		this.curr.addKey(k1);
		this.curr.addTreasure(new Treasure(11, 7));
		this.curr.addTreasure(new Treasure(8, 15));
	}
	
	public Maze getMaze() {
		return this.curr;
	}
	
	public void setMaze(Maze m) {
		this.curr = m;
		this.map_size = m.getSize();
		this.user = m.getUser();
		this.map = new char[this.map_size][this.map_size];
		this.curr.updateImage();
	}
	
	public int getMapSize() {
		return this.map_size;
	}
	
	/*
	 * Integrate old gameLoop with graphics and javafx
	 */
	public OUTCOME gameLoop(ArrayList<String> input, GraphicsContext gc, double refreshTime) {
		if (input.size() <= 0)
			buffer_cmd = null;
		this.map = drawMap();
		this.curr.updateMap(map, gc);
		gc.clearRect(0, 0, map_size*32,map_size*32);
		this.user.getSprite().setVelocity(0,0);
		
		CoOrd in_front = this.user.getInfront();
		Object ahead = this.curr.getEntity(in_front);
		Object under = this.curr.getEntity(this.user.getCoordinates());
		ACTION outcome = ACTION.NOTHING;
		/**
		 * Basic movement with arrow keys
		 */
		if (input.size() > 0 && !input.get(0).equals(buffer_cmd)) {
			buffer_cmd = input.get(0);
			if (input.contains("LEFT")) {
				outcome = this.user.move('<', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
			} else if (input.contains("DOWN")) {
				outcome = this.user.move('v', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
			} else if (input.contains("RIGHT")) { 
				outcome = this.user.move('>', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
			} else if (input.contains("UP")) { 
				outcome = this.user.move('^', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
			/**
			 * K for using weapon, nothing happens if character hasn't equipped weapon
			 * system passes the entity in front of character to assist using weapon behaviour
			 */
			} else if (input.contains("K")) {
				this.user.useWeapon(ahead);
			/**
			 * CTRL for picking up items at the character's co-ordinates
			 * system checks the entity at character's co-ordinates (besides character himself)
			 * 			and calls corresponding pickup behaviour
			 */
			} else if (input.contains("CONTROL")) {
				if (under instanceof Weapon) {
					this.user.pickUpWeapon((Weapon) under);
				} else if (under instanceof Potion) {
					this.user.pickUpPotion((Potion) under);
				} else if (under instanceof Treasure) {
					((Treasure) under).pickUp();
				} else if (under instanceof Key) {
					this.user.setHoldingKey((Key) under);
					((Key) under).pickUp();
				}
			/**
			 * I for opening inventory menu to check what weapons and potions are available in character's bag
			 */
			/*
			 * Need to pass the input string in, instead of scanner here!
			 */
			} else if (input.contains("I")) {
				// do we need this?
			/**
			 * ESC for pausing the game and opening menu options
			 */
			//} else if (input.contains("ESCAPE")) {
				//return OUTCOME.PAUSE;
			} else if (input.contains("Q")) {
				return OUTCOME.QUIT;
			}
		}
		
		/**
		 * Check for outcome from character's action (move) and handle logic of the game
		 * If character tries to move onto a boulder entity, push boulder behaviour is checked and called
		 */
		switch (outcome) {
		case DIE:
			return OUTCOME.LOSE;
		case DESTROY:
			break;
		case GAME_COMPLETE:
			return OUTCOME.WIN;
		case MOVE:
			break;
		case NOTHING:
			break;
		case PUSH_BOULDER:
			Boulder b = (Boulder) ahead;
			CoOrd next_to = b.getInfront(this.user.getIcon());
			if (b.push_boulder(this.user.getIcon(), this.map[next_to.getX()][next_to.getY()],
					this.curr.getEntity(next_to), this.map_size) != ACTION.NOTHING) {
				this.user.move(this.user.getIcon(), ' ', null, this.map_size);
			}
			break;
		default:
			break;
		}
		/**
		 * After each character's action, render the map to update all entities
		 */
		this.user.getSprite().update(refreshTime);
		this.map = drawMap();
		this.curr.updateMap(map, gc);
		//this.printMap();
		if (this.curr.checkGoal() == this.curr.getWinCond())
			return OUTCOME.WIN;
		else if (this.curr.checkGoal() == -1)
			return OUTCOME.LOSE;
		
		
		return OUTCOME.NOTHING;
	}
	
	public char[][] getMap() {
		return map;
	}
	
	public void initializeDesign(ArrayList<String> mapString){
		System.out.println(mapString.toString());
		for (String str : mapString) {
			String[] substr = str.split(" ");
			switch(substr[0]) {
			case "Arrow":
				this.curr.addWeaponDrop(new Arrow(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),this.getUser()));
				break;
			case "Bomb":
				this.curr.addWeaponDrop(new Bomb(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),this.user.getCoordinates()));
				break;
			case "Sword":
				this.curr.addWeaponDrop(new Sword(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "HoverPotion":
				this.curr.addPotion(new HoverPotion(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "InvincibilityPotion":
				this.curr.addPotion(new InvincibilityPotion(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "Coward":
				this.curr.addEnemy(new Coward(new CoOrd(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),50)));
				break;
			case "Hunter":
				this.curr.addEnemy(new Hunter(new CoOrd(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),50)));
				break;
			case "Strategist":
				this.curr.addEnemy(new Strategist(new CoOrd(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),50)));
				break;
			case "Hound":
				this.curr.addEnemy(new Hound(new CoOrd(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]),50)));
				break;
			case "Boulder":
				this.curr.addObstacle(new Boulder(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break; 
			case "Pit":
				this.curr.addObstacle(new Pit(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "FloorSwitch":
				this.curr.addObstacle(new FloorSwitch(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "Wall":
				this.curr.addObstacle(new Wall(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "Exit":
				this.curr.addExit(new Exit(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "CloseDoor":
				this.curr.addObstacle(new Door(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "OpenDoor":
				Door d = new Door(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2]));
				d.openDoor();
				this.curr.addObstacle(d);
				break;
			case "Key":
				this.curr.addKey(new Key(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			case "Treasure":
				this.curr.addTreasure(new Treasure(Integer.parseInt(substr[1]),
						Integer.parseInt(substr[2])));
				break;
			default:
				break;
			}
		}
	}

}
