package game.tictactoe.view;

import java.util.Scanner;

import game.tictactoe.controller.GameManager;
import game.tictactoe.model.*;

public class TicTacToeRunner {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in).useDelimiter("\\n");
		
		System.out.println("Enter the board size: 3 - 5");
		String boardSizeString = scan.next();
		int boardSize;
		
		try {
			boardSize = Integer.parseInt(boardSizeString);
			
		} catch (NumberFormatException e) {
			boardSize = 3;
		}
		System.out.println("Enter player1 name: ");
		String nameOne = scan.next();
		System.out.println("Enter player2 name:");
		String nameTwo = scan.next();
		nameTwo = (nameOne.equals(nameTwo)) ? "Player2" : nameTwo;
		
		GameManager gameManager = 
				new GameManager(new Board(boardSize), nameOne, nameTwo);
		
		ConsoleManager consoleManager = new ConsoleManager(scan, gameManager);
		consoleManager.start();
	}
}
