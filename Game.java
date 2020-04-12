package GameCore;

public class Game {;
	private static boolean sound;
	private Player player1, player2;
	private static BoardContents content = null;
	public static int boardSize = 15, boardWidth = 700 ;
	
	public Game() {
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
	public Player endOfGame() {
		if (isDead(this.player1)) return this.player2;
		else if (isDead(this.player2)) return this.player1;
		return null;
	}
	private boolean isDead(Player player) {
		for (int i = 0 ; i < boardSize ; i++) {
			for (int j = 0 ; j < boardSize ; j++) {
				if ( player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY 
					&& player.getGameBoard().getBoardArray()[i][j] != BoardContents.ATTACKED) return false;
			}
		}
		return true;
	}
}
