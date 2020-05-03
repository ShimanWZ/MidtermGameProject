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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ShopController {
	@FXML private AnchorPane pane;
	private Player turn = MainGameControler.getTurn();
	private ArrayList<ImageView> tempImgs = new ArrayList<ImageView>();
	private static boolean placed = false;
	@FXML private Label money;
	
	public void initialize() throws FileNotFoundException {
		money.setText(turn.getWallet() + "$");
		drawRawBoard();
		drawXs(turn);
	}
	
	//----------------------making the base ready to accept a pawn----------------------//
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
	
	
	// placing pawn on player's board
	@FXML
	private void placePawn(MouseEvent event) throws IOException {
		boolean xBorder = 100 < event.getSceneX() && event.getSceneX() < 100 + Game.boardWidth;
		boolean yBoarder = 100 < event.getSceneY() && event.getSceneY() < 100 + Game.boardWidth;
		if (xBorder && yBoarder) {
			placePawnOnBoard(event, turn);
		}
	}
	
	private void placePawnOnBoard(MouseEvent event, Player player) throws IOException {
		
		placed = false;
		
		
		//the following statements check if we have enough money and will place pawns on players board
		if(Game.getContent() == BoardContents.SOLDIER && turn.getWallet() >= Soldier.getPrice()) {
			Soldier.placeSoldier(event.getSceneX(), event.getSceneY(),player);
			if (placed) turn.setWallet(turn.getWallet() - Soldier.getPrice());
		}
		if(Game.getContent() == BoardContents.CAVALRY && Cavalry.isHorizontalSw() && turn.getWallet() >= Cavalry.getPrice()) {
			Cavalry.placeCavalry(event.getSceneX(), event.getSceneY(),player);
			if (placed) turn.setWallet(turn.getWallet() - Cavalry.getPrice());
		}
		if(Game.getContent() == BoardContents.CAVALRY && !Cavalry.isHorizontalSw() && turn.getWallet() >= Cavalry.getPrice()) {
			Cavalry.placeCavalry(event.getSceneX(), event.getSceneY(),player);
			if (placed) turn.setWallet(turn.getWallet() - Cavalry.getPrice());
		}
		if(Game.getContent() == BoardContents.CASTLE && turn.getWallet() >= Castle.getPrice()) {
			Castle.placeCastle(event.getSceneX(), event.getSceneY(),player);
			if (placed) turn.setWallet(turn.getWallet() - Castle.getPrice());
		}
		if(Game.getContent() == BoardContents.HEADQUARTER && turn.getWallet() >= HeadQuarter.getPrice()) {	
			HeadQuarter.placeHeadQuarter(event.getSceneX(), event.getSceneY(),player);
			if (placed) turn.setWallet(turn.getWallet() - HeadQuarter.getPrice());
			
		}	
		
		if(placed) {
			drawXs(turn);
			MainGameControler.setIsGonnaGetAtt(MainGameControler.getTurn());
			Main.window.setScene(Main.gameScene);
		}
	}
	
//-------------------------------drawing methods-----------------------------//
	
	//this method will draw reserved board places
	public void drawXs(Player player) throws FileNotFoundException {
		double width = (double)Game.boardWidth / Game.boardSize, initX = 100 , initY = 100;
			
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (player.getGameBoard().getBoardArray()[i][j] != BoardContents.EMPTY) {
					
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
	
	// this method will draw mouse cursor contents
	public void drawTempXs(Player player) throws FileNotFoundException {
		
		double width = (double)Game.boardWidth / Game.boardSize, initX = 100 , initY = 100;
	
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

	@FXML
	private void drawInitBoard(MouseEvent event) throws FileNotFoundException {
		for (ImageView a: tempImgs ) {
			pane.getChildren().remove(a);
		}
		tempImgs.clear();
		money.setText(turn.getWallet() + "$");
		Player initPlayer = new Player();
		boolean xBorder = 100 < event.getSceneX() && event.getSceneX() < 100 + Game.boardWidth;
		boolean yBoarder = 100 < event.getSceneY() && event.getSceneY() < 100 + Game.boardWidth;
		if (xBorder && yBoarder) {		
		if(Game.getContent() == BoardContents.SOLDIER) {
				Soldier.placeSoldier(event.getSceneX(), event.getSceneY(),initPlayer);
			}
			if(Game.getContent() == BoardContents.CAVALRY && Cavalry.isHorizontalSw()) {
				Cavalry.placeCavalry(event.getSceneX(), event.getSceneY(),initPlayer);
			}
			if(Game.getContent() == BoardContents.CAVALRY && !Cavalry.isHorizontalSw()) {
				Cavalry.placeCavalry(event.getSceneX(), event.getSceneY(),initPlayer);
			}
			if(Game.getContent() == BoardContents.CASTLE) {
				Castle.placeCastle(event.getSceneX(), event.getSceneY(),initPlayer );
			}
			if(Game.getContent() == BoardContents.HEADQUARTER) {
				HeadQuarter.placeHeadQuarter(event.getSceneX(), event.getSceneY(),initPlayer);
			}
		}
		drawTempXs(initPlayer);
	}
	//this method will draw the table
	private void drawRawBoard() {
		double curX, curY, width = (double)Game.boardWidth / Game.boardSize, initX = 100 , initY = 100;
		
		//drawing background
		Rectangle r = new Rectangle(initX,initY, initX + Game.boardWidth, initY + Game.boardWidth);
		r.setFill(Color.rgb(14, 29, 76));
		pane.getChildren().add(r);
		
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
	//-------------------------------------------------------------------------------//
	public static void setPlaced(boolean placed) {
		ShopController.placed = placed;
	}
	public void backToGame() {
		Main.window.setScene(Main.gameScene);
	}
}
