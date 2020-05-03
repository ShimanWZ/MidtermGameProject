package UI;

import java.io.IOException;

import GameCore.BoardContents;
import GameCore.Game;
import fileHandling.WritingFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainGameMenuController {

	@FXML private void quit() {
		Main.window.setScene(Main.mainMenu);
	}
	
	@FXML private void resumeLater() {
		WritingFile.Write();
		Main.window.setScene(Main.mainMenu);
	}
	@FXML private void shop() throws IOException {
		if (checkForCastle()) {
			Parent shop = FXMLLoader.load(getClass().getResource("shop.fxml"));
			Main.shop = new Scene(shop);
			Main.shop.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.window.setScene(Main.shop);
		}	
	}
	
	private boolean checkForCastle() {
		for (int i = 0 ; i < Game.boardSize ; i++) {
			for (int j = 0 ; j < Game.boardSize ; j++) {
				if (MainGameControler.getTurn().getGameBoard().getBoardArray()[i][j] == BoardContents.CASTLE) return true;
			}
		}
		return false;
	}
}
