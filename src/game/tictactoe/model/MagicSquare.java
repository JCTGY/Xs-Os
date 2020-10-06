package game.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MagicSquare {
	

	private static Map<Integer, Integer> sumOfMagicSquare; 
	
	private static final int[][] SIZE_3X3 = {
			{ 4, 9, 2 },
			{ 3, 5, 7 },
			{ 8, 1, 6 }
	};
	
	private static final int[][] SIZE_4X4 = {
			{ 2, 16, 13, 3 },
			{ 11, 5, 8, 10 },
			{ 7, 9, 12, 6 },
			{14, 4, 1, 15}
	};
	
	private static final int[][] SIZE_5X5 = {
			{ 1, 23, 16, 4, 21 },
			{ 15, 14, 7, 18, 11 },
			{ 24, 17, 13, 9, 2 },
			{20, 8, 19, 12, 6},
			{5, 3, 10, 22, 25}
	};
	
	private static List<int[][]> magicSquares;
	
	public MagicSquare() {
		magicSquares = new ArrayList<>(
				Arrays.asList(SIZE_3X3, SIZE_4X4, SIZE_5X5));
		sumOfMagicSquare = new HashMap<Integer, Integer>();
		sumOfMagicSquare.put(3, 15);
		sumOfMagicSquare.put(4, 34);
		sumOfMagicSquare.put(5, 65);
	}
	
	public List<Integer>getMagicArray(List<List<Integer>>coors, int size) {
		
		List<Integer>magicArray = new ArrayList<>();
		int[][]square;
		Optional<int[][]>optionalSquare = magicSquares.stream()
				.filter(s-> s.length == size)
				.findAny();
		if (!optionalSquare.isEmpty()) square = optionalSquare.get();
		else return null;
		
		for (List<Integer>coor : coors) {
			magicArray.add(square[coor.get(1)][coor.get(0)]);
		}
		
		return magicArray;
	}
	
	public int getSumOfMagicSquare(int size) {
		return sumOfMagicSquare.get(size);
	}
}
