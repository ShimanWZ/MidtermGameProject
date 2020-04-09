package UI;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import GameCore.Game;

public class SettingControler {
	public CheckBox checkBox;
	public TextField text;
	public void soundControler(ActionEvent event) {
		//setting the sound in the game class to whatever in selected in check box
		Game.setSound(checkBox.isSelected()); 
	}
	public void boardSizeDeterminer(ActionEvent event) {
		//setting the board size to whatever in written in text field
		Game.setBoardSize(Integer.parseInt(text.getText()));
	}
	
	public void backToMain() {
		InitializeControler.game = new Game();
		Main.window.setScene(Main.mainMenu);
	}
	public void initialize() {
		text.setText(Game.boardSize+"");
		checkBox.setSelected(Game.getSound());
	}
	
}
