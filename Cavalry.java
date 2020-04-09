package GameCore;

import UI.MainGameControler;

public class Cavalry extends Pawn{
	boolean placed, isActive;
	static boolean horizontalSw;
	int counter, MAXCounter = 2, curI, curJ;
	
	
	//-------------------------attack methods-------------------------------//
	 public static void cavalryVerticalAttack(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		cav.verticalAttack(x, y, player);
	}
	public static void cavalryHorizontalAttack(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		cav.horizontalAttack(x, y, player);
	}

	protected void verticalAttack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnI + 1 < Game.boardSize && isValidForVerticalAttack(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.ATTACKED);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.ATTACKED);
			Utils.changeTurn(MainGameControler.game, player);
		}
	}
	protected void horizontalAttack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnJ + 1 < Game.boardSize && isValidForHorizontalAttack(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.ATTACKED);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.ATTACKED);
			Utils.changeTurn(MainGameControler.game, player);

		}
		
	}
	
	
	
	
	//-------------------------placing methods-----------------------//
	public static void placeCavalryVertical(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		cav.verticalPlace(x, y, player);
	}
	public static void placeCavalryHorizontal(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		cav.horizontalPlace(x, y, player);
	}
	protected void verticalPlace(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if ( pawnI + 1 < Game.boardSize && isValidForVerticalPlacing(pawnI, pawnJ, player)) {
			player.increaseCavalryCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.CAVALRY);
		}		
	}
	protected void horizontalPlace(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if (pawnJ + 1 < Game.boardSize && isValidForHorizontalPlacing(pawnI, pawnJ, player)) {
			player.increaseCavalryCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.CAVALRY);
		}		
	}
	
	//---------------------attack validation-------------------------//
	
	protected boolean isValidForVerticalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.ATTACKED 
				&&	player.getGameBoard().getBoardArray()[i + 1][j] != BoardContents.ATTACKED) {
			return true;
		} else return false;
	}
	protected boolean isValidForHorizontalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.ATTACKED 
				&&	player.getGameBoard().getBoardArray()[i][j + 1] != BoardContents.ATTACKED) {
			return true;
		} else return false;
	}
	
	//--------------------placing validation------------------------//
	
	protected boolean isValidForVerticalPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY 
				&&	player.getGameBoard().getBoardArray()[i + 1][j] == BoardContents.EMPTY) {
			return true;
		} else return false;
	}
	protected boolean isValidForHorizontalPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY 
				&&	player.getGameBoard().getBoardArray()[i][j + 1] == BoardContents.EMPTY) {
			return true;
		} else return false;
	}
	
	
	
	//---------------------------------------------------//
	public static boolean isHorizontalSw() {
		return horizontalSw;
	}
	public static void setHorizontalSw(boolean horizontalSw) {
		Cavalry.horizontalSw = horizontalSw;
	}
	
	
	//empty methods
	protected void attack(double x, double y, Player player) {}
	protected boolean isValidForAttack(int i, int j, Player player) {return false;}
	protected void place(double x, double y, Player player) {}
	protected boolean isValidForPlacing(int i, int j, Player player) {return false;}
}
