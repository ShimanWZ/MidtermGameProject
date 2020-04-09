package GameCore;

public class Game {
	private boolean running;
	private static boolean sound;
	public Player player1, player2;
	private static BoardContents content = null;
	public static int boardSize = 15, boardWidth = 700 ;
	
	public Game() {
		running = true;
		sound = true;
		player1 = new Player();
		player2 = new Player();
	}

	public Player getPlayer1() {
		return player1;
	}
	public Player getPlayer2() {
		return player2;
	}
	public static void setSound(boolean b) {
		sound = b;
	}
	public static boolean getSound() {
		return sound;
	}
	public static void setBoardSize(int size) {
		boardSize = size;
	}

	public static BoardContents getContent() {
		return content;
	}

	public static void setContent(BoardContents content) {
		Game.content = content;
	}	
}
