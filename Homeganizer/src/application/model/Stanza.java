package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Stanza {

	private String id;
	private int larghezza, profondit�;
	private String proprietario;
	private LinkedList<Mobile> mobili;
	private ArrayList<String> whitelisted;
	
	public Stanza(String Id, String Proprietario, int Larghezza, int Profondit�) {
		this.id= Id;
		this.proprietario= Proprietario;
		this.larghezza= Larghezza;
		this.profondit�= Profondit�;
		this.mobili = new LinkedList<Mobile>();
	}
	
	public void aggiungiMobile(Mobile m) {
		mobili.add(m);
	}
	
	public boolean isWhitelisted(String id) {
		for(String p : whitelisted) {
			if(p.equals(id)) { return true; }
		}	
				
		return false;
	}
	
	public LinkedList <Mobile> getMobili() {
		return mobili;
	}
	
	public String getId() {
		return id;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public int getProfondit�() {
		return profondit�;
	}

	public String getProprietario() {
		return proprietario;
	}
			
}
