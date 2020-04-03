package GameCore;

public class Game {
	private boolean running, sound;
	private Player player1, player2;
	static int boardSize = 30;
	int boardWidth;
	
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
}
