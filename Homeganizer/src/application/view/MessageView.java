package application.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageView {
	public static void showMessageAlert(AlertType alertType, String title, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText("");
		alert.setContentText(contentText);
		alert.show();
	}
}
