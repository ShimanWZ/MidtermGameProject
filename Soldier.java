package GameCore;

public class Soldier extends Pawn{
	boolean placed, isActive;
	int counter, MAXCounter = 1, curI, curJ;
	
	
	@Override
	public void attack(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForAttack(pawnI,pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.attacked);
		}
	}
	
	
	@Override
	public void place(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForPlacing(pawnI,pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.soldier);
		}
	}


	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.attacked) return true;
		else return false;
	}


	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.empty) return true;
		else return false;
	}

	


	

}
