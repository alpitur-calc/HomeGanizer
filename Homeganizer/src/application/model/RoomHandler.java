package application.model;

import java.util.LinkedList;

public class RoomHandler {
	
	private static RoomHandler instance = null;
	private LinkedList<Stanza> stanze;
	private String idProprietario;
	
	public static RoomHandler getInstance() {
		if(instance == null)
			instance = new RoomHandler();
		return instance;
	}
	
	private RoomHandler() {
		stanze = new LinkedList<Stanza>();
	}

	public boolean aggiungiStanza(String nome, int larghezza, int profondit�) {
		stanze.add(new Stanza(nome, idProprietario, larghezza, profondit�));
		return true;
	}
	
	public boolean rimuoviStanza(String id) {
		for(Stanza i : stanze) {
			if(i.getId() == id) {
				stanze.remove(i);
			}
		}
		return true;
	}
	
	public void caricaStanze() {
		
	}
	
	public String getProprietario() {
		return idProprietario;
	}
	
	public void setProprietario(String idProprietario) {
		this.idProprietario = idProprietario;
	}
	
	public LinkedList<Stanza> getStanze() {
		return stanze;
	}
	
}
