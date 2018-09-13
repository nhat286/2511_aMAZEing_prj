package eric;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTextField;

import niriksha.Character;
import niriksha.Potions;
import niriksha.SpecialItems;
import niriksha.Weapon;
import niriksha.ACTION;

import jae.Enemy;
import jae.Hunter;
import jae.Strategist;

public class MazeSystem extends TimerTask implements KeyListener, ActionListener {

	/**
	 * Run in terminal
	 * Go to directory bin
	 */
	private int map_size;
	private char[][] map;
	private Maze curr;
	private Timer clock;
	private Character user;
	private static char keyPressed;
	
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
		/*this.clock = new Timer("Timer");
		long delay  = 100L;
	    long period = 100L;
	    clock.scheduleAtFixedRate(this, delay, period);*/
		
		Enemy e1 = new Hunter(3, 4);
		this.curr.addEnemy(e1);
		Enemy e2 = new Strategist(7, 12);
		this.curr.addEnemy(e2);
		Enemy e3 = new Hunter(11, 17);
		this.curr.addEnemy(e3);
		this.printMap();
		char input = sc.next().charAt(0);
		while (input != 'q') {
			CoOrd in_front = this.user.getInfront();
			Object o = this.curr.getEntity(in_front);
			ACTION outcome = ACTION.NOTHING;
			switch (input) {
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
			case 'x':
				this.user.useWeapon((Weapon) o, o);
				break;
			case 'c':
				this.user.useSpecialisedItem((SpecialItems) o);
				break;
			case 'z':
				this.user.usePotion((Potions) o);
				break;
			case 'j':
				if (o instanceof SpecialItems) {
					this.user.pickUpSpecialisedItem((SpecialItems) o);
					((SpecialItems) o).setCoordinates(-2, -2);
				}
				else if (o instanceof Weapon) {
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
				return -1;
			case DESTROY:
				break;
			case GAME_COMPLETE:
				return 0;
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
		char input;
		do {
			input = sc.next().charAt(0);
			this.printMap();
			System.out.println("Design options:");
			System.out.println("\tAdd entities (a)");
			System.out.println("\tChange entities (c)");
			System.out.println("\tDelete entities (d)");
			System.out.println("\tPlay and test design (p)");
			System.out.print("> ");
			switch (input) {
			case 'a':
				System.out.println("Add entity?");
				break;
			case 'p':
				System.out.println("Test the design now? (y/n) > ");
				if (sc.next().charAt(0) == 'y') gameLoop(sc);
				break;
			case 'c':
				System.out.println("Change entity?");
				break;
			case 'd':
				System.out.println("Delete entity?");
				break;
			default:
				System.out.println("Unknown option?");
				break;
			}
		} while (input != 'q');
	}
	
	public static void main(String[] args) throws InterruptedException {
		MazeSystem sys = new MazeSystem();
		int size = 20;
		sys.start(size, 0b00001);
		
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
		
		/*JTextField textField = new JTextField();
	    textField.addKeyListener(sys);
	    JFrame jframe = new JFrame();
	    jframe.add(textField);
	    jframe.setSize(200, 150);
	    jframe.setVisible(true);*/
		
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
						clearScreen();
						System.out.println("\t\tOH NO YOU DIE! RESTARTING");
						Thread.sleep(1000);
						sys.start(size, 0b00001);
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
		case 'x':
			this.user.useWeapon((Weapon) o, o);
			break;
		case 'c':
			this.user.useSpecialisedItem((SpecialItems) o);
			break;
		case 'z':
			this.user.usePotion((Potions) o);
			break;
		case 'j':
			if (o instanceof SpecialItems) {
				this.user.pickUpSpecialisedItem((SpecialItems) o);
				((SpecialItems) o).setCoordinates(-2, -2);
			}
			else if (o instanceof Weapon) {
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
		keyPressed = '.';
//		e1.move(); e2.move(); e3.move();
		this.printMap();
		*/
	}

}
