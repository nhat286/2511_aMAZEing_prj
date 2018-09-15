package eric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTextField;

import niriksha.Character;
import niriksha.HoverPotion;
import niriksha.InvincibilityPotion;
import niriksha.Obstacle;
import niriksha.Pit;
import niriksha.Potions;
import niriksha.Sword;
import niriksha.Weapon;
import niriksha.ACTION;
import niriksha.Arrow;
import jae.Coward;
import jae.Enemy;
import jae.Hound;
import jae.Hunter;
import jae.Strategist;
import kyle_maze.EnemyStat;
import kyle_maze.InventoryMenu;
import kyle_maze.ItemStat;
import kyle_maze.Rules;
import kyle_maze.SaveLoad;

public class MazeSystem extends TimerTask implements KeyListener, ActionListener {

	/**
	 * Run in terminal
	 * Go to directory bin
	 */
	private int map_size;
	private char[][] map;
	private Maze curr;
	//private Timer clock;
	private Character user;
	private static char keyPressed;
	private int pause;
	private ScheduledExecutorService executor;
	
	public MazeSystem() {		
		//drawMap();
	}
	
	public void start(int size, int goal) {
		this.map_size = size;
		this.curr = new Maze(goal);
		this.map = new char[size][size];
		this.user = new Character(1, 1);
		this.curr.addCharacter(this.user);
		this.keyPressed = '.';
		this.pause = 0;
	}
	
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}
	
	public char[][] drawMap() {
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				if (i == 0 || i == this.map_size - 1 || j == 0 || j == this.map_size - 1)
					this.map[i][j] = '#';
				else
					this.map[i][j] = ' ';
			}
		}
		return this.map;
	}
	
	public void printMap() {
		clearScreen();
		this.map = drawMap();
		this.curr.updateMap(map);
		if (this.curr.checkGoal() != 0)
			return;
		System.out.println("To win, you have to complete the option(s) below");
		int cond = this.curr.getWinCond();
		if ((cond & 0b00001) > 0)
			System.out.println("\tFind the door to win!");
		if ((cond & 0b00010) > 0)
			System.out.println("\tCollect all treasures to finish the maze!");
		if ((cond & 0b00100) > 0)
			System.out.println("\tKill all enemies to pass!");
		if ((cond & 0b01000) > 0)
			System.out.println("\tFind the key to open the door!");
		if ((cond & 0b10000) > 0)
			System.out.println("\tSolve the puzzle to unlock the door!");
		System.out.println("Press i to check inventory!");
		if (this.user.weaponEquipped()) {
			System.out.println("\tCurrently equip " + this.user.equip_weapon.getType());
		}
		if (this.user.getActivePotion().size() > 0) {
			System.out.print("\tCurrently activate:");
			for (Potions p : this.user.getActivePotion()) {
				System.out.print(" " + p.getType());
			}
			System.out.print("\n");
		}
		System.out.println("Press p to pause the game and open menu!");
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public void setPause(int pause) {
 		this.pause = pause;
	}
	
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
				es.displayMenu();
				break;
			case 2:
				ItemStat is = new ItemStat();
				is.displayMenu();
				break;
			case 3:
				Rules rl = new Rules();
				rl.displayMenu();
				break;
			case 4:
				SaveLoad save = new SaveLoad(this.curr);
				save.displayMenu();
				save.SaveGame();
				break;
			case 5:
				SaveLoad load = new SaveLoad(this.curr);
				load.displayMenu();
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
	
	public void level1Initiate() {
		this.start(20, 0b00100);
		Enemy e1 = new Hunter(new CoOrd(3, 4));
		this.curr.addEnemy(e1);
		Enemy e2 = new Strategist(new CoOrd(7, 12));
		this.curr.addEnemy(e2);
		Enemy e3 = new Hound(new CoOrd(11, 17), e1.getCurrPos());
		this.curr.addEnemy(e3);
		Enemy e4 = new Coward(new CoOrd(16, 2));
		this.curr.addEnemy(e4);
		Obstacle o1 = new Pit(10, 10);
		this.curr.addObstacle(o1);
		Weapon w1 = new Sword(8, 1);
		this.curr.addWeaponDrop(w1);
		Weapon w2 = new Arrow(5, 1);
		this.curr.addWeaponDrop(w2);
		this.user.pickUpPotion(new HoverPotion(-2,-2));
		this.user.pickUpPotion(new HoverPotion(-2,-2));
		this.user.pickUpPotion(new InvincibilityPotion(-2,-2));
	}
	
	public OUTCOME gameLoop(Scanner sc) {
		/*executor = Executors.newSingleThreadScheduledExecutor();
		long delay  = 100L;
	    long period = 100L;
	    executor.scheduleAtFixedRate(this, delay, period, TimeUnit.MILLISECONDS);*/
		
	    this.printMap();
		char input = sc.next().charAt(0);
		while (input != 'q') {
			CoOrd in_front = this.user.getInfront();
			Object ahead = this.curr.getEntity(in_front);
			Object under = this.curr.getEntity(this.user.getCoordinates());
			ACTION outcome = ACTION.NOTHING;
			switch (input) {
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
			case 'k':
				this.user.useWeapon(ahead);
				break;
			case 'j':
				if (under instanceof Weapon) {
					this.user.pickUpWeapon((Weapon) under);
					((Weapon) under).setCoordinates(-2, -2);
					//this.curr.deleteWeaponDrop((Weapon) under);
				}
				else if (under instanceof Potions) {
					this.user.pickUpPotion((Potions) under);
					((Potions) under).setCoordinates(-2, -2);
					//this.curr.deletePotion((Potions) under);
				}
				break;
			case 'i':
				InventoryMenu iM = new InventoryMenu(this.user, sc);
				iM.displayMenu();
				break;
			case 'p':
				if (pauseGame(sc) == 0) {
					input = 'q';
					return OUTCOME.QUIT;
				}
				break;
			default:
				break;
			}
			
			switch (outcome) {
			case DIE:
				return OUTCOME.LOSE;
			case DESTROY:
				break;
			case GAME_COMPLETE:
				return OUTCOME.WIN;
			case HOVER:
				break;
			case MOVE:
				break;
			case NOTHING:
				break;
			case PICK_UP_ITEM:
				break;
			case PICK_UP_POTION:
				break;
			case PICK_UP_WEAPON:
				break;
			case PUSH_BOULDER:
				break;
			default:
				break;
			}
			this.printMap();
			if (this.curr.checkGoal() == 1)
				return OUTCOME.WIN;
			else if (this.curr.checkGoal() == -1)
				return OUTCOME.LOSE;
			input = sc.next().charAt(0);
		}
		
		return OUTCOME.QUIT;
	}
	
	public void design(Scanner sc) throws InterruptedException {
		clearScreen();
		int size;
		System.out.print("How big is your maze? > ");
		size = sc.nextInt();
		
		int goal = 0;
		System.out.println("How to win your maze?");
		System.out.print("Find the door to win? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00001;
		System.out.print("Collect all treasures to finish the maze? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00010;
		System.out.print("Kill all enemies to pass? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00100;
		System.out.print("Find the key to open the door? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += 0b01000;
		System.out.print("Solve the puzzle to unlock the door? (y/n) > ");
		if (sc.next().charAt(0) == 'y')
			goal += 0b10000;
		
		this.start(size, goal);
		this.printMap();
		
		char input;
		do {
			this.printMap();
			System.out.println("Design options:");
			System.out.println("\tSet character coordinates (s)");
			System.out.println("\tAdd entities (a)");
			System.out.println("\tChange entities (c)");
			System.out.println("\tDelete entities (d)");
			System.out.println("\tPlay and test design (p)");
			System.out.println("\tQuit design mode (q)");
			System.out.print("> ");
			input = sc.next().charAt(0);
			switch (input) {
			case 's':
				System.out.print("x: ");
				int x = sc.nextInt();
				System.out.print("y: ");
				int y = sc.nextInt();
				if (x < 1 || y < 1 || x > this.map_size - 1 || y > this.map_size - 1)
					System.out.println("Can't set character coordinates!");
				else {
					this.user.setCoordinates(x, y);
					this.curr.resetCharCoOrd(x, y);
				}
				break;
			case 'a':
				if (!designAdd(sc))
					System.out.println("Can't add entity to maze, try again!");
				break;
			case 'p':
				System.out.print("Test the design now? (y/n) > ");
				if (sc.next().charAt(0) == 'y') {
					OUTCOME result = gameLoop(sc);
					if (result == OUTCOME.LOSE) {
						clearScreen();
						System.out.println("\t\tOH NO YOU DIE! RESTARTING");
						Thread.sleep(1000);
					} else if (result == OUTCOME.QUIT) {
						this.curr.resetCharCoOrd(1, 1);
						break;
					}
				}
				break;
			case 'c':
				System.out.println("Change entity?");
				break;
			case 'd':
				if (!designDelete(sc))
					System.out.println("Can't delete entity, not found in maze, try again!");
				break;
			default:
				System.out.println("Unknown option? Try again!");
				break;
			}
		} while (input != 'q');
		
	}
	
	public boolean designAdd(Scanner sc) {
		System.out.println("Co-ordinates?");
		System.out.print("x: ");
		int x = sc.nextInt();
		System.out.print("y: ");
		int y = sc.nextInt();
		if (x < 1 || y < 1 || x > this.map_size - 1 || y > this.map_size - 1)
			return false;
		
		System.out.println("Entity?");
		System.out.println("\tEnemy (e)");
		System.out.println("\tPotion drop (p)");
		System.out.println("\tSpecial item drop (i)");
		System.out.println("\tWeapon drop (w)");
		System.out.println("\tObstacle (o)");
		System.out.print("> ");
		//char type = sc.next().charAt(0);
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
				this.curr.addEnemy(new Hunter(new CoOrd(x, y)));
				break;
			case 's':
				this.curr.addEnemy(new Strategist(new CoOrd(x, y)));
				break;
			case 'd':
				this.curr.addEnemy(new Hound(new CoOrd(x, y), null));
				break;
			case 'c':
				this.curr.addEnemy(new Coward(new CoOrd(x, y)));
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
				this.curr.addPotion(new HoverPotion(x, y));
				break;
			case 'i':
				this.curr.addPotion(new InvincibilityPotion(x, y));
				break;
			default:
				return false;
			}
			break;
		case 'w':
			System.out.println("What weapon type?");
			System.out.println("\tSword (s)");
			System.out.println("\tArrow (a)");
			System.out.print("> ");
			switch(sc.next().charAt(0)) {
			case 's':
				this.curr.addWeaponDrop(new Sword(x, y));
				break;
			case 'a':
				this.curr.addWeaponDrop(new Arrow(x, y));
				break;
			default:
				return false;
			}
			break;
		case 'o':
			break;
		default:
			return false;
		}
		
		return true;
	}
	
	public boolean designDelete(Scanner sc) {
		System.out.println("Co-ordinates?");
		System.out.print("x: ");
		int x = sc.nextInt();
		System.out.print("y: ");
		int y = sc.nextInt();
		if (x < 1 || y < 1 || x > this.map_size - 1 || y > this.map_size - 1)
			return false;
		
		Object o = this.curr.getEntity(new CoOrd(x, y));
		if (o == null)
			return false;
		
		if (o instanceof Weapon) {
			((Weapon) o).setCoordinates(-1, -1);
		} else if (o instanceof Potions) {
			((Potions) o).setCoordinates(-1, -1);
		} else if (o instanceof Enemy) {
			((Enemy) o).enemyDies();
		}
		return true;
	}
	
	public static void main(String[] args) throws InterruptedException {
		MazeSystem sys = new MazeSystem();
		
		/*
		 * Game default: MazeRunner goal + 20x20 maze + 3 enemies + character (1, 1)
		 * Logic for system would be:
		 * Play game:
		 * 		while game not end {
		 * 			get info and coords from user
		 * 			get/update behaviours and stats
		 * 			updateMap();
		 * 		}
		 * 
		 * Design map:
		 * 		get size and win conditions
		 * 		loop to add entity
		 * 		play -> do play game logic
		 */
		/*
		JTextField textField = new JTextField();
	    textField.addKeyListener(sys);
	    JFrame jframe = new JFrame();
	    jframe.add(textField);
	    jframe.setSize(200, 150);
	    jframe.setVisible(true);
		*/
		char input = '.';
		Scanner sc = new Scanner(System.in);
		while (input != 'q') {
			clearScreen();
			System.out.println("******aMAZEing******");
			System.out.println("Press y to start!");
			System.out.println("Press q to quit!");
			System.out.println("Press m to design your own maze!");
			input = sc.next().charAt(0);
			switch (input) {
			case 'y':
				OUTCOME result;
				/*if(sys.pause == 0) {
					sys.gameInitiate();
				}*/
				do {
					sys.level1Initiate();
					result = sys.gameLoop(sc);
					if (result == OUTCOME.LOSE) {
						clearScreen();
						System.out.println("\t\tOH NO YOU DIE! RESTARTING");
						Thread.sleep(1000);
					} else if (result == OUTCOME.WIN) {
						clearScreen();
						System.out.println("CONGRATULATIONS! YOU WIN!!!");
						System.out.println("Press any key to continue");
						try {
							System.in.read();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				} while (result != OUTCOME.QUIT);
				break;
			case 'm':
				System.out.println("Design time!");
				sys.design(sc);
				break;
			case 'q':
				System.out.println("Thank you for playing!");
				break;
			default:
				System.out.println("Unknown option?");
			}
		}
		sc.close();
		System.out.println("Exitting...");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Pressed: " + arg0.getKeyChar());
		keyPressed = arg0.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Released: " + arg0.getKeyChar());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		CoOrd in_front = this.user.getInfront();
		Object o = this.curr.getEntity(in_front);
		ACTION outcome = ACTION.NOTHING;
		switch (keyPressed) {
		case 'a':
			outcome = this.user.move('<', this.map[in_front.getX()][in_front.getY()], o, this.map_size);
			break;
		case 's':
			outcome = this.user.move('v', this.map[in_front.getX()][in_front.getY()], o, this.map_size);
			break;
		case 'd':
			outcome = this.user.move('>', this.map[in_front.getX()][in_front.getY()], o, this.map_size);
			break;
		case 'w':
			outcome = this.user.move('^', this.map[in_front.getX()][in_front.getY()], o, this.map_size);
			break;
		case ' ':
			this.user.useWeapon(o);
			break;
		case 'j':
			if (o instanceof Weapon) {
				this.user.pickUpWeapon((Weapon) o);
				((Weapon) o).setCoordinates(-2, -2);
			}
			else if (o instanceof Potions) {
				this.user.pickUpPotion((Potions) o);
				((Potions) o).setCoordinates(-2, -2);
			}
			break;
		default:
			break;
		}
		
		switch (outcome) {
		case DIE:
		case DESTROY:
			break;
		case GAME_COMPLETE:
		case HOVER:
			break;
		case MOVE:
			break;
		case NOTHING:
			break;
		case PICK_UP_ITEM:
			break;
		case PICK_UP_POTION:
			break;
		case PICK_UP_WEAPON:
			break;
		case PUSH_BOULDER:
			break;
		default:
			break;
		}
		this.printMap();
		keyPressed = '.';
		*/
	}

}
