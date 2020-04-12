package GameCore;

import UI.MainGameControler;

public class HeadQuarter extends Pawn {
	private boolean isActive = true;
	private int headQuarterSw = 0;
	
	//------------------attack methods------------------//
	public static void headQuarterAttack(double x, double y, Player player) {
		HeadQuarter head = new HeadQuarter();
		head.attack(x, y, player);
	}
	@Override
	protected void attack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnI + 2 < Game.boardSize && pawnJ + 2 < Game.boardSize && isValidForAttack(pawnI,pawnJ, player)) {
			for (int c1 = pawnI ; c1 < pawnI + 3 ; c1++) {
				for (int c2 = pawnJ ; c2 < pawnJ + 3 ; c2++) {
					player.setGameBoard(c1, c2, BoardContents.ATTACKED);
				}
			}
			afterAttack(MainGameControler.getTurn());
			Utils.changeTurn(MainGameControler.game, player);
		}		
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		for (int c1 = i ; c1 < i + 3 ; c1++) {
			for (int c2 = j ; c2 < j + 3 ; c2++) {
				if (player.getGameBoard().getBoardArray()[c1][c2] == BoardContents.ATTACKED) return false;
			}
		}
		return true;
	}
	//-----------------------placing methods----------------------//
	public static void placeHeadQuarter(double x, double y, Player player) {
		HeadQuarter head = new HeadQuarter();
		head.place(x, y, player);
	}
	@Override
	protected void place(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if (pawnI + 2 < Game.boardSize && pawnJ + 2 < Game.boardSize && isValidForPlacing(pawnI,pawnJ, player)) {
			player.increaseHeadquarterCount();
			for (int c1 = pawnI ; c1 < pawnI + 3 ; c1++) {
				for (int c2 = pawnJ ; c2 < pawnJ + 3 ; c2++) {
					player.setGameBoard(c1, c2, BoardContents.HEADQUARTER);
				}
			}
		}		
	}
	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		for (int c1 = i ; c1 < i + 3 ; c1++) {
			for (int c2 = j ; c2 < j + 3 ; c2++) {
				if (player.getGameBoard().getBoardArray()[c1][c2] != BoardContents.EMPTY) return false;
			}
		}
		return true;
	}
	//------------------------attack restriction methods---------------------------//
	
	private void afterAttack(Player player) {
		player.incrementPawnSws();
		player.getHeadquarter().setIsActive(false);
		this.setHeadQuarterSw(0);
	}
	private void setIsActive(boolean b) {
		isActive = b;
	}
	public void increasePawnSw() {
		this.headQuarterSw++;
		if (this.headQuarterSw == 4) {
			this.isActive = true;
			headQuarterSw = 0;
		}
	}
	public void setHeadQuarterSw(int headQuarterSw) {
		this.headQuarterSw = headQuarterSw;
	}
	public boolean isActive() {
		return this.isActive;
	}
}
