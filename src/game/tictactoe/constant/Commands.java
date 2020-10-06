package game.tictactoe.constant;

public enum Commands {
	SCORE("score"), 
	QUIT("quit"), 
	EXIT("exit"),
	HELP("help");

	private String command;
	
	Commands(String command) {
		this.command = command;
	}
}
