package game.tictactoe.view;

import java.util.Scanner;

import game.tictactoe.constant.Commands;
import game.tictactoe.controller.GameManager;

public class ConsoleManager {

	private Scanner scan;
	private GameManager gameManager;
	
	public ConsoleManager(Scanner scan, GameManager gameManager) {
		this.scan = scan;
		this.gameManager = gameManager;
	}
	
	public boolean start(String instruction) {
		System.out.println("Start the Game");
		while (true) {
			
			gameManager.askForNextMove();
			String message = scan.next();
			if (message.equalsIgnoreCase(Commands.NEWGAME.name())) {
				gameManager.reset();
			} else if (message.equalsIgnoreCase(Commands.SCORE.name())) {
				gameManager.printScore();
			} else if (message.equalsIgnoreCase(Commands.MAIN.name())) {
				return false ; 
			} else if (message.equalsIgnoreCase(Commands.EXIT.name())) {
				return true;
			} else if (message.equalsIgnoreCase(Commands.HELP.name())) {
				System.out.println(instruction);
			}
			
			String[] coor = message.split(" ");
			if (coor.length == 2) inputPlayerMove(coor);	
		}
	}
	
	private void inputPlayerMove(String[] coor) {
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
