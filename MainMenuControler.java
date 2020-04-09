package UI;

import java.io.IOException;

import GameCore.Game;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainMenuControler {

	
	public void startNewGame() throws IOException{
		Parent Initializing = FXMLLoader.load(getClass().getResource("Initialize.fxml"));
		Main.initialize = new Scene(Initializing);
		Main.initialize.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.initialize);
	}
	
	public void setting() throws IOException{
		Parent settingView = FXMLLoader.load(getClass().getResource("SettingMenu.fxml"));
		Main.settingMenu = new Scene(settingView);
		Main.settingMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Main.window.setScene(Main.settingMenu);
	}
	
	public void resume() throws IOException{
		
	}
	

	public void exit() {
		Platform.exit();
		System.exit(0);
	}
}
