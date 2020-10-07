package game.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game.tictactoe.constant.GameConstant;

public class GameManager {

	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	private MagicSquare magicSquare;
	private Leaderboard leaderboard;
	
	public GameManager(Board board, String nameOne, 
			String nameTwo, Leaderboard leaderboard) {
		this.board = board;
		playerOne = new Player(nameOne, GameConstant.circle);
		playerTwo = new Player(nameTwo, GameConstant.cross);
		currentPlayer = playerOne;
		magicSquare = new MagicSquare();
		this.leaderboard = leaderboard;
	}
	
	public Leaderboard getLeaderboard() {
		return leaderboard;
	}

	// Display the current score
	public void printScore() {
		System.out.println("Current Score Board");
		System.out.println(playerOne.getName() + " Score: " + playerOne.getScore());
		System.out.println(playerTwo.getName() + " Score: " + playerTwo.getScore());
		System.out.println("===========================================\n");
	}
	
	// Display text on Console asking user to input the move
	public void askForNextMove() {
		
		board.printBoard();
		System.out.println(currentPlayer.getName() + 
				"'s turn: [" + currentPlayer.getHold() + "] enter move from [1-" + board.getSize() +
				"]" + "[1-" + board.getSize() + "]\n");
	}
	
	// Input position for the move, and make the move
	public boolean move(int x, int y) {
		if (!checkInputCoor(x, y)) return false;
		if (board.getCharOfPosition(x, y) != GameConstant.empty) {
			return false;
		}
		int size = board.getSize();
		
		board.placeMove(x, y, currentPlayer.getHold());
		if (board.numberOfEmpty() < (size * size - size))
			if (checkForResult()) reset();
		currentPlayer = (currentPlayer.equals(playerOne)) 
				? playerTwo : playerOne;
		return true;
	}
	
	// Reset to a new Game
	public void reset() {
		printScore();
		System.out.println("============== New Game Start =============");
		board.clearBoard();
	}
	
	public void changeBoardSize(int size) {
		
		if (size != board.getSize()) {
			board.resize(size);
		}
	}
	
	// Check if someone win using the magic board
	private boolean checkForResult() {
		List<List<Integer>> coors = new ArrayList<List<Integer>>();
		int size = board.getSize();
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board.getCharOfPosition(x, y) == currentPlayer.getHold()) {
					coors.add(new ArrayList<>(Arrays.asList(x, y)));
				}
			}
		}
		
		if (magicSquare.hasMagicLink(coors, size)) {
			currentPlayerWin();
			return true;
		} else if (board.numberOfEmpty() == 0) {
			System.out.println("Game Tie");
			return true;
		}
		return false;
	}
	
	// helper function
	
	// Check if it is a valid input coor
	private boolean checkInputCoor(int x, int y) {
		int size = board.getSize();
		if (x >= size || x < 0 || y >= size || y < 0) {
			return false;
		}
		return true;
	}
	
	// display text for the player win
	private void currentPlayerWin() {
		
		System.out.println(currentPlayer.getName() + " WIN!");
		currentPlayer.winGame();
		leaderboard.addScore(currentPlayer.getName());
	}
}
