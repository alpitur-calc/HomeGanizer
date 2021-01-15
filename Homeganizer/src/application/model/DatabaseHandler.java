package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class DatabaseHandler {
	private HashMap<String,User> users;
	//private Connection con; indeciso
	
	private static DatabaseHandler instance = null;
	
	public static DatabaseHandler getInstance() {
		if(instance == null)
			instance = new DatabaseHandler();
		return instance;
	}
	
	private DatabaseHandler() {
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
				User u = new User(result.getString("username"), result.getString("password"),result.getString("secureAnswer"));
				users.put(u.getUsername(), u);
			}
			stm1.close();
		} catch (Exception e) {
			//LoginMessageView.corruptedErrorMessage();
		}
	}
	
	private static void createUsersTableIfNotExists(Connection con) throws Exception {
		PreparedStatement stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(username varchar(5),password varchar(12),secureAnswer varchar(2);");
		stm.executeUpdate();
		stm.close();
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

}
