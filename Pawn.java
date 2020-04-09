package GameCore;

public abstract class Pawn {
	boolean placed, isActive;
	int counter, MAXCounter, curI, curJ;
	
	abstract protected void attack(double x, double y, Player player) ;
	abstract protected void place(double x, double y, Player player) ;
	abstract protected boolean isValidForAttack(int i, int j, Player player);
	abstract protected boolean isValidForPlacing(int i, int j , Player player);

	
}
