package UI;
import GameCore.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SettingControler {
	@FXML private CheckBox checkBox;
	@FXML private TextField text;
	
	
	@FXML 
	private void boardSizeDeterminer(ActionEvent event) {
		//setting the board size to whatever in written in text field
		Game.setBoardSize(Integer.parseInt(text.getText()));
	}
	
	@FXML 
	private void backToMain() {
		InitializeControler.game = new Game();
		Main.window.setScene(Main.mainMenu);
	}
	public void initialize() {
		text.setText(Game.boardSize+"");
	}
}
