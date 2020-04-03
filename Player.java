package GameCore;

import java.util.ArrayList;

public class Player {
	private boolean isTurn;
	private Board gameBoard;
	 Soldier s1, s2, s3, s4, s5;
	ArrayList<Pawn> PawnsArray = new ArrayList<Pawn>();
	
	public Player() {
		gameBoard = new Board();
		addSoldiers();
		
	}
	
	
	public void drawBoard() {
		
	}
	public void setTurn(Boolean isTurn) {
		this.isTurn = isTurn;
	}
	public boolean isTurn() {
		return isTurn;
	}


	public Board getGameBoard() {
		return gameBoard;
	}


	public void setGameBoard(int i, int j, BoardContents content) {
		this.gameBoard.getBoardArray()[i][j] = content;
	}
	
	//initializing pawns
	private void addSoldiers() {
		PawnsArray.add(s1);
		PawnsArray.add(s2);
		PawnsArray.add(s3);
		PawnsArray.add(s4);
		PawnsArray.add(s5);
	}
}
