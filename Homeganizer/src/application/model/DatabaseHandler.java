package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

public class DatabaseHandler {
	private HashMap<String, User> users;
	private MemorizedUserPassword memorizedUser;
	// private Connection con; indeciso

	private static DatabaseHandler instance = null;

	public static DatabaseHandler getInstance() {
		if (instance == null)
			instance = new DatabaseHandler();
		return instance;
	}

	private DatabaseHandler() {
		memorizedUser = null;
		loadUsers();
	}

	private void loadUsers() {
		users = new HashMap<String, User>();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			createUsersTableIfNotExists(con);
			PreparedStatement stm1 = con.prepareStatement("SELECT * FROM users;");
			ResultSet result = stm1.executeQuery();
			while (result.next()) {
				User u = new User(result.getString("username"), result.getString("password"),
						result.getString("secureAnswer"));
				users.put(u.getUsername(), u);
			}
			stm1 = con.prepareStatement("SELECT * FROM memUser;");
			result = stm1.executeQuery();
			while (result.next())
				memorizedUser = new MemorizedUserPassword(result.getString("username"), result.getString("password"));
			stm1.close();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}

	private static void createUsersTableIfNotExists(Connection con) throws Exception {
		PreparedStatement stm = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS users(username varchar(5),password varchar(12),secureAnswer varchar(2));");
		stm.executeUpdate();
		stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS memUser(username varchar(5),password varchar(5));");
		stm.executeUpdate();
		stm.close();
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void addMemorizedUser(String username, String password) {
		clearMemorizedUser();
		memorizedUser = new MemorizedUserPassword(username, password);
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			PreparedStatement stm1 = con.prepareStatement("INSERT INTO memUser VALUES(?,?);");
			stm1.setString(1, memorizedUser.getUsername());
			stm1.setString(2, memorizedUser.getPassword());
			stm1.executeUpdate();
			stm1.close();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}

	public void clearMemorizedUser() {
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			PreparedStatement stm1 = con.prepareStatement("DELETE FROM memUser;");
			stm1.executeUpdate();
			stm1.close();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}

	public void addUser(User u) {
		users.put(u.getUsername(), u);
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			PreparedStatement stm1 = con.prepareStatement("INSERT INTO users VALUES(?,?,?);");
			stm1.setString(1, u.getUsername());
			stm1.setString(2, u.getPassword());
			stm1.setString(3, u.getSecureAnswer());
			stm1.executeUpdate();
			stm1.close();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}

	public boolean userExists(String u) {
		return users.containsKey(u);
	}

	public boolean userAnswer(String insertedUsername, String secureAnswer) {
		return users.get(insertedUsername).getSecureAnswer().equals(secureAnswer);
	}

	public void UpdateUser(User u) {
		users.put(u.getUsername(), u);
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			PreparedStatement stm1 = con.prepareStatement("UPDATE users SET password=? WHERE username=?;");
			stm1.setString(1, u.getPassword());
			stm1.setString(2, u.getUsername());
			stm1.executeUpdate();
			stm1.close();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}

	public MemorizedUserPassword getMemorizedUser() {
		return memorizedUser;
	}
}
