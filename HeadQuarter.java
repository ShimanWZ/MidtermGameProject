package GameCore;

public class HeadQuarter extends Pawn {
	boolean placed, isActive;
	int counter, MAXCounter = 4, curI, curJ;
	
	
	@Override
	public void attack(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForAttack(pawnI,pawnJ, player)) {
			for (int c1 = pawnI ; c1 < pawnI + 3 ; c1++) {
				for (int c2 = pawnJ ; c2 < pawnJ + 3 ; c2++) {
					player.setGameBoard(c1, c2, BoardContents.attacked);
				}
			}
		}		
	}
	@Override
	public void place(int x, int y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForPlacing(pawnI,pawnJ, player)) {
			curI = pawnI;
			curJ = pawnJ;
			for (int c1 = pawnI ; c1 < pawnI + 3 ; c1++) {
				for (int c2 = pawnJ ; c2 < pawnJ + 3 ; c2++) {
					player.setGameBoard(c1, c2, BoardContents.headquarter);
				}
			}
		}		
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		for (int c1 = i ; c1 < i + 3 ; c1++) {
			for (int c2 = j ; c2 < j + 3 ; c2++) {
				if (player.getGameBoard().getBoardArray()[c1][c2] == BoardContents.attacked) return false;
			}
		}
		return true;
	}
	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		for (int c1 = i ; c1 < i + 3 ; c1++) {
			for (int c2 = j ; c2 < j + 3 ; c2++) {
				if (player.getGameBoard().getBoardArray()[c1][c2] != BoardContents.empty) return false;
			}
		}
		return true;
	}
	
	
}
