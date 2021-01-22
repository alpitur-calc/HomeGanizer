package application.model;

import org.mindrot.jbcrypt.BCrypt;

import application.SceneHandler;
import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

public class RegisterHandler {
	public static void createUser(String insertedUsername, String insertedPassword1, String insertedPassword2,
			String secureAnswer) throws Exception {
		if (DatabaseHandler.getInstance().userExists(insertedUsername)) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Utente già registrato");
			return;
		}
		if (insertedUsername.equals("") || insertedPassword1.equals("") || insertedPassword2.equals("")
				|| secureAnswer.equals("")) {
			System.out.println("user non esiste");
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Non tutti i dati sono stati inseriti");
			return;
		}
		if (!insertedPassword1.equals(insertedPassword2)) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Le password non corrispondono");
			return;
		}
		if (insertedPassword1.length() >= 5) {
			User u = new User(insertedUsername, BCrypt.hashpw(insertedPassword1, BCrypt.gensalt(12)), secureAnswer);
			DatabaseHandler.getInstance().addUser(u);
			MessageView.showMessageAlert(AlertType.INFORMATION, "Informazione", "Utente registrato correttamente");
			SceneHandler.getInstance().goToScene("loginInterface.fxml", "Homeganizer Login", 1280, 720);
		} else
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione",
					"La password deve contenere almeno 5 caratteri");
	}

	public static void changePassword(String insertedUsername, String insertedPassword1, String insertedPassword2,
			String secureAnswer) throws Exception {
		if (!DatabaseHandler.getInstance().userExists(insertedUsername)) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Utente non registrato");
			return;
		}
		if (!DatabaseHandler.getInstance().userAnswer(insertedUsername, secureAnswer)) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Risposta di sicurezza non valida");
			return;
		}
		if (insertedUsername.equals("") || insertedPassword1.equals("") || insertedPassword1.equals("")
				|| secureAnswer.equals("")) {
			System.out.println("dati non inseriti");
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Non tutti i dati sono stati inseriti");
			return;
		}
		if (!insertedPassword1.equals(insertedPassword2)) {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Le password non corrispondono");
			return;
		}
		if (insertedPassword1.length() >= 5) {
			User u = new User(insertedUsername, BCrypt.hashpw(insertedPassword1, BCrypt.gensalt(12)), secureAnswer);
			DatabaseHandler.getInstance().UpdateUser(u);
			MessageView.showMessageAlert(AlertType.INFORMATION, "Informazione", "Password aggiornata correttamente");
			SceneHandler.getInstance().goToScene("loginInterface.fxml", "Homeganizer Login", 1280, 720);
		} else
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione",
					"La password deve contenere almeno 5 caratteri");
	}
}
