package UI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import GameCore.BoardContents;
import GameCore.Castle;
import GameCore.Cavalry;
import GameCore.Game;
import GameCore.HeadQuarter;
import GameCore.Player;
import GameCore.Soldier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainGameControler {
	@FXML private AnchorPane pane;
	@FXML private Label player1money;
	@FXML private Label player2money;
	public static Game game ;
	private static Player isGonnaGetAttacked;
	private ArrayList<ImageView> tempImgs = new ArrayList<ImageView>();
	private ArrayList<Object> temp = new ArrayList<Object>();
	
	
	public void initialize() throws FileNotFoundException {
		if (game == null) {
			game = InitializeControler.getGame();
			setIsGonnaGetAtt(MainGameControler.game.getPlayer1());
		}
		player1money.setText(game.getPlayer1().getWallet() + "$");
		player2money.setText(game.getPlayer2().getWallet() + "$");
		drawRawBoard(25, 75);
		drawRawBoard(775, 75);
		drawTriangle();
		drawBoardContents();
	}
	

	//-----------------making game ready to attack----------------------//
	@FXML 
	private void setCavalry() {
			Game.setContent(BoardContents.CAVALRY);
			Cavalry.setHorizontalSw(false);
	}
	@FXML 
	private void setCavalry2() {
			Game.setContent(BoardContents.CAVALRY);
			Cavalry.setHorizontalSw(true);
	}
	@FXML 
	private void setSoldier() {
		Game.setContent(BoardContents.SOLDIER);
	}
	@FXML 
	private void setCastle() {
			Game.setContent(BoardContents.CASTLE);
	}
	@FXML
	private void setHeadQuarter() {
			Game.setContent(BoardContents.HEADQUARTER);
	}
	
	//----------------------drawing methods--------------------------//
	private void drawRawBoard(double initX, double initY) {
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize;
		
		//drawing vertical lines
		for (curX = initX ; curX <= initX + Game.boardWidth + 1 ; curX += width) {
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
	private void drawBoardContents() throws FileNotFoundException {
		drawContents(game.getPlayer1(), 25, 75);
		drawContents(game.getPlayer2(), 775, 75);
	}
	private void drawContents(Player player, double initX, double initY) throws FileNotFoundException {
		double width = (double)Game.boardWidth / Game.boardSize	;
		
		
		//drawing the Xs and Os in game, O is success attacked and X is empty one
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.SUCCESS_ATTACKED) {
					
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
				else if (player.getGameBoard().getBoardArray()[i][j] == BoardContents.EMPTY_ATTACKED) {
					
					 Image image = new Image(new FileInputStream("D:\\midproject\\pics\\final pics\\X.png"));  
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
		
		//cleaning pane from recent triangle
		for (Object o : temp) {
			pane.getChildren().remove(o);
		}
		temp.clear();
		
		// initializing and drawing triangle
		if (isGonnaGetAttacked == game.getPlayer1()) {
			 Polygon triangle = new Polygon();
		     triangle.getPoints().addAll(735.0 , 425.0, 760.0, 375.0 , 760.0 , 475.0);
		     triangle.setFill(Color.WHITE);
		     triangle.setStroke(Color.WHITE);
		     pane.getChildren().add(triangle);
		     temp.add(triangle);
		}
		else {
			 Polygon triangle = new Polygon();
		     triangle.getPoints().addAll(740.0, 375.0,  740.0, 475.0, 765.0, 425.0);
		     triangle.setFill(Color.WHITE);
		     triangle.setStroke(Color.WHITE);
		     pane.getChildren().add(triangle);
		     temp.add(triangle);
		}
	}
	
	
	@FXML
	private void drawTemps(MouseEvent event) throws FileNotFoundException {
		
		//cleaning pane from last children added
		for (ImageView a: tempImgs ) {
			pane.getChildren().remove(a);
		}
		tempImgs.clear();
	
		drawTriangle();
	
		//displaying the money of each player
		player1money.setText(game.getPlayer1().getWallet() + "$");
		player2money.setText(game.getPlayer2().getWallet() + "$");
		
		
		Player player = new Player();
		boolean yBoarder = 75 < event.getSceneY() && event.getSceneY() < 75 + Game.boardWidth;
		boolean xBorder = (25 < event.getSceneX() && event.getSceneX() < 25 + Game.boardWidth)
				|| (775 < event.getSceneX() && event.getSceneX() < 775 + Game.boardWidth);

		
		
		if (xBorder && yBoarder) {		
			if(Game.getContent() == BoardContents.SOLDIER) {
				Soldier.placeTempSoldier(event.getSceneX(), event.getSceneY(),player);
			}
			if(Game.getContent() == BoardContents.CAVALRY && Cavalry.isHorizontalSw() && getTurn().getCavalry().isActive()) {
				Cavalry.placeTempCavalry(event.getSceneX(), event.getSceneY(),player);
			}
			
			if(Game.getContent() == BoardContents.CAVALRY && !Cavalry.isHorizontalSw() && getTurn().getCavalry().isActive()) {
				Cavalry.placeTempCavalry(event.getSceneX(), event.getSceneY(),player);
			}
			if(Game.getContent() == BoardContents.CASTLE && getTurn().getCastle().isActive()) {
				Castle.placeTempCastle(event.getSceneX(), event.getSceneY(),player );
			}
			if(Game.getContent() == BoardContents.HEADQUARTER && getTurn().getHeadquarter().isActive()) {
				HeadQuarter.placeTempHeadquarter(event.getSceneX(), event.getSceneY(),player);
			}
		}
		
		drawTempXs(event, player);
	}
	//drawing the mouse cursor content
		private void drawTempXs(MouseEvent event, Player player) throws FileNotFoundException {
			double width = (double)Game.boardWidth / Game.boardSize, initX, initY = 75;
			
			
			//initializing initial x
			if (25 < event.getSceneX() && event.getSceneX() < 25 + Game.boardWidth) {
				initX = 25;
			}
			else initX = 775;	
			
			
			
			for (int i = 0 ; i < Game.boardSize ; i++) {
				for (int j = 0 ; j < Game.boardSize ; j++) {
					if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY) {
						
						 Image image = new Image(new FileInputStream("D:\\midproject\\pics\\final pics\\O.png"));  
					     ImageView imageView = new ImageView(image); 
					      
					     double curX = initX + ( j * width);
					     double curY = initY + (i* width);
					     
					     imageView.setX(curX); 
					     imageView.setY(curY); 
					     imageView.setFitHeight(width-5); 
					     imageView.setFitWidth(width-5); 
					     tempImgs.add(imageView);
					     pane.getChildren().add(imageView);
					}
				}
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
		drawBoardContents();
		drawTriangle();
		checkEndOfGame();
	}
	
	//--------------------------------------------------------------------------------//
	public static void setIsGonnaGetAtt (Player p) {
		isGonnaGetAttacked = p;
	}
	public static Player isGonnaGetAtt() {
		return isGonnaGetAttacked;
	}
	public static Player getTurn() {
		if (isGonnaGetAttacked == game.getPlayer1()) return game.getPlayer2();
		return game.getPlayer1();
	}
	public void afterShopping() {
		drawTriangle();
	}
	
	private void checkEndOfGame() throws IOException {
		if (game.endOfGame() != null) {
			Parent winner = FXMLLoader.load(getClass().getResource("winner.fxml"));
			Main.winner = new Scene(winner);
			Main.winner.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.window.setScene(Main.winner);
		}
	}
	@FXML private void writeFile() throws IOException {
		Parent MainGameMenu = FXMLLoader.load(getClass().getResource("MainGameMenu.fxml"));
		Main.mainGameMenu = new Scene(MainGameMenu);
		Main.mainGameMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.mainGameMenu);
	}
}
