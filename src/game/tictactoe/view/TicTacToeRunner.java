package game.tictactoe.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import game.tictactoe.controller.GameManager;
import game.tictactoe.model.*;

public class TicTacToeRunner {

	public static final String HEADER_FILEPATH = "resources/header";
	public static final String INSTRUTION_FILEPATH = "resources/instruction";
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in).useDelimiter("\\n");
		displayHeader();
		String instruction = getInstruction();
		System.out.println(instruction);

		int boardSize = getBoardSize(scan);
		System.out.println("Enter player1 name: ");
		String nameOne = scan.next();
		System.out.println("Enter player2 name: ");
		String nameTwo = scan.next();
		nameTwo = (nameOne.equals(nameTwo)) ? "Player2" : nameTwo;
		
		GameManager gameManager = 
				new GameManager(new Board(boardSize), nameOne, nameTwo);
		
		ConsoleManager consoleManager = new ConsoleManager(scan, gameManager);
		while (true) {
			if (consoleManager.start(instruction)) break ;
			gameManager.changeBoardSize(getBoardSize(scan));
		}
	}
	
	public static void displayHeader() {
		try {
			File file = new File(HEADER_FILEPATH);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);

			String str = new String(data, "UTF-8");
			System.out.println(str);
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open the header file");
		} catch (IOException e) {
			System.out.println("Cannot read the header file");
		}
	}
	
	public static String getInstruction() {
		try {
			File file = new File(INSTRUTION_FILEPATH);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);

			String str = new String(data, "UTF-8");
			fis.close();
			return str;
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open the header file");
		} catch (IOException e) {
			System.out.println("Cannot read the header file");
		}
		return "Opps, cannot open the instruction file";
	}
	
	private static int getBoardSize(Scanner scan) {
		
		System.out.println("Enter the board size: 3 - 5");
		String boardSizeString = scan.next();
		int boardSize;
		
		try {
			boardSize = Integer.parseInt(boardSizeString);
			if (boardSize > 5 || boardSize < 3) {
				boardSize = Board.DEFAULT_BOARD_SIZE;
			}
			
		} catch (NumberFormatException e) {
			boardSize = Board.DEFAULT_BOARD_SIZE;
		}
		return boardSize;
	}
	
}
