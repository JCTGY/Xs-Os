package game.tictactoe.model;


public class Player {

	private String name;
	private int score;
	private Character hold;
	
	public Player(String name, Character hold) {
		this.name = name;
		this.hold = hold;
		score = 0;
	}
	
	public void winGame() { score++; }
	
	public void setHold(Character hold) {
		this.hold = hold;
	}

	public Character getHold() { return hold; }
	public String getName() { return name; }
	public int getScore() { return score; }
}
