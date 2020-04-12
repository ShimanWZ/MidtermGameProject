package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinnerControler {
	@FXML
	public Label label;
	
	public void initialize() {
		String labelString = new String();
		if (MainGameControler.game.endOfGame() == MainGameControler.game.getPlayer1()) {
			labelString = "Player one is the winner";
		}
		else labelString = "Player two is the winner";
		label.setText(labelString);
	}
	
	public void backToMain() {
		Main.window.setScene(Main.mainMenu);
	}
}
