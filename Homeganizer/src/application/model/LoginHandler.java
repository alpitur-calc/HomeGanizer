package application.model;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

public class LoginHandler {
	public static void login(String insertedUsername, String insertedPassword) {
		if (insertedUsername.equals("") || insertedPassword.equals("")) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Nome Utente o Password non inseriti");
			return;
		}
		HashMap<String, User> usernamePassword = DatabaseHandler.getInstance().getUsers();
		if (usernamePassword == null)
			return;
		try {
			if (usernamePassword.containsKey(insertedUsername)
					&& BCrypt.checkpw(insertedPassword, usernamePassword.get(insertedUsername).getPassword())) {
				MessageView.showMessageAlert(AlertType.INFORMATION, "Informazione", "Login OK"); // provvisorio in
																									// attesa della
																									// scena del
																									// programma
			} else
				MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Utente non registrato");
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Si è verificato un errore. Contattare l'amministratore");
		}
	}

}
