package GameCore;

import UI.MainGameControler;

public class Utils {
	
	public static int getInitSceneIndexI(double y) {
		int i;
		double curY, width = (double)Game.boardWidth / Game.boardSize,initY = 100;
		
		for (curY = initY, i = 0 ; curY <= initY + Game.boardWidth ; curY += width , i++ ) {
			if (curY < y && y < curY + width) return i;
		}
		return 0;
	}
	public static int getInitSceneIndexJ(double x) {
		int j;
		double curX, width = (double)Game.boardWidth / Game.boardSize, initX = 100;

		
		for (curX = initX, j = 0 ; curX <= initX + Game.boardWidth ; curX += width, j++) {
			if (curX < x && x < curX + width) return j;
		}
		return 0;
	}
	public static int getIndexI(double y) {
		int i;
		double curY, width = (double)Game.boardWidth / Game.boardSize, initY = 75;
	
		//drawing horizontal lines
		for (curY = initY , i = 0; curY <= initY + Game.boardWidth ; curY += width , i++) {
			if (curY < y && y < curY + width) return i;
		}
		return 0;
	}
	public static int getIndexJ(double x) {
		int i;
		double curX, width = (double)Game.boardWidth / Game.boardSize, initX = 25;
		if (x < 25 + Game.boardWidth) {
			for (curX = initX , i = 0; curX <= initX + Game.boardWidth ; curX += width, i++) {					
				if (curX < x && x < curX + width) return i;	
			}
		}
		else if (775 < x ) {
			initX = 775;
			for (curX = initX , i = 0; curX <= initX + Game.boardWidth ; curX += width, i++) {					
				if (curX < x && x < curX + width) return i;	
			}
		}
		return 0;
	}
	
	public static void changeTurn(Game game, Player turn) {
		if (turn == game.getPlayer1()) MainGameControler.setIsGonnaAtt(game.getPlayer2());
		else MainGameControler.setIsGonnaAtt(game.getPlayer1());
	}
}
