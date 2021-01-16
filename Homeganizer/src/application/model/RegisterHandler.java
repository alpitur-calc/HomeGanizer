package application.model;

import org.mindrot.jbcrypt.BCrypt;

public class RegisterHandler {
	public static void createUser(String insertedUsername, String insertedPassword1, String insertedPassword2,
			String secureAnswer) {
		if (DatabaseHandler.getInstance().userExists(insertedUsername)) {
			System.out.println("user esiste");
			return;
		}
		if (insertedUsername.equals("") || insertedPassword1.equals("") || insertedPassword1.equals("")
				|| secureAnswer.equals("")) {
			System.out.println("user non esiste");
			// LoginMessageView.noUsernameOrPasswordError();
			return;
		}
		if (!insertedPassword1.equals(insertedPassword2)) {
			// errore
			System.out.println("password non esiste");
			return;
		}
		if (insertedPassword1.length() >= 5) {
			User u = new User(insertedUsername,BCrypt.hashpw(insertedPassword1, BCrypt.gensalt(12)),secureAnswer);
			DatabaseHandler.getInstance().addUser(u);
		} else
			System.out.println("password 5 non esiste");
		// LoginMessageView.shortPassword();
	}
}
