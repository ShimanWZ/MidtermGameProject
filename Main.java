package UI;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	static Stage window;
	static Scene mainMenu, settingMenu, initialize, gameScene, winner, mainGameMenu, shop;
	@Override
	public void start(Stage primaryStage) throws IOException {
		window = primaryStage;
		initializemainMenu();
		window.setScene(mainMenu);
		window.show();
	}
	
	//----------------------initializing scenes---------------------------------//
	private void initializemainMenu() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		mainMenu = new Scene(root);
		mainMenu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	}
	//-------------------------------main---------------------------------------------//
	public static void main(String[] args) {
		launch(args);
	}
}
