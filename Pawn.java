package GameCore;

public abstract class Pawn {
	boolean placed, isActive;
	int counter, MAXCounter, curI, curJ;
	
	abstract public void attack(int x, int y, Player player) ;
	abstract public void place(int x, int y, Player player) ;
	abstract protected boolean isValidForAttack(int i, int j, Player player);
	abstract protected boolean isValidForPlacing(int i, int j , Player player);


	
}
