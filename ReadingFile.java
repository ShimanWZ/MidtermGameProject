package fileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GameCore.BoardContents;
import GameCore.Game;
import GameCore.Player;
import UI.MainGameControler;


public class ReadingFile {
	public static void read() {
		 try {
			 File file = new File("file.txt");
		     Scanner myReader = new Scanner(file);
		     int temp;
	      
		     //getting the board size in previous game
		     temp = myReader.nextInt();
		     Game.boardSize = temp;
		     
		     
		     //getting the player whose turn is
		     temp = myReader.nextInt();
		     setTurn(temp);
		     
		     //getting the restrictions
		     attackRestrictionHandler(myReader, MainGameControler.game.getPlayer1());
		     attackRestrictionHandler(myReader, MainGameControler.game.getPlayer2());
	      
		     //getting the total money each player had
		     MainGameControler.game.getPlayer1().setWallet(myReader.nextInt());
		     MainGameControler.game.getPlayer2().setWallet(myReader.nextInt());
		     
		     //getting board contents
		     for(int i = 0 ; i < Game.boardSize ; i++) {
		    	 for (int j = 0 ; j < Game.boardSize; j++) {
		    		 temp = myReader.nextInt();
		    		 boardContentHandler(i, j, temp, MainGameControler.game.getPlayer1());
		    	  }
		      }
		     for(int i = 0 ; i < Game.boardSize ; i++) {
		    	  for (int j = 0 ; j < Game.boardSize; j++) {
		    		  temp = myReader.nextInt();
		    		  boardContentHandler(i, j, temp, MainGameControler.game.getPlayer2());
		    	  }
		      }
		     
		     myReader.close();
		 } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
	}
	
	private static void setTurn(int player) {
		if (player == 0) MainGameControler.setIsGonnaGetAtt(MainGameControler.game.getPlayer1());
		else MainGameControler.setIsGonnaGetAtt(MainGameControler.game.getPlayer2());
	}
	
	private static void boardContentHandler(int i, int j, int content, Player player) {
		BoardContents boardCon = BoardContents.EMPTY;
		if (content == 0) boardCon = BoardContents.EMPTY;
		if (content == 3) boardCon =  BoardContents.CASTLE;
		if (content == 2) boardCon = BoardContents.CAVALRY;
		if (content == 1) boardCon = BoardContents.SOLDIER;
		if (content == 4) boardCon = BoardContents.HEADQUARTER;
		if (content == 5) boardCon = BoardContents.SUCCESS_ATTACKED;
		if (content == 6) boardCon =  BoardContents.EMPTY_ATTACKED;

		
		if (player == MainGameControler.game.getPlayer1()) MainGameControler.game.getPlayer1().getGameBoard().getBoardArray()[i][j] = boardCon;
		if (player == MainGameControler.game.getPlayer2()) MainGameControler.game.getPlayer2().getGameBoard().getBoardArray()[i][j] = boardCon;
	}
	
	private static void attackRestrictionHandler(Scanner myReader, Player player) {
		
		if(myReader.nextInt() == 0) {
			player.getCastle().setIsActive(true);
		}else player.getCastle().setIsActive(false);
		player.getCastle().setCastleSw(myReader.nextInt());
		
		if(myReader.nextInt() == 0) {
			player.getCavalry().setIsActive(true);
		}else player.getCavalry().setIsActive(false);
		player.getCavalry().setCavalrySw(myReader.nextInt());
		
		if(myReader.nextInt() == 0) {
			player.getHeadquarter().setIsActive(true);
		}else player.getHeadquarter().setIsActive(false);
		player.getHeadquarter().setHeadQuarterSw(myReader.nextInt());
	}
}
