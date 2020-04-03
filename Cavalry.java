package GameCore;

public class Cavalry extends Pawn{
	boolean placed, isActive;
	int counter, MAXCounter = 2, curI, curJ;
	
	
	//attack methods
	public void verticalAttack(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForVerticalAttack(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.attacked);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.attacked);
		}
	}
	public void horizontalAttack(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForVerticalAttack(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.attacked);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.attacked);
		}
		
	}
	
	
	//placing methods
	
	public void verticalPlace(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForVerticalPlacing(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.cavalry);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.cavalry);
		}		
	}
	public void horizontalPlace(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForVerticalPlacing(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.cavalry);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.cavalry);
		}		
	}
	
	//attack validation
	
	protected boolean isValidForVerticalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.attacked 
				&&	player.getGameBoard().getBoardArray()[i + 1][j] != BoardContents.attacked) {
			return true;
		} else return false;
	}
	protected boolean isValidForHorizontalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.attacked 
				&&	player.getGameBoard().getBoardArray()[i][j + 1] != BoardContents.attacked) {
			return true;
		} else return false;
	}
	
	//placing validation
	
	protected boolean isValidForVerticalPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.empty 
				&&	player.getGameBoard().getBoardArray()[i + 1][j] == BoardContents.empty) {
			return true;
		} else return false;
	}
	protected boolean isValidForHorizontalPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.empty 
				&&	player.getGameBoard().getBoardArray()[i][j + 1] == BoardContents.empty) {
			return true;
		} else return false;
	}
	
	
	//empty methods
	public void attack(int x, int y, Player player) {}
	protected boolean isValidForAttack(int i, int j, Player player) {return false;}
	protected boolean isValidForPlacing(int i, int j, Player player) {return false;}
	public void place(int x, int y, Player player) {}
}
