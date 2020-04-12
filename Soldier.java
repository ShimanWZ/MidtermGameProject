package GameCore;

import UI.MainGameControler;

public class Soldier extends Pawn{

	
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
			player.setGameBoard(pawnI, pawnJ, BoardContents.ATTACKED);
			Utils.changeTurn(MainGameControler.game, player);
			MainGameControler.getTurn().incrementPawnSws();
		}
	}
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.ATTACKED) return true;
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
			player.increaseSoldierCount();
			player.setGameBoard(pawnI, pawnJ, BoardContents.SOLDIER);
		}
	}
	protected boolean isValidForPlacing(int i, int j, Player player) {
		if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY) return true;
		else return false;
	}	

}
