package GameCore;

public class Board {
	BoardContents[][] board ;
	
	public Board() {
		board = new BoardContents[Game.boardSize][Game.boardSize];
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for( int j = 0 ; j < Game.boardSize ; j++) {
				board[i][j] = BoardContents.empty;
			}
		}
	}
	
	public void drawRawBoard(int initX, int initY) {}
	public void drawBoardContents(int initX, int initY) {}
	public BoardContents[][] getBoardArray(){
	return board;
	}
}
