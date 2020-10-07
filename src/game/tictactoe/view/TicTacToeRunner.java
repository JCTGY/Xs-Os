package game.tictactoe.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import game.tictactoe.controller.ConsoleManager;
import game.tictactoe.model.*;

public class TicTacToeRunner {

	public static final String HEADER_FILEPATH = "resources/header";
	public static final String INSTRUTION_FILEPATH = "resources/instruction";
	public static final String LEADDERBOARD_FILEPATH = "resources/leaderboard";
	
	public static void main(String[] args) {
		
		Leaderboard leaderboard = readLeaderboardFromFile();
		Scanner scan = new Scanner(System.in).useDelimiter("\\n");
		displayHeader();
		String instruction = getInstruction();
		System.out.println(instruction);

		int boardSize = getBoardSize(scan);
		System.out.println("Enter player1 name: ");
		String nameOne = changeFirstLetterUpper(scan.next());
		System.out.println("Enter player2 name: ");
		String nameTwo = changeFirstLetterUpper(scan.next());
		nameOne = (nameOne.equals("")) ? "Player1" : nameOne;
		nameTwo = (nameTwo.equals("")) ? "Player2" : nameTwo;
		
		GameManager gameManager = 
				new GameManager(new Board(boardSize), nameOne, 
						nameTwo, leaderboard);
		ConsoleManager consoleManager = new ConsoleManager(scan, gameManager);
		while (true) {
			if (consoleManager.start(instruction)) break ;
			gameManager.changeBoardSize(getBoardSize(scan));
		}
		writeLeaderBoardToFile(leaderboard);
	}
	
	public static Leaderboard readLeaderboardFromFile() {
		
		Leaderboard leaderboard = null;
		File leaderboardFile = new File(LEADDERBOARD_FILEPATH);
		try ( FileInputStream fis = new FileInputStream(leaderboardFile);
				ObjectInputStream ois = new ObjectInputStream(fis)){
			leaderboard = (Leaderboard)ois.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found");
		} catch (IOException e) {
			System.out.println("File cannot read");
		} catch (ClassNotFoundException e) {
			System.out.println("class not fount");
		}
		
		return (leaderboard == null) ? new Leaderboard() : leaderboard;
	}
	
	public static void writeLeaderBoardToFile(Leaderboard leaderboard) {
		
		File file = new File(LEADDERBOARD_FILEPATH);
		
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("File cannot be created");
			}
		
		try (FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
			) {
			oos.writeObject(leaderboard);
		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found");
		} catch (IOException e) {
			System.out.println("File cannot written to");
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
	
	public static String changeFirstLetterUpper(String s) {
		if (s == null || s.equals("")) return "";
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
