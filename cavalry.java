package GameCore;

public class cavalry extends Pawn{
	
	
	//attack methods
	@Override
	public void attack(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	public void verticalAttack(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	public void horizontalAttack(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	//placing methods
	@Override
	public void place(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	public void verticalPlace(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	public void horizontalPlace(int x, int y, Player player) {
		// TODO Auto-generated method stub
		
	}
	
	//attack validation
	@Override
	protected boolean isValidForAttack(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	protected boolean isValidForVerticalAttack(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	protected boolean isValidForHorizontalAttack(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//placing validation
	@Override
	protected boolean isValidForPlacing(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	protected boolean isValidForVerticalPlacing(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	protected boolean isValidForHorizontalPlacing(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
