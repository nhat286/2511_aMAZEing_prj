package eric;

import java.util.ArrayList;
import java.util.Scanner;

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
import kyle_maze.EnemyStat;
import kyle_maze.InventoryMenu;
import kyle_maze.ItemStat;
import kyle_maze.Rules;
import kyle_maze.SaveLoad;

public class MazeSystem {

	private int map_size;
	private char[][] map;
	private Maze curr;
	private Character user;
	private static String buffer_cmd = null;
	
	public MazeSystem() {		
		//drawMap();
	}
	
	/**
	 * Instantiate a new maze, with specific goal
	 * @param size, the maze size (map is size x size matrix)
	 * @param goal, the objective of the maze (5 different goals, can be combined)
	 * @throws IllegalArgumentException if goal <= 0 or goal > 0b11111, and size <= 2
	 */
	public void start(int size, int goal) {
		this.map_size = size;
		this.curr = new Maze(goal);
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
//		if (this.user.getActivePotion().size() > 0) {
//			System.out.print("\tCurrently activate:");
//			for (Potion p : this.user.getActivePotion()) {
//				System.out.print(" " + p.getType());
//			}
//			System.out.print("\n");
//		}
		System.out.println("Press p to pause the game and open menu!");
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Method to print out menu options when game is paused
	 * @param sc the scanner to read from stdin (terminal)
	 * @return number to signal continue checking menu, continue playing or quit maze
	 */
	public int pauseGame(Scanner sc) {
		int option = -1;
			while (option < 0) {
			clearScreen();
			System.out.println("1. Check enemy stat in current maze?");
			System.out.println("2. Check item drops in current maze?");
			System.out.println("3. Check rules of the game?");
			System.out.println("4. Save game?");
			System.out.println("5. Load game?");
			System.out.println("6. Go back to game?");
			System.out.println("7. Quit game?");
			System.out.print("> ");
			switch(sc.nextInt()) {
			case 1:
				EnemyStat es = new EnemyStat(this.curr);
				es.display();
				break;
			case 2:
				ItemStat is = new ItemStat(this.curr);
				is.display();
				break;
			case 3:
				Rules rl = new Rules(this.curr);
				rl.display();
				break;
			case 4:
				SaveLoad save = new SaveLoad(this.curr);
				save.display();
				save.SaveGame();
				break;
			case 5:
				SaveLoad load = new SaveLoad(this.curr);
				load.display();
				load.LoadGame();
				break;
			case 6:
				option = 1;
				break;
			case 7:
				option = 0;
				break;
			default:
				System.out.println("Unknown option?");
			}
		}
		return option;
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
		this.user.equipWeapon(new Sword(-2, -2));
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
		Weapon tmp = new Arrow(-2, -2, this.user);
		this.curr.addWeaponDrop(tmp);
		this.user.equipWeapon(tmp);
		
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
	
	/**
	 * Main game logic method, read from input the player's action and render the map for each action
	 * A turn-based logic, every movable entity does an action alongside the character's action
	 * Handle object checking (finding entity in front of character to determine interaction and entity's action)
	 * @param sc scanner to read from stdin (terminal)
	 * @return OUTCOME enums, which is LOSE if character dies, WIN if character satisfies goal(s),
	 * 							QUIT if player chooses to quit the maze 
	 */
	public OUTCOME gameLoop(Scanner sc) {
		this.map = drawMap();
		this.curr.updateMap(map);
	    this.printMap();
		char input = sc.next().charAt(0);
		while (input != 'q') {
			CoOrd in_front = this.user.getInfront();
			Object ahead = this.curr.getEntity(in_front);
			Object under = this.curr.getEntity(this.user.getCoordinates());
			ACTION outcome = ACTION.NOTHING;
			switch (input) {
			/**
			 * Basic movement: a is left, d is right, w is up, s is down
			 */
			case 'a':
				outcome = this.user.move('<', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
				break;
			case 's':
				outcome = this.user.move('v', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
				break;
			case 'd':
				outcome = this.user.move('>', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
				break;
			case 'w':
				outcome = this.user.move('^', this.map[in_front.getX()][in_front.getY()], ahead, this.map_size);
				break;
			/**
			 * k for using weapon, nothing happens if character hasn't equipped weapon
			 * system passes the entity in front of character to assist using weapon behaviour
			 */
			case 'k':
				this.user.useWeapon(ahead);
				break;
			/**
			 * j for picking up items at the character's co-ordinates
			 * system checks the entity at character's co-ordinates (besides character himself)
			 * 			and calls corresponding pickup behaviour
			 */
			case 'j':
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
				break;
			/**
			 * i for opening inventory menu to check what weapons and potions are available in character's bag
			 */
			case 'i':
				InventoryMenu iM = new InventoryMenu(this.user, sc);
				iM.display();
				break;
			/**
			 * p for pausing the game and opening menu options
			 */
			case 'p':
				if (this.pauseGame(sc) == 0) {
					input = 'q';
					return OUTCOME.QUIT;
				}
				break;
			default:
				break;
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
			this.map = drawMap();
			this.curr.updateMap(map);
			if (this.curr.checkGoal() == this.curr.getWinCond())
				return OUTCOME.WIN;
			else if (this.curr.checkGoal() == -1)
				return OUTCOME.LOSE;
			this.printMap();
			input = sc.next().charAt(0);
		}
		
		return OUTCOME.QUIT;
	}
	
	/**
	 * Main design logic method, asking for basic maze information (size of map, goals) and then logic for new entities
	 * @param sc scanner to read from stdin (terminal)
	 * @throws InterruptedException
	 */
	public void design(Scanner sc) throws InterruptedException {
		clearScreen();
		int size;
		System.out.print("How big is your maze? > ");
		size = sc.nextInt();
		
		int goal = 0;
		System.out.println("How to win your maze?");
		System.out.print("Find the door to win? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += Maze.RUNNER;
		System.out.print("Collect all treasures to finish the maze? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += Maze.COLLECTOR;
		System.out.print("Kill all enemies to pass? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += Maze.SLAYER;
		System.out.print("Find the key to open the door? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += Maze.DETECTIVE;
		System.out.print("Solve the puzzle to unlock the door? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += Maze.BRAINER;
		
		this.start(size, goal);
		
		char input;
		do {
			this.map = drawMap();
			this.curr.updateMap(map);
			this.printMap();
			System.out.println("Design options:");
			System.out.println("\tSet character coordinates (s)");
			System.out.println("\tAdd entities (a)");
			System.out.println("\tChange entities (c)");
			System.out.println("\tLink entities (l)");
			System.out.println("\tDelete entities (d)");
			System.out.println("\tPlay and test design (p)");
			System.out.println("\tQuit design mode (q)");
			System.out.print("> ");
			input = sc.next().charAt(0);
			switch (input) {
			case 's':
				CoOrd setCoord = designGetCoord(sc);
				if (setCoord == null)
					System.out.println("Can't set character coordinates!");
				else {
					this.curr.resetCharCoOrd(setCoord.getX(), setCoord.getY());
				}
				break;
			case 'a':
				CoOrd newCoord = designGetCoord(sc);
				if (newCoord == null)
					System.out.println("Can't add entity to maze, try again!");
				else if (!designAdd(sc, newCoord))
					System.out.println("Can't add entity to maze, try again!");
				break;
			case 'p':
				System.out.print("Test the design now? (y/n) > ");
				if (sc.next().charAt(0) == 'y') {
					MazeSystem test_design = designNewMaze(this);
					OUTCOME result = test_design.gameLoop(sc);
					if (result == OUTCOME.LOSE) {
						clearScreen();
						System.out.println("\t\tOH NO YOU DIE! RESTARTING...");
						Thread.sleep(1000);
					} else if (result == OUTCOME.QUIT) {
						test_design = null;
						break;
					} else if (result == OUTCOME.WIN) {
						System.out.println("\t\tYOU WIN! RETURNING TO DESIGN MODE...");
						Thread.sleep(1000);
						test_design = null;
						break;
					}
				}
				break;
			case 'c':
				System.out.println("Where is the entity to be changed?");
				CoOrd move = designGetCoord(sc);
				if (move == null) {
					System.out.println("Can't find entity, try again!");
					break;
				} else {
					Object o = this.curr.getEntity(move);
					if (o == null) {
						System.out.println("Can't find entity, try again!");
						break;
					}
					System.out.println("Change (l)ocation or (t)ype of entity?");
					System.out.print("> ");
					switch (sc.next().charAt(0)) {
					case 'l':
						System.out.println("Where to change to?");
						move = designGetCoord(sc);
						if (move == null) {
							System.out.println("Invalid coordinates, try again!");
						} else {
							if (o instanceof Weapon) {
								((Weapon) o).setCoordinates(move.getX(), move.getY());
							} else if (o instanceof Potion) {
								((Potion) o).setCoordinates(move.getX(), move.getY());
							} else if (o instanceof Enemy) {
								((Enemy) o).setCurrPos(move);
							} else if (o instanceof Obstacle) {
								((Obstacle) o).setCoordinates(move.getX(), move.getY());
							} else if (o instanceof Exit) {
								((Exit) o).setCoordinates(move.getX(), move.getY());
							} else if (o instanceof Key) {
								((Key) o).setCoordinates(move.getX(), move.getY());
							} else if (o instanceof Treasure) {
								((Treasure) o).setCoordinates(move.getX(), move.getY());
							}
						}
						break;
					case 't':
						if (designAdd(sc, move)) {
							if (o instanceof Weapon) {
								((Weapon) o).destroyWeapon();
							} else if (o instanceof Potion) {
								((Potion) o).destroyPotion();
							} else if (o instanceof Enemy) {
								((Enemy) o).enemyDies();
							} else if (o instanceof Obstacle) {
								((Obstacle) o).destroyObstacle();
							} else if (o instanceof Exit) {
								((Exit) o).setCoordinates(-1, -1);
							} else if (o instanceof Key) {
								((Key) o).pickUp();
							} else if (o instanceof Treasure) {
								((Treasure) o).removeTreasure();
							}
						} else {
							System.out.println("Can't change entity, unknown option!");
						}
						break;
					default:
						System.out.println("Can't change entity, unknown option!");
						break;
					}
				}
				break;
			case 'd':
				if (!designDelete(sc))
					System.out.println("Can't delete entity, not found in maze, try again!");
				break;
			case 'l':
				System.out.println("Where is the entity to be linked to?");
				CoOrd link = designGetCoord(sc);
				if (link == null) {
					System.out.println("Can't find entity, try again!");
					break;
				} else {
					Object o1 = this.curr.getEntity(link);
					if (o1 == null) {
						System.out.println("Can't find entity, try again!");
						break;
					} else if (!(o1 instanceof Key || o1 instanceof Hound)) {
						System.out.println("Can only link keys to doors, or hounds to hunters. Try again!");
						break;
					}
					System.out.println("Which entity to link to?");
					CoOrd linked = designGetCoord(sc);
					if (linked == null) {
						System.out.println("Can't find entity, try again!");
						break;
					}
					Object o2 = this.curr.getEntity(linked);
					if (o2 == null) {
						System.out.println("Can't find entity, try again!");
						break;
					} else if (o1 instanceof Key && o2 instanceof Door) {
						((Key) o1).linkDoor((Door) o2);
					} else if (o1 instanceof Hound && o2 instanceof Hunter) {
						((Hound) o1).linkHunter(((Hunter) o2).getCurrPos());
					} else {
						System.out.println("Can only link keys to doors, or hounds to hunters. Try again!");
						break;
					}
				}
				break;
			default:
				System.out.println("Unknown option? Try again!");
				break;
			}
		} while (input != 'q');
		
	}
	
	/**
	 * Method to read player's desired co-ordinates for entity
	 * @param sc scanner to read from stdin (terminal)
	 * @return a valid co-ordinates, which are between 1 and map size - 1
	 */
	public CoOrd designGetCoord(Scanner sc) {
		System.out.println("Co-ordinates?");
		System.out.print("x: ");
		int x = sc.nextInt();
		System.out.print("y: ");
		int y = sc.nextInt();
		if (x < 1 || y < 1 || x > this.map_size - 1 || y > this.map_size - 1)
			return null;
		return new CoOrd(x, y, 0);
	}
	
	/**
	 * Method to add new entity with specified co-ordinates
	 * @param sc scanner to read from stdin (terminal)
	 * @param newCoord the co-ordinates of entity being added to maze
	 * @return true if successfully add to maze, false if can't find entity type or other unexpected input
	 */
	public boolean designAdd(Scanner sc, CoOrd newCoord) {
		System.out.println("Entity?");
		System.out.println("\tEnemy (e)");
		System.out.println("\tPotion drop (p)");
		System.out.println("\tItem (i)");
		System.out.println("\tWeapon drop (w)");
		System.out.println("\tObstacle (o)");
		System.out.print("> ");
		switch(sc.next().charAt(0)) {
		case 'e':
			System.out.println("What enemy type?");
			System.out.println("\tHunter (h)");
			System.out.println("\tStrategist (s)");
			System.out.println("\tHound (d)");
			System.out.println("\tCoward (c)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 'h':
				this.curr.addEnemy(new Hunter(newCoord));
				break;
			case 's':
				this.curr.addEnemy(new Strategist(newCoord));
				break;
			case 'd':
				this.curr.addEnemy(new Hound(newCoord));
				break;
			case 'c':
				this.curr.addEnemy(new Coward(newCoord));
				break;
			default:
				return false;
			}
			break;
		case 'p':
			System.out.println("What potion type?");
			System.out.println("\tHover potion (h)");
			System.out.println("\tInvincible potion (i)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 'h':
				this.curr.addPotion(new HoverPotion(newCoord.getX(), newCoord.getY()));
				break;
			case 'i':
				this.curr.addPotion(new InvincibilityPotion(newCoord.getX(), newCoord.getY()));
				break;
			default:
				return false;
			}
			break;
		case 'w':
			System.out.println("What weapon type?");
			System.out.println("\tSword (s)");
			System.out.println("\tArrow (a)");
			System.out.println("\tBomb (b)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 's':
				this.curr.addWeaponDrop(new Sword(newCoord.getX(), newCoord.getY()));
				break;
			case 'a':
				this.curr.addWeaponDrop(new Arrow(newCoord.getX(), newCoord.getY(), this.user));
				break;
			case 'b':
				this.curr.addWeaponDrop(new Bomb(newCoord.getX(), newCoord.getY(), this.user.getCoordinates()));
			default:
				return false;
			}
			break;
		case 'o':
			System.out.println("What obstacle type?");
			System.out.println("\tBoulder (b)");
			System.out.println("\tDoor (d)");
			System.out.println("\tFloor switch (s)");
			System.out.println("\tPit (p)");
			System.out.println("\tWall (w)");
			System.out.println("\tExit (e)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 'b':
				this.curr.addObstacle(new Boulder(newCoord.getX(), newCoord.getY()));
				break;
			case 'd':
				this.curr.addObstacle(new Door(newCoord.getX(), newCoord.getY()));
				break;
			case 's':
				this.curr.addObstacle(new FloorSwitch(newCoord.getX(), newCoord.getY()));
				break;
			case 'p':
				this.curr.addObstacle(new Pit(newCoord.getX(), newCoord.getY()));
				break;
			case 'w':
				this.curr.addObstacle(new Wall(newCoord.getX(), newCoord.getY()));
				break;
			case 'e':
				this.curr.addExit(new Exit(newCoord.getX(), newCoord.getY()));
				break;
			default:
				return false;
			}
			break;
		case 'i':
			System.out.println("What item type?");
			System.out.println("\tKey (k)");
			System.out.println("\tTreasure (t)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 'k':
				this.curr.addKey(new Key(newCoord.getX(), newCoord.getY()));
				break;
			case 't':
				this.curr.addTreasure(new Treasure(newCoord.getX(), newCoord.getY()));
				break;
			default:
				return false;
			}
			break;
		default:
			return false;
		}
		
		return true;
	}
	
	/**
	 * Method to delete an entity in the maze/on the map
	 * @param sc scanner to read from stdin (terminal)
	 * @return true if entity is deleted from maze/map, false if can't find entity or other unexpected input
	 */
	public boolean designDelete(Scanner sc) {
		CoOrd del = designGetCoord(sc);
		if (del == null)
			return false;
		
		Object o = this.curr.getEntity(del);
		if (o == null)
			return false;
		
		if (o instanceof Weapon) {
			((Weapon) o).destroyWeapon();
		} else if (o instanceof Potion) {
			((Potion) o).destroyPotion();
		} else if (o instanceof Enemy) {
			((Enemy) o).enemyDies();
		} else if (o instanceof Obstacle) {
			((Obstacle) o).destroyObstacle();
		} else if (o instanceof Exit) {
			((Exit) o).setCoordinates(-1, -1);
		} else if (o instanceof Key) {
			((Key) o).pickUp();
		} else if (o instanceof Treasure) {
			((Treasure) o).removeTreasure();
		}
		return true;
	}
	
	public MazeSystem designNewMaze(MazeSystem old) {
		MazeSystem copy = new MazeSystem();
		copy.start(old.map_size, old.curr.getWinCond());
		copy.curr.resetCharCoOrd(old.user.getCoordinates().getX(), old.user.getCoordinates().getY());
		old.curr.copyMaze(copy.curr, old.curr);
		return copy;
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
			 * SPACEBAR for using weapon, nothing happens if character hasn't equipped weapon
			 * system passes the entity in front of character to assist using weapon behaviour
			 */
			} else if (input.contains("SPACE")) {
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
				InventoryMenu iM = new InventoryMenu(this.user, null);
				iM.display();
			/**
			 * ESC for pausing the game and opening menu options
			 */
			} else if (input.contains("ESCAPE")) {
				if (this.pauseGame(null) == 0) {
					return OUTCOME.QUIT;
				}
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

}
