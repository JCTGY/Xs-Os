package game.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

import game.tictactoe.constant.GameConstant;

public class Board {
	
	public static final int DEFAULT_BOARD_SIZE = 3;
	
	private List<List<Character>> board;
	private int size;
	
	public Board(int size) {
		this.size = size;
		board = new ArrayList<List<Character>>();
		for (int i = 0; i < size; i++) {
			List<Character> list = new ArrayList<Character>();
			for (int j = 0; j < size; j++) {
				list.add(GameConstant.empty);
			}
			board.add(list);
		}
	}
	
	public void resize(int size) {
		this.size = size;
		board = new ArrayList<List<Character>>();
		for (int i = 0; i < size; i++) {
			List<Character> list = new ArrayList<Character>();
			for (int j = 0; j < size; j++) {
				list.add(GameConstant.empty);
			}
			board.add(list);
		}
	}
	
	public void printBoard() {
	
		board.stream().forEach(row -> {
			row.forEach(c -> System.out.print(c + " "));
			System.out.println();
		});
	}
	
	public void placeMove(int x, int y, char c) {
		if (x >= size || y >= size) return ;
		board.get(x).set(y, c);
	}
	
	public Character getCharOfPosition(int x, int y) {
		return board.get(x).get(y);
	}
	
	public void clearBoard() {
		
		for (int i = 0; i < size; i++) {
			List<Character> row = board.get(i);
			for (int j = 0; j < size; j++) {
				row.set(j, GameConstant.empty);
			}
		}
	}
	
	public int numberOfEmpty() {
		return (int)board.stream()
				.flatMap(List::stream)
				.filter(c -> c == GameConstant.empty)
				.count();
	}
	
	public int getSize() { return size; }
}
