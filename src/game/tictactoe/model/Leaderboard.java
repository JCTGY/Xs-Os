package game.tictactoe.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Leaderboard implements Serializable {

	/**
	 * Version number
	 * marker interface
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Integer> leaderScore;
	
	public Leaderboard() {

		leaderScore = new TreeMap<>();
	}
	
	public void addScore(String name) {
		if (leaderScore.containsKey(name)) {
			leaderScore.put(name, leaderScore.get(name) + 1);
		} else {
			leaderScore.put(name, 1);
		}
	}
	
	public void printLeaderBoard() {
		
		Map<String,Integer> topTen =
			    leaderScore.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(10)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, 
			          (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println("=====================================");
		System.out.println("=====        Leaderboard        =====");
		System.out.println("=====================================");
		System.out.println("|name                          score|");
		System.out.println("-------------------------------------");
		
		topTen.forEach((k, v) -> {
			int len = 37 - (4 + k.length() + v.toString().length());
			System.out.println("| " + k + padString(len, v.toString()));
		});
		System.out.println("=====================================\n\n");
	}
	
	private String padString(int len, String v) {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(" ");
		}
		sb.append(v);
		sb.append(" |");
		return sb.toString();
	}
}
