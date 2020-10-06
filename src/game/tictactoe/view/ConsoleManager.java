package game.tictactoe.view;

import java.util.Scanner;

import game.tictactoe.controller.GameManager;

public class ConsoleManager {

	private Scanner scan;
	private GameManager gameManager;
	
	public ConsoleManager(Scanner scan, GameManager gameManager) {
		this.scan = scan;
		this.gameManager = gameManager;
	}
	
	public void start() {
		System.out.println("Start the Game");
		while (true) {
			
			gameManager.askForNextMove();
			String message = scan.next();
			if (message.equalsIgnoreCase("exit")) return ;
			String[] coor = message.split(" ");
			if (coor.length == 2) {
				try {
					int x = Integer.parseInt(coor[0]);
					int y = Integer.parseInt(coor[1]);
					if (!gameManager.move(--x, --y)) {
						System.out.println("Invalid board position, "
								+ "must be within an empty position");
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid board position");
				}
			}			
		}
	}
	
}
