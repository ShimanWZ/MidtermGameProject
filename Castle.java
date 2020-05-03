package GameCore;

import UI.MainGameControler;
import UI.ShopController;

public class Castle extends Pawn{
	private boolean isActive = true;
	private int castleSw = 0;
	private static int price = 70;
	
	
	
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
			
			
			for (int i = pawnI ; i <= pawnI + 1 ; i++) {
				for (int j = pawnJ ; j <= pawnJ + 1 ; j++ ) {
					if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY) {
						player.setGameBoard(i, j, BoardContents.EMPTY_ATTACKED);
					}
					else {
						getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
						player.setGameBoard(i, j, BoardContents.SUCCESS_ATTACKED);
					}
				}
			}
			
			afterAttack(MainGameControler.getTurn());
			Utils.changeTurn(MainGameControler.game, player);
		}
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		Boolean b1 = player.getGameBoard().getBoardArray()[i][j] !=  BoardContents.EMPTY_ATTACKED && 
				player.getGameBoard().getBoardArray()[i][j] !=  BoardContents.SUCCESS_ATTACKED;
		
		Boolean b2 = player.getGameBoard().getBoardArray()[i + 1][j] !=  BoardContents.EMPTY_ATTACKED &&
				player.getGameBoard().getBoardArray()[i + 1][j] !=  BoardContents.SUCCESS_ATTACKED;
		
		Boolean b3 = player.getGameBoard().getBoardArray()[i][j + 1] !=  BoardContents.EMPTY_ATTACKED &&
				player.getGameBoard().getBoardArray()[i][j + 1] !=  BoardContents.SUCCESS_ATTACKED;
		
		Boolean b4 = player.getGameBoard().getBoardArray()[i + 1][j + 1] !=  BoardContents.EMPTY_ATTACKED &&
				player.getGameBoard().getBoardArray()[i + 1][j + 1] !=  BoardContents.SUCCESS_ATTACKED;
		if (b1 && b2 && b3 && b4) return true;
		else return false;
	}
	
	//------------------------placing methods-----------------------------//
	//placing castle in initializing board
	public static void placeCastle(double x, double y, Player player) {
		Castle c = new Castle();
		c.place(x, y, player);
	}
	@Override
	protected void place(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		placeOnBoard(pawnI, pawnJ, player);
	}
	//placing castle in main game boards (temprory)
	public static void placeTempCastle(double x, double y, Player player) {
		Castle c = new Castle();
		c.tempPlace(x, y, player);
	}
	private void tempPlace(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		placeOnBoard(pawnI, pawnJ, player);
	}
	
	private void placeOnBoard(int pawnI, int pawnJ, Player player) {
		if (pawnI + 1 < Game.boardSize && pawnJ + 1 < Game.boardSize && isValidForPlacing(pawnI,pawnJ, player)) {
			player.increaseCastleCount();
			ShopController.setPlaced(true);
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
	public void setIsActive(boolean b) {
		isActive = b;
	}
	public void increasePawnSw() {
		this.castleSw++;
		if (this.castleSw == 3) {
			this.isActive = true;
			castleSw = -1;
		}
	}
	public void setCastleSw(int castleSw) {
		this.castleSw = castleSw;
	}
	public boolean isActive() {
		return this.isActive;
	}

	public int getCastleSw() {
		return castleSw;
	}
	public static int getPrice() {
		return price;
	}
	private Player getAttacker(Player p) {
		if (p == MainGameControler.game.getPlayer1()) return MainGameControler.game.getPlayer2();
		else return MainGameControler.game.getPlayer1();
	}
}
