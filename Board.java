package GameCore;

public class Board {
	private BoardContents[][] board ;
	
	public Board() {
		board = new BoardContents[Game.boardSize][Game.boardSize];
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for( int j = 0 ; j < Game.boardSize ; j++) {
				board[i][j] = BoardContents.EMPTY;
			}
		}
	}
	
	public BoardContents[][] getBoardArray(){
		return board;
	}
	
	public String toString() {
		String s = new String();
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for( int j = 0 ; j < Game.boardSize ; j++) {
				s += board[i][j] + " ";
			}
			s += "\n";
		}
		return s;
	}
}
