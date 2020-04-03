package GameCore;

public class Castle extends Pawn{
	boolean placed, isActive;
	int counter, MAXCounter = 3, curI, curJ;
	
	

	@Override
	public void attack(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForAttack(pawnI,pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.attacked);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.attacked);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.attacked);
			player.setGameBoard(pawnI + 1, pawnJ + 1, BoardContents.attacked);
		}
		
	}

	@Override
	public void place(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForPlacing(pawnI,pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.castle);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.castle);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.castle);
			player.setGameBoard(pawnI + 1, pawnJ + 1, BoardContents.castle);
		}
	}

	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		Boolean b1 = player.getGameBoard().getBoardArray()[i][j] !=  BoardContents.attacked;
		Boolean b2 = player.getGameBoard().getBoardArray()[i + 1][j] !=  BoardContents.attacked;
		Boolean b3 = player.getGameBoard().getBoardArray()[i][j + 1] !=  BoardContents.attacked;
		Boolean b4 = player.getGameBoard().getBoardArray()[i + 1][j + 1] !=  BoardContents.attacked;
		if (b1 && b2 && b3 && b4) return true;
		else return false;
	}

	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		Boolean b1 = player.getGameBoard().getBoardArray()[i][j] ==  BoardContents.empty;
		Boolean b2 = player.getGameBoard().getBoardArray()[i + 1][j] ==  BoardContents.empty;
		Boolean b3 = player.getGameBoard().getBoardArray()[i][j + 1] ==  BoardContents.empty;
		Boolean b4 = player.getGameBoard().getBoardArray()[i + 1][j + 1] ==  BoardContents.empty;
		if (b1 && b2 && b3 && b4) return true;
		else return false;
	}

}
