package game.tictactoe.constant;

public enum Commands {
	SCORE("score"), 
	NEWGAME("newgame"), 
	EXIT("exit"),
	MAIN("main"),
	HELP("help");

	private String command;
	
	Commands(String command) {
		this.command = command;
	}
}
