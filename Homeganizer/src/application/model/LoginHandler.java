package application.model;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import application.SceneHandler;
import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

public class LoginHandler {
	public static boolean login(String insertedUsername, String insertedPassword, boolean memo) {
		if (insertedUsername.equals("") || insertedPassword.equals("")) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Nome Utente o Password non inseriti");
			return false;
			
		}
		HashMap<String, User> usernamePassword = DatabaseHandler.getInstance().getUsers();
		try {
			if (usernamePassword.containsKey(insertedUsername)
					&& BCrypt.checkpw(insertedPassword, usernamePassword.get(insertedUsername).getPassword())) {
				if (memo)
					DatabaseHandler.getInstance().addMemorizedUser(insertedUsername, insertedPassword);
				else
					DatabaseHandler.getInstance().clearMemorizedUser();
				SceneHandler.getInstance().goToScene("mainInterface.fxml", "Room Editor", 1280, 720);
				RoomHandler.getInstance().setProprietario(insertedUsername);
				DatabaseHandler.getInstance().loadRooms(insertedUsername);
			} else
				{MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Utente non registrato"); return false;}
				
				
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
			return false;
		}
		return true;
	}

}
