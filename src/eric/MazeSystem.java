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
	private Timer clock;
	
	public MazeSystem(int size) {
		this.map_size = size;
		this.curr = new Maze();
		this.map = new char[size][size];
		//drawMap();
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
		for (int i = 0; i < this.map_size; i++) {
			for (int j = 0; j < this.map_size; j++) {
				System.out.print(this.map[i][j]);
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {
		MazeSystem sys = new MazeSystem(20);
		
		Character user = new Character(1, 1);
		sys.curr.addCharacter(user);
		
		Enemy e1 = new Enemy(3, 4, 'A');
		sys.curr.addEnemy(e1);
		Enemy e2 = new Enemy(7, 12, 'A');
		sys.curr.addEnemy(e2);
		Enemy e3 = new Enemy(11, 17, 'A');
		sys.curr.addEnemy(e3);
		sys.printMap();
		
		/*
		 * Logic for system would be:
		 * while game not end {
		 * 		printMap();
		 * 		get info and coords from user
		 * 		get/update behaviours and stats
		 * 		updateMap();
		 * }
		 */
		Scanner sc = new Scanner(System.in);
		char input = sc.next().charAt(0);
		while (input != 'q') {
			CoOrd in_front = user.getInfront();
			Object o = sys.curr.getEntity(in_front);
			switch (input) {
			case 'a':
				user.move("left", o, sys.map_size);
				break;
			case 's':
				user.move("down", o, sys.map_size);
				break;
			case 'd':
				user.move("right", o, sys.map_size);
				break;
			case 'w':
				user.move("up", o, sys.map_size);
				break;
			case 'x':
				user.useWeapon(o);
				break;
			case 'c':
				user.useSpecialisedItem();
				break;
			case 'j':
				if (o instanceof SpecialItems)
					user.pickUpSpecialisedItem((SpecialItems) o);
				else if (o instanceof Weapon)
					user.pickUpWeapon((Weapon) o);
				break;
			default:
				break;
			}
			e1.move(); e2.move(); e3.move();
			sys.printMap();
			input = sc.next().charAt(0);
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
