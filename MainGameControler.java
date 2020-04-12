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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainGameControler {
	public AnchorPane pane;
	public static Game game ;
	private static Player isGonnaGetAttacked;
	public void initialize() {
		game = InitializeControler.getGame();
		isGonnaGetAttacked = game.getPlayer1();
		drawP1RawBoard();
		drawP2RawBoard();
		drawTriangle();
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
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize, initX = 25 , initY = 75;
		
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
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize, initX = 775 , initY = 75;
		
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
		
		double width = (double)Game.boardWidth / Game.boardSize, initX = 25 , initY = 75;	
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (game.getPlayer1().getGameBoard().getBoardArray()[i][j] == BoardContents.ATTACKED) {
					
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
		double width = (double)Game.boardWidth / Game.boardSize, initX = 775 , initY = 75;	
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (game.getPlayer2().getGameBoard().getBoardArray()[i][j] == BoardContents.ATTACKED) {
					
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
	private void drawTriangle() {
		Rectangle r = new Rectangle(726,355, 47,140);
		r.setFill(Color.rgb(14, 29, 76));
		pane.getChildren().add(r);
		if (isGonnaGetAttacked == game.getPlayer1()) {
			 Polygon triangle = new Polygon();
		     triangle.getPoints().addAll(735.0 , 425.0, 760.0, 375.0 , 760.0 , 475.0);
		     triangle.setFill(Color.WHITE);
		     triangle.setStroke(Color.WHITE);
		     pane.getChildren().add(triangle);
		}
		else {
			 Polygon triangle = new Polygon();
		     triangle.getPoints().addAll(740.0, 375.0,  740.0, 475.0, 765.0, 425.0);
		     triangle.setFill(Color.WHITE);
		     triangle.setStroke(Color.WHITE);
		     pane.getChildren().add(triangle);
		}
	}
	
	//-------------------------------------- attack methods ------------------------------------------//
	public void attack(MouseEvent event) throws IOException { 
		boolean yBoarder = 75 < event.getSceneY() && event.getSceneY() < 75 + Game.boardWidth;

		if (isGonnaGetAttacked == game.getPlayer1()) {
			boolean xBorder = (25 < event.getSceneX() && event.getSceneX() < 25 + Game.boardWidth);
			if (xBorder && yBoarder) {
				attack(event, isGonnaGetAttacked);
			}
		}
		
		if (isGonnaGetAttacked == game.getPlayer2()) {
			boolean xBorder = (775 < event.getSceneX() && event.getSceneX() < 775 + Game.boardWidth);
			if (xBorder && yBoarder) {
				attack(event, isGonnaGetAttacked);
			}
		}	
	}
	
	private void attack(MouseEvent event, Player player) throws IOException {
		
		//the following statements check if we have valid pawns left and will place pawns on players board
		if(Game.getContent() == BoardContents.SOLDIER) {
			Soldier.soldierAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CAVALRY && Cavalry.isHorizontalSw() && getTurn().getCavalry().isActive()) {
			Cavalry.cavalryHorizontalAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CAVALRY && !Cavalry.isHorizontalSw() && getTurn().getCavalry().isActive()) {
			Cavalry.cavalryVerticalAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.CASTLE && getTurn().getCastle().isActive()) {
			Castle.castleAttack(event.getSceneX(), event.getSceneY(),player);
		}
		if(Game.getContent() == BoardContents.HEADQUARTER && getTurn().getHeadquarter().isActive()) {
			HeadQuarter.headQuarterAttack(event.getSceneX(), event.getSceneY(),player);
		}
		//setting attacked places on board
		drawXs();
		drawTriangle();
		checkEndOfGame();
	}
	
	//--------------------------------------------------------------------------------//
	public static void setIsGonnaAtt (Player p) {
		isGonnaGetAttacked = p;
	}
	public static Player getTurn() {
		if (isGonnaGetAttacked == game.getPlayer1()) return game.getPlayer2();
		return game.getPlayer1();
	}
	private void checkEndOfGame() throws IOException {
		if (game.endOfGame() != null) {
			Parent winner = FXMLLoader.load(getClass().getResource("winner.fxml"));
			Main.winner = new Scene(winner);
			Main.winner.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.window.setScene(Main.winner);
		}
	}
}
