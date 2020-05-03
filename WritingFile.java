package fileHandling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import GameCore.BoardContents;
import GameCore.Game;
import GameCore.Player;
import UI.MainGameControler;

public class WritingFile {
	
	public static void Write(){
	      try{
	          FileWriter fr = new FileWriter("file.txt");
	          BufferedWriter br = new BufferedWriter(fr);
	          PrintWriter out = new PrintWriter(br);
	          
	          // wrtiting board size
	          out.write(Game.boardSize + " ");
	          
	          // writing the turn
	          if (MainGameControler.isGonnaGetAtt() == MainGameControler.game.getPlayer1()) {
	        	  out.write(0 + " ");
	          }
	          else out.write(1 + " ");
	          
	          
	          // writing the restriction of attacks(which pawns we cant attack with)
	          writeAttackRestriction(MainGameControler.game.getPlayer1(), out);
	          writeAttackRestriction(MainGameControler.game.getPlayer2(), out);
	          
	          // writing the total money eachplayer has
	          out.write(MainGameControler.game.getPlayer1().getWallet() + " ");
	          out.write(MainGameControler.game.getPlayer2().getWallet() + " ");

	          
	          // writing the players boards
	          for (int i = 0 ; i < Game.boardSize ; i++) {
		          for (BoardContents content : MainGameControler.game.getPlayer1().getGameBoard().getBoardArray()[i]) {
		        	  writeContents(content, out);
		          }
	          }
	          
	          for (int i = 0 ; i < Game.boardSize ; i++) {
		          for (BoardContents content : MainGameControler.game.getPlayer2().getGameBoard().getBoardArray()[i]) {
		        	  writeContents(content, out);
		          }
	          }
	          
	          out.close();
	           
	      }
	      catch(IOException e){
	       System.out.println(e);   
	      }
	}
	private static void writeContents(BoardContents content, PrintWriter out) {
		if (content == BoardContents.EMPTY) out.write(0 + " ");
		if (content == BoardContents.CASTLE) out.write(3 + " ");
		if (content == BoardContents.CAVALRY) out.write(2 + " ");
		if (content == BoardContents.SOLDIER) out.write(1 + " ");
		if (content == BoardContents.HEADQUARTER) out.write(4 + " ");
		if (content == BoardContents.SUCCESS_ATTACKED) out.write(5 + " ");
		if (content == BoardContents.EMPTY_ATTACKED) out.write(6 + " ");
    }
	
	private static void writeAttackRestriction(Player player, PrintWriter out) {
		if (player.getCastle().isActive()) {
			out.write(0 + " ");
		} else out.write(1 + " ");
		out.write(player.getCastle().getCastleSw() + " ");
		
		if (player.getCavalry().isActive()) {
			out.write(0 + " ");
		} else out.write(1 + " ");
		out.write(player.getCavalry().getCavalrySw() + " ");
		
		if (player.getHeadquarter().isActive()) {
			out.write(0 + " ");
		} else out.write(1 + " ");
		out.write(player.getHeadquarter().getHeadQuarterSw() + " ");

	}
}
