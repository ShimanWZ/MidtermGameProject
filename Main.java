package GameCore;

public class Main {

	public static void main(String[] args) {
		Game game = new Game();
		HeadQuarter soldier = new HeadQuarter();
		soldier.place(5, 5, game.getPlayer1());
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for( int j = 0 ; j < Game.boardSize ; j++) {
				System.out.println(game.getPlayer1().getGameBoard().getBoardArray()[i][j]);
			}
		}
	
	}

}
