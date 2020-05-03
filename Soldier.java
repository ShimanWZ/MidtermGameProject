package GameCore;

import UI.MainGameControler;
import UI.ShopController;

public class Soldier extends Pawn {
	
	private static int price = 30;
	
	
	//-------------------attack methods------------//
	
	public static void soldierAttack(double x, double y, Player player) {
		Soldier soldier = new Soldier();
		soldier.attack(x,y,player);
	}
	@Override
	protected void attack(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);
		
		if (isValidForAttack(pawnI,pawnJ, player)) {
			if (player.getGameBoard().getBoardArray()[pawnI][pawnJ] == BoardContents.EMPTY) {
				player.setGameBoard(pawnI, pawnJ, BoardContents.EMPTY_ATTACKED);
			}
			else {
				// increasing total money
				getAttacker(player).setWallet(getAttacker(player).getWallet() + 10);
				player.setGameBoard(pawnI, pawnJ, BoardContents.SUCCESS_ATTACKED);
			}
			MainGameControler.getTurn().incrementPawnSws();
			Utils.changeTurn(MainGameControler.game, player);
		}
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.SUCCESS_ATTACKED
				&& player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY_ATTACKED) return true;
		else return false;
	}
	
	
	
	//-------------------------placing methods----------------------//
	public static void placeSoldier(double x, double y, Player player) {
		Soldier soldier = new Soldier();
		soldier.place(x, y, player);
	}
	protected void place(double x, double y, Player player) {
		int pawnI = Utils.getInitSceneIndexI(y);
		int pawnJ = Utils.getInitSceneIndexJ(x);
		
		if (isValidForPlacing(pawnI,pawnJ, player)) {
			ShopController.setPlaced(true);
			player.increaseSoldierCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.SOLDIER);
		}
	}
	
	
	public static void placeTempSoldier(double x, double y, Player player) {
		Soldier soldier = new Soldier();
		soldier.tempPlace(x, y, player);
	}
	protected void tempPlace(double x, double y, Player player) {
		int pawnI = Utils.getIndexI(y);
		int pawnJ = Utils.getIndexJ(x);

		if (isValidForPlacing(pawnI,pawnJ, player)) {
			player.increaseSoldierCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.SOLDIER);
		}
	}
	
	protected boolean isValidForPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY) return true;
		else return false;
	}
	
	//------------------------------------------------------------//
	public static int getPrice() {
		return price;
	}
	private Player getAttacker(Player p) {
		if (p == MainGameControler.game.getPlayer1()) return MainGameControler.game.getPlayer2();
		else return MainGameControler.game.getPlayer1();
	}
}
