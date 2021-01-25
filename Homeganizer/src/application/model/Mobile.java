package application.model;

import java.util.LinkedList;

public class Mobile {
	
	public static final String 	ARMADIO = "Armadio",
								CASSAPANCA = "Cassapanca",
								LIBRERIA = "Libreria",
								SCAFFALE = "Scaffale",
								TAVOLO = "Tavolo";
					
	
	//Prendere i vari COUNTER dal DB
	
	public static Integer IDCOUNTERARMADIO = 1;
	public static Integer IDCOUNTERCASSAPANCA = 1;
	public static Integer IDCOUNTERLIBRERIA = 1;
	public static Integer IDCOUNTERSCAFFALE = 1;
	public static Integer IDCOUNTERTAVOLO = 1;
	
	
	private String id;
	private String idStanza;
	private String nome;
	private String tipo;
	private int x,y,w,h;
	private LinkedList <Oggetto> oggetti;
	
	public Mobile(String idStanza, String nome, String tipo) {
		initId(tipo);
		this.idStanza = idStanza;
		this.x = 0;
		this.y = 0;
		this.w = 1; // largo 1 blocco della matrice
		this.h = 1; // lungo 1 blocco della matrice
		this.nome = nome;
		this.tipo = tipo;
		this.oggetti = new LinkedList <Oggetto>();
	}
	
	
	private void initId(String tipo) {
		switch (tipo) {
		
		case ARMADIO:
			this.id = "MAR" + IDCOUNTERARMADIO.toString();
			IDCOUNTERARMADIO++;
			break;
			
		case CASSAPANCA:
			this.id = "MCA" + IDCOUNTERCASSAPANCA.toString();
			IDCOUNTERCASSAPANCA++;
			break;
			
		case LIBRERIA:
			this.id = "MLI" + IDCOUNTERLIBRERIA.toString();
			IDCOUNTERLIBRERIA++;
			break;
		
		case SCAFFALE:
			this.id = "MSC" + IDCOUNTERSCAFFALE.toString();
			IDCOUNTERSCAFFALE++;
			break;
		
		case TAVOLO:
			this.id = "MTA" + IDCOUNTERTAVOLO.toString();
			IDCOUNTERTAVOLO++;
			break;
							
		default:
			break;
		}
	}
	
	
	public void aggiungiOggetto(String nome, String descrizione, String tipo){
		oggetti.add(new Oggetto(this.id,nome, descrizione, tipo));
	}
	
	//Va fatta la parte del DB
	public void rimuoviOggetto(String id) {
		for (Oggetto i : oggetti) {
			if(i.getId().equals(id)) {
				oggetti.remove(i);
			}
		}
	}

	public LinkedList <Oggetto> getOggetti() {
		return oggetti;
	}
	
	public String getIcona() {
		switch (tipo) {
		
		case ARMADIO:
			return "ArmadioIcon";

		case CASSAPANCA:
			return "CassapancaIcon";
			
		case LIBRERIA:
			return "LibreriaIcon";
			
		case SCAFFALE:
			return "ScaffaleIcon";
			
		case TAVOLO:
			return "TavoloIcon";
			
		default:
			return null;
		}
	}
	
	public String getImmagine() {
		switch (tipo) {
		
		case ARMADIO:
			return "Armadio";

		case CASSAPANCA:
			return "Cassapanca";
			
		case LIBRERIA:
			return "Libreria";
			
		case SCAFFALE:
			return "Scaffale";
			
		case TAVOLO:
			return "Tavolo";
			
		default:
			return null;
		}
	}

	public String getId() {
		return id;
	}
	
	public String getIdStanza() {
		return idStanza;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipo;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getH() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public int getW() {
		return w;
	}
	
	public void setW(int w) {
		this.w = w;
	}
}
