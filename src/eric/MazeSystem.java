package eric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import niriksha.Character;
import niriksha.SpecialItems;
import niriksha.Weapon;

public class MazeSystem implements KeyListener, ActionListener {

	/**
	 * Run in terminal
	 * Go to directory bin
	 */
	private int map_size;
	private char[][] map;
	private Maze curr;
	//private Timer clock;
	private Character user;
	
	public MazeSystem() {		
		//drawMap();
	}
	
	public void start(int size, int goal) {
		this.map_size = size;
		this.curr = new Maze(goal);
		this.map = new char[size][size];
		this.user = new Character(1, 1);
		this.curr.addCharacter(this.user);
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
		this.map = drawMap();
		this.curr.updateMap(map);
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	/*
	 * if character dies => return -1, restart process
	 * if character wins => return 0, end game!
	 * if quit => return -2
	 */
	public int gameLoop(Scanner sc) {
		Enemy e1 = new Enemy(3, 4, 'A');
		this.curr.addEnemy(e1);
		Enemy e2 = new Enemy(7, 12, 'A');
		this.curr.addEnemy(e2);
		Enemy e3 = new Enemy(11, 17, 'A');
		this.curr.addEnemy(e3);
		this.printMap();
		char input = sc.next().charAt(0);
		while (input != 'q') {
			CoOrd in_front = this.user.getInfront();
			Object o = this.curr.getEntity(in_front);
			switch (input) {
			case 'a':
				this.user.move("left", o, this.map_size);
				break;
			case 's':
				this.user.move("down", o, this.map_size);
				break;
			case 'd':
				this.user.move("right", o, this.map_size);
				break;
			case 'w':
				this.user.move("up", o, this.map_size);
				break;
			case 'x':
				this.user.useWeapon(o);
				break;
			case 'c':
				this.user.useSpecialisedItem();
				break;
			case 'j':
				if (o instanceof SpecialItems)
					this.user.pickUpSpecialisedItem((SpecialItems) o);
				else if (o instanceof Weapon)
					this.user.pickUpWeapon((Weapon) o);
				break;
			default:
				break;
			}
			e1.move(); e2.move(); e3.move();
			this.printMap();
			input = sc.next().charAt(0);
		}
		
		return -2;
	}
	
	public void design(Scanner sc) {
		clearScreen();
		int size;
		System.out.println("How big is your maze? >");
		size = sc.nextInt();
		
		int goal = 0;
		System.out.println("How to win your maze?");
		System.out.println("Find the door to win? (y/n) >");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00001;
		System.out.println("Collect all treasures to finish the maze? (y/n) >");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00010;
		System.out.println("Kill all enemies to pass? (y/n) >");
		if (sc.next().charAt(0) == 'y')
			goal += 0b00100;
		System.out.println("Find the key to open the door? (y/n) >");
		if (sc.next().charAt(0) == 'y')
			goal += 0b01000;
		System.out.println("Solve the puzzle to unlock the door? (y/n) >");
		if (sc.next().charAt(0) == 'y')
			goal += 0b10000;
		
		this.start(size, goal);
		this.printMap();
	}
	
	public static void main(String[] args) {
		MazeSystem sys = new MazeSystem();
		int size = 20;
		sys.start(size, 0b00001);
		
		/*
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
				int result;
				do {
					result = sys.gameLoop(sc);
					if (result == -1) {
						sys.start(20, 0b00001);
					} else if (result == -2) {
						break;
					}
				} while (result != 0);
				break;
			case 'm':
				System.out.println("Design time!");
				sys.design(sc);
				break;
			case 'q':
				sc.close();
				return;
			default:
				System.out.println("Unknown option?");
			}
		}
		sc.close();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Pressed: " + arg0.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Released: " + arg0.getKeyChar());
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
