package game.tictactoe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game.tictactoe.constant.GameConstant;
import game.tictactoe.model.Board;
import game.tictactoe.model.MagicSquare;
import game.tictactoe.model.Player;

public class GameManager {

	private Board board;
	private Player playerOne;
	private Player playerTwo;
	private Player currentPlayer;
	private MagicSquare magicSquare;
	
	public GameManager(Board board, String nameOne, String nameTwo) {
		this.board = board;
		playerOne = new Player(nameOne, GameConstant.circle);
		playerTwo = new Player(nameTwo, GameConstant.cross);
		currentPlayer = playerOne;
		magicSquare = new MagicSquare();
	}
	
	public boolean checkForWin() {
		System.out.println("number of empty: " + board.numberOfEmpty());
		return false;
	}
	
	// TODO: add display score
	public void printScore() {
		
	}
	
	public void askForNextMove() {
		
		System.out.println(currentPlayer.getName() + 
				" enter move from [1-" + board.getSize() +
				"]" + "[1-" + board.getSize() + "]");
	}
	
	public boolean move(int x, int y) {
		if (!checkInputCoor(x, y)) return false;
		if (board.getCharOfPosition(x, y) != GameConstant.empty) {
			return false;
		}
		int size = board.getSize();
		
		board.placeMove(x, y, currentPlayer.getHold());
		board.printBoard();
		if (board.numberOfEmpty() < (size * size - size))
			checkForResult();
		currentPlayer = (currentPlayer.equals(playerOne)) 
				? playerTwo : playerOne;
		return true;
	}
	
	public boolean checkInputCoor(int x, int y) {
		int size = board.getSize();
		if (x >= size || x < 0 || y >= size || y < 0) {
			return false;
		}
		return true;
	}
	
	public boolean checkForResult() {
		List<List<Integer>> coors = new ArrayList<List<Integer>>();
		int size = board.getSize();
		int sum = magicSquare.getSumOfMagicSquare(size);
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (board.getCharOfPosition(x, y) == currentPlayer.getHold()) {
					coors.add(new ArrayList<>(Arrays.asList(x, y)));
				}
			}
		}
		
		List<Integer> magicArray = magicSquare.getMagicArray(coors, size);
		System.out.println("coors: " + coors);
		System.out.println("magicArray: " + magicArray);
		if (magicArray.isEmpty()) return false;
		final int arraySize = magicArray.size();
		for (int i = 0; i < arraySize - 2; i++) {
			for (int j = i + 1; j < arraySize - 1; j++) {
				for (int k = j + 1; k < arraySize; k++) {
					if (magicArray.get(i) + magicArray.get(j) + magicArray.get(k) == sum) {
						currentPlayerWin();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	// helper function
	
	private void currentPlayerWin() {
		
		System.out.println(currentPlayer.getName() + " WIN!");
	}
}
