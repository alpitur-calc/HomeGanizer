package application.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class MessageView {
	public static void showMessageAlert(AlertType alertType, String title, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(contentText);
		alert.show();
	}
	
	public static ButtonType yesNoRequest(AlertType alertType,String text) {
		Alert alert = new Alert(alertType, text, ButtonType.YES, ButtonType.NO);
		alert.showAndWait();
		return alert.getResult();
	}
}
