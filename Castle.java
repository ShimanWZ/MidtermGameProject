package GameCore;

import UI.MainGameControler;

public class Castle extends Pawn{
	private boolean isActive = true;
	private int castleSw = 0;
	
	
	//------------------------attack methods-----------------------//
	public static void castleAttack(double x, double y, Player player) {
		Castle c = new Castle();
		c.attack(x, y, player);
	}
	@Override
	protected void attack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnI + 1 < Game.boardSize && pawnJ + 1 < Game.boardSize && isValidForAttack(pawnI,pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.ATTACKED);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.ATTACKED);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.ATTACKED);
			player.setGameBoard(pawnI + 1, pawnJ + 1, BoardContents.ATTACKED);
			afterAttack(MainGameControler.getTurn());
			Utils.changeTurn(MainGameControler.game, player);
		}
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		Boolean b1 = player.getGameBoard().getBoardArray()[i][j] !=  BoardContents.ATTACKED;
		Boolean b2 = player.getGameBoard().getBoardArray()[i + 1][j] !=  BoardContents.ATTACKED;
		Boolean b3 = player.getGameBoard().getBoardArray()[i][j + 1] !=  BoardContents.ATTACKED;
		Boolean b4 = player.getGameBoard().getBoardArray()[i + 1][j + 1] !=  BoardContents.ATTACKED;
		if (b1 && b2 && b3 && b4) return true;
		else return false;
	}
	//------------------------placing methods-----------------------------//
	public static void placeCastle(double x, double y, Player player) {
		Castle c = new Castle();
		c.place(x, y, player);
	}
	@Override
	protected void place(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if (pawnI + 1 < Game.boardSize && pawnJ + 1 < Game.boardSize && isValidForPlacing(pawnI,pawnJ, player)) {
			player.increaseCastleCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.CASTLE);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.CASTLE);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.CASTLE);
			player.setGameBoard(pawnI + 1, pawnJ + 1, BoardContents.CASTLE);
		}
	}

	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		Boolean b1 = player.getGameBoard().getBoardArray()[i][j] ==  BoardContents.EMPTY;
		Boolean b2 = player.getGameBoard().getBoardArray()[i + 1][j] ==  BoardContents.EMPTY;
		Boolean b3 = player.getGameBoard().getBoardArray()[i][j + 1] ==  BoardContents.EMPTY;
		Boolean b4 = player.getGameBoard().getBoardArray()[i + 1][j + 1] ==  BoardContents.EMPTY;
		if (b1 && b2 && b3 && b4) return true;
		else return false;
	}
	//----------------------------attack restriction methods---------------------------//
	private void afterAttack(Player player) {
		player.incrementPawnSws();
		player.getCastle().setIsActive(false);
		this.setCastleSw(0);
	}
	private void setIsActive(boolean b) {
		isActive = b;
	}
	public void increasePawnSw() {
		this.castleSw++;
		if (this.castleSw == 3) {
			this.isActive = true;
			castleSw = 0;
		}
	}
	public void setCastleSw(int castleSw) {
		this.castleSw = castleSw;
	}
	public boolean isActive() {
		return this.isActive;
	}
}
