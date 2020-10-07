package game.tictactoe.constant;

public enum Commands {
	SCORE("score"), 
	NEWGAME("newgame"), 
	EXIT("exit"),
	SIZE("size"),
	LEADERBOARD("leaderboard"),
	HELP("help");

	private String command;
	
	Commands(String command) {
		this.command = command;
	}
}
