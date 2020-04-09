package UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import GameCore.BoardContents;
import GameCore.Castle;
import GameCore.Cavalry;
import GameCore.Game;
import GameCore.HeadQuarter;
import GameCore.Player;
import GameCore.Soldier;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MainGameControler {
	public AnchorPane pane;
	public static Game game ;
	private static Player isGonnaGetAttacked;
	public void initialize() {
		game = InitializeControler.getGame();
		isGonnaGetAttacked = game.player1;
		drawP1RawBoard();
		drawP2RawBoard();
	}
	
	
	//-----------------making game ready to attack----------------------//
	public void setCavalry() {
		Game.setContent(BoardContents.CAVALRY);
		Cavalry.setHorizontalSw(false);
	}
	public void setCavalry2() {
		Game.setContent(BoardContents.CAVALRY);
		Cavalry.setHorizontalSw(true);
	}
	public void setSoldier() {
		Game.setContent(BoardContents.SOLDIER);
	}
	public void setCastle() {
		Game.setContent(BoardContents.CASTLE);
	}
	public void setHeadQuarter() {
		Game.setContent(BoardContents.HEADQUARTER);
	}
	
	//----------------------drawing methods--------------------------//
	
	private void drawP1RawBoard() {
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize, initX = 25 , initY = 25;
		
		//drawing vertical lines
		for (curX = initX ; curX <= initX + Game.boardWidth ; curX += width) {
			Line l1 = new Line(curX, initY, curX, initY +  Game.boardWidth);
			l1.setStroke(Color.WHITE);
			l1.setFill(Color.WHITE);
			pane.getChildren().add(l1);
		}
		//drawing horizontal lines
		for (curY = initY ; curY <= initY + Game.boardWidth ; curY += width ) {
		Line l1 = new Line(initX , curY ,initX +  Game.boardWidth,curY);
		l1.setStroke(Color.WHITE);
		l1.setFill(Color.WHITE);
		pane.getChildren().add(l1);
		}
	}
	private void drawP2RawBoard() {
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize, initX = 775 , initY = 25;
		
		//drawing vertical lines
		for (curX = initX ; curX <= initX + Game.boardWidth + 1 ; curX += width) {
			Line l1 = new Line(curX, initY, curX, initY +  Game.boardWidth);
			l1.setStroke(Color.WHITE);
			l1.setFill(Color.WHITE);
			pane.getChildren().add(l1);
		}
		//drawing horizontal lines
		for (curY = initY ; curY <= initY + Game.boardWidth  ; curY += width ) {
		Line l1 = new Line(initX , curY ,initX +  Game.boardWidth,curY);
		l1.setStroke(Color.WHITE);
		l1.setFill(Color.WHITE);
		pane.getChildren().add(l1);
		}
	}
	private void drawXs() throws FileNotFoundException {
		drawP1Xs();
		drawP2Xs();
	}
	private void drawP1Xs() throws FileNotFoundException {
		
		double width = (double)Game.boardWidth / Game.boardSize, initX = 25 , initY = 25;	
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (game.player1.getGameBoard().getBoardArray()[i][j] == BoardContents.ATTACKED) {
					
					 Image image = new Image(new FileInputStream("D:\\midproject\\pics\\final pics\\O.png"));  
				     ImageView imageView = new ImageView(image); 
				      
				     double curX = initX + ( j * width);
				     double curY = initY + (i* width);
				     
				     imageView.setX(curX); 
				     imageView.setY(curY); 
				     imageView.setFitHeight(width); 
				     imageView.setFitWidth(width); 
				      
				     pane.getChildren().add(imageView);	
				}
			}
		}	
	}
	private void drawP2Xs() throws FileNotFoundException {
		double width = (double)Game.boardWidth / Game.boardSize, initX = 775 , initY = 25;	
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (game.player2.getGameBoard().getBoardArray()[i][j] == BoardContents.ATTACKED) {
					
					 Image image = new Image(new FileInputStream("D:\\midproject\\pics\\final pics\\O.png"));  
				     ImageView imageView = new ImageView(image); 
				      
				     double curX = initX + ( j * width);
				     double curY = initY + (i* width);
				     
				     imageView.setX(curX); 
				     imageView.setY(curY); 
				     imageView.setFitHeight(width); 
				     imageView.setFitWidth(width); 
				      
				     pane.getChildren().add(imageView);	
				}
			}
		}		
	}
	//-------------------------------------- attack methods ------------------------------------------//
	public void attack(MouseEvent event) throws IOException {
		boolean xBorder = (25 < event.getSceneX() && event.getSceneX() < 25 + Game.boardWidth)
				|| (775 < event.getSceneX() && event.getSceneX() < 775 + Game.boardWidth) ;
		boolean yBoarder = 25 < event.getSceneY() && event.getSceneY() < 25 + Game.boardWidth;
		if (xBorder && yBoarder) {
			attack(event, isGonnaGetAttacked);
		}
	}
	
	private void attack(MouseEvent event, Player player) throws IOException {
		
		//the following statements check if we have valid pawns left and will place pawns on players board
		if(Game.getContent() == BoardContents.SOLDIER) {
			Soldier.soldierAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CAVALRY && Cavalry.isHorizontalSw()) {
			Cavalry.cavalryHorizontalAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CAVALRY && !Cavalry.isHorizontalSw()) {
			Cavalry.cavalryVerticalAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CASTLE) {
			Castle.castleAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.HEADQUARTER) {
			HeadQuarter.headQuarterAttack(event.getSceneX(), event.getSceneY(),player);
		}
		//setting attacked places on board
		drawXs();
	}
	
	
	
	
	
	
	//-------------------------------------------------------------//
	public static void setIsGonnaAtt (Player p) {
		isGonnaGetAttacked = p;
	}
}
