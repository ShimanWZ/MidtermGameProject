package GameCore;

import UI.MainGameControler;
import UI.ShopController;

public class Cavalry extends Pawn {
	private boolean isActive = true;
	private static boolean horizontalSw;
	private int cavalrySw = 0;
	private static int price = 50;
	
	
	
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
			if (player.getGameBoard().getBoardArray()[pawnI][pawnJ] == BoardContents.EMPTY) {
				player.setGameBoard(pawnI, pawnJ, BoardContents.EMPTY_ATTACKED);
			}
			else {
				getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
				player.setGameBoard(pawnI, pawnJ, BoardContents.SUCCESS_ATTACKED);
			}

			if (player.getGameBoard().getBoardArray()[pawnI + 1][pawnJ] == BoardContents.EMPTY) {
				player.setGameBoard(pawnI + 1, pawnJ, BoardContents.EMPTY_ATTACKED);
			}
			else {
				getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
				player.setGameBoard(pawnI + 1, pawnJ, BoardContents.SUCCESS_ATTACKED);
			}

			afterAttack(MainGameControler.getTurn());
			Utils.changeTurn(MainGameControler.game, player);
		}
	}
	protected void horizontalAttack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnJ + 1 < Game.boardSize && isValidForHorizontalAttack(pawnI, pawnJ, player)) {
			
			if (player.getGameBoard().getBoardArray()[pawnI][pawnJ] == BoardContents.EMPTY) {
				player.setGameBoard(pawnI, pawnJ, BoardContents.EMPTY_ATTACKED);
			}
			else {
				getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
				player.setGameBoard(pawnI, pawnJ, BoardContents.SUCCESS_ATTACKED);
			}
			
			
			if (player.getGameBoard().getBoardArray()[pawnI][pawnJ + 1] == BoardContents.EMPTY) {
				player.setGameBoard(pawnI, pawnJ + 1, BoardContents.EMPTY_ATTACKED);
			}
			else {
				getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
				player.setGameBoard(pawnI, pawnJ + 1, BoardContents.SUCCESS_ATTACKED);
			}
			
			
			afterAttack(MainGameControler.getTurn());
			Utils.changeTurn(MainGameControler.game, player);
		}
		
	}
	
	//-------------------------placing methods-----------------------//
	public static void placeCavalry(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		if (horizontalSw) cav.horizontalPlace(x, y, player);
		else cav.verticalPlace(x, y, player);
	}
		protected void verticalPlace(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if ( pawnI + 1 < Game.boardSize && isValidForVerticalPlacing(pawnI, pawnJ, player)) {
			player.increaseCavalryCount();
			ShopController.setPlaced(true);
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.CAVALRY);
		}		
	}
	protected void horizontalPlace(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if (pawnJ + 1 < Game.boardSize && isValidForHorizontalPlacing(pawnI, pawnJ, player)) {
			player.increaseCavalryCount();
			ShopController.setPlaced(true);
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.CAVALRY);
		}		
	}
	
	
	public static void placeTempCavalry(double x, double y, Player player) {
		Cavalry cav = new Cavalry();
		if(horizontalSw) cav.tempHorizontalPlace(x,y,player);
		else cav.tempVerticalPlace(x, y, player);
	}
	private void tempVerticalPlace(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if ( pawnI + 1 < Game.boardSize && isValidForVerticalPlacing(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI + 1, pawnJ, BoardContents.CAVALRY);
		}		
	}
	protected void tempHorizontalPlace(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (pawnJ + 1 < Game.boardSize && isValidForHorizontalPlacing(pawnI, pawnJ, player)) {
			player.setGameBoard(pawnI, pawnJ, BoardContents.CAVALRY);
			player.setGameBoard(pawnI, pawnJ + 1, BoardContents.CAVALRY);
		}		
	}
	
	//---------------------attack validation-------------------------//
	
	protected boolean isValidForVerticalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY_ATTACKED
				&& player.getGameBoard().getBoardArray()[i][j] != BoardContents.SUCCESS_ATTACKED
				&&	player.getGameBoard().getBoardArray()[i + 1][j] != BoardContents.EMPTY_ATTACKED
				&& player.getGameBoard().getBoardArray()[i + 1][j] != BoardContents.SUCCESS_ATTACKED) {
			return true;
		} else return false;
	}
	protected boolean isValidForHorizontalAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY_ATTACKED
				&& player.getGameBoard().getBoardArray()[i][j] != BoardContents.SUCCESS_ATTACKED
				&& player.getGameBoard().getBoardArray()[i][j + 1] != BoardContents.EMPTY_ATTACKED
				&& player.getGameBoard().getBoardArray()[i][j + 1] != BoardContents.SUCCESS_ATTACKED) {
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
	//------------------------attack restriction methods---------------------------//
	private void afterAttack(Player player) {
		player.incrementPawnSws();
		player.getCavalry().setIsActive(false);
		this.setCavalrySw(0);
	}
	public void setIsActive(boolean b) {
		isActive = b;
	}
	public void setCavalrySw(int cavalrySw) {
		this.cavalrySw = cavalrySw;
	}
	public void increasePawnSw() {
		this.cavalrySw++;
		if (this.cavalrySw == 2) {
			this.isActive = true;
			cavalrySw = 0;
		}
	}
	public boolean isActive() {
		return isActive;
	}
	public int getCavalrySw() {
		return cavalrySw;
	}
	//---------------------------------------------------//
	public static boolean isHorizontalSw() {
		return horizontalSw;
	}
	public static void setHorizontalSw(boolean horizontalSw) {			
		Cavalry.horizontalSw = horizontalSw;
	}
	public static int getPrice() {
		return price;
	}
	private Player getAttacker(Player p) {
		if (p == MainGameControler.game.getPlayer1()) return MainGameControler.game.getPlayer2();
		else return MainGameControler.game.getPlayer1();
	}
	//empty methods
	protected void attack(double x, double y, Player player) {}
	protected boolean isValidForAttack(int i, int j, Player player) {return false;}
	protected void place(double x, double y, Player player) {}
	protected boolean isValidForPlacing(int i, int j, Player player) {return false;}
	
}
