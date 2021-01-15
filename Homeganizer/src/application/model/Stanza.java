package application.model;

import java.util.ArrayList;
import java.util.Map;

public class Stanza {

	private String id;
	private int larghezza, profondità;
	private String proprietario;
	private Map<String, Mobile> mobili;
	private ArrayList<String> whitelisted;
	
	public Stanza(String Id, String Proprietario, int Larghezza, int Profondità) {
		this.id= Id;
		this.proprietario= Proprietario;
		this.larghezza= Larghezza;
		this.profondità= Profondità;
	}

	public String getId() {
		return id;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public int getProfondità() {
		return profondità;
	}

	public String getProprietario() {
		return proprietario;
	}
	
	public boolean isWhitelisted(String id) {
		for(String p : whitelisted) {
			if(id.equals(id)) { return true; }
		}	
		
		return false;
	}
		
}
