package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

public class DatabaseHandler {
	private HashMap<String, User> users;
	private HashMap<String,Integer> elencoCounter;
	private MemorizedUserPassword memorizedUser;
	private LinkedList<Stanza> stanze;
	private String currentUser;

	private static DatabaseHandler instance = null;

	public static final String nomiCounter[] = { "IDCOUNTER", "IDCOUNTERARMADIO", "IDCOUNTERCASSAPANCA",
			"IDCOUNTERLIBRERIA", "IDCOUNTERSCAFFALE", "IDCOUNTERTAVOLO", "IDCOUNTERBO", "IDCOUNTERCO", "IDCOUNTERDI",
			"IDCOUNTEREL", "IDCOUNTERLI", "IDCOUNTERUT" };

	public static DatabaseHandler getInstance() {
		if (instance == null)
			instance = new DatabaseHandler();
		return instance;
	}

	private DatabaseHandler() {
		memorizedUser = null;
		initialLoad();
	}

	private void initialLoad() {
		users = new HashMap<String, User>();
		elencoCounter = new HashMap<String,Integer>();
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
			createTablesIfNotExist(con);
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
			stm1 = con.prepareStatement("SELECT * FROM counter;");
			result = stm1.executeQuery();
			while (result.next()) {
				elencoCounter.put(result.getString("tipo"), result.getInt("valore"));
			}
			stm1.close();
			setCounters();
		} catch (Exception e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore",
					"Si è verificato un errore. Contattare l'amministratore");
		}
	}
	
	public void setCounters() {
		Stanza.IDCOUNTER = elencoCounter.get(DatabaseHandler.nomiCounter[0]);
		Mobile.IDCOUNTERARMADIO = elencoCounter.get(DatabaseHandler.nomiCounter[1]);
		Mobile.IDCOUNTERCASSAPANCA = elencoCounter.get(DatabaseHandler.nomiCounter[2]);
		Mobile.IDCOUNTERLIBRERIA = elencoCounter.get(DatabaseHandler.nomiCounter[3]);
		Mobile.IDCOUNTERSCAFFALE = elencoCounter.get(DatabaseHandler.nomiCounter[4]);
		Mobile.IDCOUNTERTAVOLO = elencoCounter.get(DatabaseHandler.nomiCounter[5]);
		Oggetto.IDCOUNTERBO = elencoCounter.get(DatabaseHandler.nomiCounter[6]);
		Oggetto.IDCOUNTERCO = elencoCounter.get(DatabaseHandler.nomiCounter[7]);
		Oggetto.IDCOUNTERDI = elencoCounter.get(DatabaseHandler.nomiCounter[8]);
		Oggetto.IDCOUNTEREL = elencoCounter.get(DatabaseHandler.nomiCounter[9]);
		Oggetto.IDCOUNTERLI = elencoCounter.get(DatabaseHandler.nomiCounter[10]);
		Oggetto.IDCOUNTERUT = elencoCounter.get(DatabaseHandler.nomiCounter[11]);
	}

	public HashMap<String, Integer> getElencoCounter() {
		return elencoCounter; // fa le bizze e non ho ancora capito perchè
	}

	private static void createTablesIfNotExist(Connection con) throws Exception {
		PreparedStatement stm = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS users(username varchar(2),password varchar(2),secureAnswer varchar(2));");
		stm.executeUpdate();
		stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS memUser(username varchar(2),password varchar(2));");
		stm.executeUpdate();
		stm = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS stanze(id varchar(2),nome varchar(2),proprietario varchar(2),larghezza int,profondità int);");
		stm.executeUpdate();
		stm = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS mobili(id varchar(2),idStanza varchar(2),nome varchar(2),tipo varchar(2),x int,y int,w int,h int,proprietario varchar(2));");
		stm.executeUpdate();
		stm = con.prepareStatement(
				"CREATE TABLE IF NOT EXISTS oggetti(id varchar(2),idMobile varchar(2),nome varchar(2),descrizione varchar(2),tipo varchar(2),proprietario varchar(2));");
		stm.executeUpdate();
		stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS counter(tipo varchar(2),valore int);");
		stm.executeUpdate();
		if (mustInitializeCounters(con,stm))
			initializeCounters(con, stm);
		stm.close();
	}

	private static boolean mustInitializeCounters(Connection con, PreparedStatement stm) throws Exception {
		boolean b = true;
		stm = con.prepareStatement("SELECT * FROM counter;");
		ResultSet result = stm.executeQuery();
		while(result.next())
			b = false;
		return b;
	}

	private static void initializeCounters(Connection con, PreparedStatement stm) throws Exception {
		for (int i = 0; i < 12; i++) {
			stm = con.prepareStatement("INSERT INTO counter VALUES(?,?);");
			stm.setString(1, nomiCounter[i]);
			stm.setInt(2, 1);
			stm.executeUpdate();
		}
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

	public LinkedList<Stanza> getStanze() {
		return stanze;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public void loadRooms(String insertedUsername) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
		PreparedStatement stm1 = con.prepareStatement("SELECT * FROM stanze WHERE proprietario=?;");
		stm1.setString(1, insertedUsername);
		ResultSet result = stm1.executeQuery();
		while (result.next()) {
			Stanza s = new Stanza(result.getString("id"), result.getString("nome"), result.getString("proprietario"),
					result.getInt("larghezza"), result.getInt("profondità"));
			loadFurniture(result.getString("id"), s, con,stm1);
			stanze.add(s);
		}
		stm1.close();
	}

	private void loadFurniture(String idStanza, Stanza s, Connection con,PreparedStatement stm1) throws Exception {
		stm1 = con.prepareStatement("SELECT * FROM mobili WHERE idStanza=?;");
		stm1.setString(1, idStanza);
		ResultSet result = stm1.executeQuery();
		while (result.next()) {
			Mobile m = new Mobile(result.getString("id"), result.getString("idStanza"), result.getString("nome"),
					result.getString("tipo"), result.getInt("x"), result.getInt("y"), result.getInt("w"),
					result.getInt("h"));
			loadObjects(result.getString("id"), m, con,stm1);
			s.aggiungiMobile(m);
		}
	}

	private void loadObjects(String idMobile, Mobile m, Connection con, PreparedStatement stm1) throws Exception {
		stm1 = con.prepareStatement("SELECT * FROM oggetti WHERE idMobile=?;");
		stm1.setString(1, idMobile);
		ResultSet result = stm1.executeQuery();
		while (result.next()) {
			Oggetto o = new Oggetto(result.getString("id"), result.getString("idMobile"), result.getString("nome"),
					result.getString("descrizione"), result.getString("tipo"));
			m.aggiungiOggetto(o);
		}
	}
	
	public void saveRooms(LinkedList<Stanza> stanze) throws Exception{
		Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
		saveCounters(con);
		deleteRooms(con);
		for(Stanza s : stanze) {
			PreparedStatement stm1 = con.prepareStatement("INSERT INTO stanze VALUES(?,?,?,?,?);");
			stm1.setString(1, s.getId());
			stm1.setString(2, s.getNome());
			stm1.setString(3, s.getProprietario());
			stm1.setInt(4, s.getLarghezza());
			stm1.setInt(5, s.getProfondità());
			stm1.executeUpdate();
			saveFurniture(con,stm1,s.getMobili());
			stm1.close();
		}
	}

	private void saveCounters(Connection con) throws Exception{
		PreparedStatement stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Stanza.IDCOUNTER);
		stm.setString(2, nomiCounter[0]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Mobile.IDCOUNTERARMADIO);
		stm.setString(2, nomiCounter[1]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Mobile.IDCOUNTERCASSAPANCA);
		stm.setString(2, nomiCounter[2]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Mobile.IDCOUNTERLIBRERIA);
		stm.setString(2, nomiCounter[3]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Mobile.IDCOUNTERSCAFFALE);
		stm.setString(2, nomiCounter[4]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Mobile.IDCOUNTERTAVOLO);
		stm.setString(2, nomiCounter[5]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTERBO);
		stm.setString(2, nomiCounter[6]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTERCO);
		stm.setString(2, nomiCounter[7]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTERDI);
		stm.setString(2, nomiCounter[8]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTEREL);
		stm.setString(2, nomiCounter[9]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTERLI);
		stm.setString(2, nomiCounter[10]);
		stm.executeUpdate();
		stm = con.prepareStatement("UPDATE counter SET valore=? WHERE tipo=?;");
		stm.setInt(1, Oggetto.IDCOUNTERUT);
		stm.setString(2, nomiCounter[11]);
		stm.executeUpdate();
		stm.close();
	}

	private void saveFurniture(Connection con, PreparedStatement stm1, LinkedList<Mobile> mobili) throws Exception {
		for(Mobile m : mobili) {
			stm1 = con.prepareStatement("INSERT INTO mobili VALUES(?,?,?,?,?,?,?,?,?);");
			stm1.setString(1, m.getId());
			stm1.setString(2, m.getIdStanza());
			stm1.setString(3, m.getNome());
			stm1.setString(4, m.getTipo());
			stm1.setInt(5, m.getX());
			stm1.setInt(6, m.getY());
			stm1.setInt(7, m.getW());
			stm1.setInt(8, m.getH());
			stm1.setString(9, currentUser);
			stm1.executeUpdate();
			saveObjects(con,stm1,m.getOggetti());
		}
	}

	private void saveObjects(Connection con, PreparedStatement stm1, LinkedList<Oggetto> oggetti) throws Exception{
		for(Oggetto o : oggetti) {
			stm1 = con.prepareStatement("INSERT INTO oggetti VALUES(?,?,?,?,?,?);");
			stm1.setString(1, o.getId());
			stm1.setString(2, o.getIdMobile());
			stm1.setString(3, o.getNome());
			stm1.setString(4, o.getDescrizione());
			stm1.setString(5, o.getTipo());
			stm1.setString(6, currentUser);
			stm1.executeUpdate();
		}
	}

	private void deleteRooms(Connection con) throws Exception {
		PreparedStatement stm1 = con.prepareStatement("DELETE FROM stanze WHERE proprietario=?;");
		stm1.setString(1, currentUser);
		stm1.executeUpdate();
		stm1 = con.prepareStatement("DELETE FROM mobili WHERE proprietario=?;");
		stm1.setString(1, currentUser);
		stm1.executeUpdate();
		stm1 = con.prepareStatement("DELETE FROM oggetti WHERE proprietario=?;");
		stm1.setString(1, currentUser);
		stm1.executeUpdate();
		stm1.close();
	}



}
