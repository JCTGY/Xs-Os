package game.tictactoe.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class MagicSquare {
	

//	private static Map<Integer, Integer> sumOfMagicSquare;
	private static Map<Integer, List<List<Integer>>> magicRowsColumns;
	
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
		magicRowsColumns = new HashMap<Integer, List<List<Integer>>>();
		for (int[][] square : magicSquares) {
			magicRowsColumns.put(square.length, getMagicRowsColumns(square));
		}
	}
	
	private List<List<Integer>> getMagicRowsColumns(int[][]square) {
		
		List<List<Integer>> rowsColumns = new ArrayList<List<Integer>>();
		List<Integer> row = new ArrayList<>();
		List<Integer> col = new ArrayList<>();
		List<Integer> cross1 = new ArrayList<>();
		List<Integer> cross2 = new ArrayList<>();
		int length = square.length;
		
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				row.add(square[x][y]);
				col.add(square[y][x]);
				if (x == y) cross1.add(square[x][y]);
				if (x + y == length - 1) cross2.add(square[x][y]);
			}
			rowsColumns.add(row);
			rowsColumns.add(col);
			row = new ArrayList<>();
			col = new ArrayList<>();
		}
		rowsColumns.add(cross1);
		rowsColumns.add(cross2);
		return rowsColumns;
	}
	
	public Set<Integer>getMagicArray(List<List<Integer>> coors, int size) {
		
		Set<Integer>magicArray = new HashSet<>();
		int[][]square;
		Optional<int[][]>optionalSquare = magicSquares.stream()
				.filter(s-> s.length == size)
				.findAny();
		if (!optionalSquare.isEmpty()) square = optionalSquare.get();
		else return null;
		
		for (List<Integer>coor : coors) {
			magicArray.add(square[coor.get(0)][coor.get(1)]);
		}
		
		return magicArray;
	}
	
	public boolean hasMagicLink(List<List<Integer>>coors, int size) {
		
		List<List<Integer>> rowsColumns = magicRowsColumns.get(size);
		Set<Integer> magicArray = getMagicArray(coors, size);
		return rowsColumns.stream().anyMatch(rc -> {
			int count = 0;
			for (int i = 0; i < rc.size(); i++) {
				if (magicArray.contains(rc.get(i)))
					count++;
			}
			if (count == rc.size() && count != 0) return true;
			return false;
		});
	}
	
	
//	public int getSumOfMagicSquare(int size) {
//		return sumOfMagicSquare.get(size);
//	}
	
//	public boolean hasMagicLink(List<List<Integer>>coors, int size) {
//		
//		List<Integer> magicArray = getMagicArray(coors, size);
//		Collections.sort(magicArray);
//		int sum = getSumOfMagicSquare(size);
//		int[] nums = magicArray.stream().mapToInt(i->i).toArray();
//		ArrayList<List<Integer>> resutl = kSum(nums, sum, size, 0);
//		// Test result
//		System.out.println("result: " + resutl);
//		if (resutl.size() != 0) return true;
//		else return false;
//	}
	
//    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
//        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
//        int len = nums.length;
//        if(index >= len) {
//            return res;
//        }
//        if(k == 2) {
//        	int i = index, j = len - 1;
//        	while(i < j) {
//                //find a pair
//        	    if(target - nums[i] == nums[j]) {
//        	    	List<Integer> temp = new ArrayList<>();
//                	temp.add(nums[i]);
//                	temp.add(target-nums[i]);
//                    res.add(temp);
//                    //skip duplication
//                    while(i<j && nums[i]==nums[i+1]) i++;
//                    while(i<j && nums[j-1]==nums[j]) j--;
//                    i++;
//                    j--;
//                //move left bound
//        	    } else if (target - nums[i] > nums[j]) {
//        	        i++;
//                //move right bound
//        	    } else {
//        	        j--;
//        	    }
//        	}
//        } else{
//            for (int i = index; i < len - k + 1; i++) {
//                //use current number to reduce ksum into k-1sum
//                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
//                if(temp != null){
//                    //add previous results
//                    for (List<Integer> t : temp) {
//                        t.add(0, nums[i]);
//                    }
//                    res.addAll(temp);
//                }
//                while (i < len-1 && nums[i] == nums[i+1]) {
//                    //skip duplicated numbers
//                    i++;
//                }
//            }
//        }
//        return res;
//    }
}
