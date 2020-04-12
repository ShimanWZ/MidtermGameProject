package GameCore;

public class Player {
	public static final int MAX_SOLDIER_COUNT = 5, MAX_CAVALRY_COUNT = 3, MAX_HEADQU_COUNT = 1, MAX_CASTLE_COUNT = 2;
	private int soldierCount, cavalryCount, headquarterCount, castleCount;
	private Cavalry cavalry;
	private HeadQuarter headquarter ;
	private Castle castle;
	
	
	private Board gameBoard;
	public Player() {
		gameBoard = new Board();
		cavalry = new Cavalry();
		headquarter = new HeadQuarter();
		castle = new Castle();
	}
	
	public Board getGameBoard() {
		return gameBoard;
	}
	public void setGameBoard(int i, int j, BoardContents content) {
		this.gameBoard.getBoardArray()[i][j] = content;
	}
	
	//------------------- getters-----------------//
	public int getSoldierCount() {
		return soldierCount;
	}
	public int getCavalryCount() {
		return cavalryCount;
	}
	public int getCastleCount() {
		return castleCount;
	}
	public int getHeadquarterCount() {
		return headquarterCount;
	}
	
	//------------------ increment----------------//
	public void increaseSoldierCount() {
		this.soldierCount++ ;
	}
	public void increaseCavalryCount() {
		this.cavalryCount++ ;
	}
	public void increaseHeadquarterCount() {
		this.headquarterCount++ ;
	}
	public void increaseCastleCount() {
		this.castleCount++ ;
	}
	
	//----------------------------------------------------------------------------------------//
	
	public boolean areAllPawnsPlaced() {
		boolean b = this.getCastleCount()== MAX_CASTLE_COUNT && this.getCavalryCount() == MAX_CAVALRY_COUNT
				&& this.getHeadquarterCount() == MAX_HEADQU_COUNT && this.getSoldierCount() == MAX_SOLDIER_COUNT;
		return b;
	}
	public void incrementPawnSws() {
		if (!this.castle.isActive()) this.castle.increasePawnSw();;
		if (!this.cavalry.isActive()) this.cavalry.increasePawnSw();;
		if (!this.headquarter.isActive()) this.headquarter.increasePawnSw();
	}
	//------------------------------- some getters----------------------------//
	
	public Cavalry getCavalry() {
		return cavalry;
	}
	public HeadQuarter getHeadquarter() {
		return headquarter;
	}
	public Castle getCastle() {
		return castle;
	}
}
