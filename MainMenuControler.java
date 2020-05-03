package UI;

import java.io.IOException;

import GameCore.Game;
import fileHandling.ReadingFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMenuControler {
	
	@FXML 
	private void startNewGame() throws IOException{
		InitializeControler.game = new Game();
		Parent Initializing = FXMLLoader.load(getClass().getResource("Initialize.fxml"));
		Main.initialize = new Scene(Initializing);
		Main.initialize.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.initialize);
	}
	
	@FXML 
	private void setting() throws IOException{
		Parent settingView = FXMLLoader.load(getClass().getResource("SettingMenu.fxml"));
		Main.settingMenu = new Scene(settingView);
		Main.settingMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.settingMenu);
	}
	
	@FXML 
	private void resume() throws IOException{
		MainGameControler.game = new Game();
		ReadingFile.read();
		Parent gameScene = FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
		Main.gameScene = new Scene(gameScene);
		Main.gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.gameScene);
	}
	

	@FXML 
	private void exit() {
		Platform.exit();
		System.exit(0);
	}
}
