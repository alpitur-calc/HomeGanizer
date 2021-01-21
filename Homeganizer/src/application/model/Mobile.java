package application.model;

import java.util.LinkedList;

public class Mobile {
	
	public static final String 	ARMADIO = "armadio",
								CASSAPANCA = "cassapanca",
								//COMODINO = "comodino",
								//CONTAINER = "container",
								//CREDENZA = "credenza",
								LIBRERIA = "libreria",
								TAVOLO = "tavolo",
								//SCAFFALE = "scaffale",
								SCRIVANIA = "scrivania";
								//VETRINA = "vetrina";
					
	
	//Prendere i vari COUNTER dal DB
	
	private static Integer IDCOUNTERARMADIO = 1;
	private static Integer IDCOUNTERLIBRERIA = 1;
	private static Integer IDCOUNTERSCRIVANIA = 1;
	private static Integer IDCOUNTERTAVOLO = 1;;
	
	
	private String id;
	private String nome;
	private String tipo;
	private int x,y;
	private LinkedList <Oggetto> oggetti;
	
	public Mobile(String nome, String tipo) {
		initId();
		this.x = 0;
		this.y = 0;
		this.nome = nome;
		this.tipo = tipo;
		this.oggetti = new LinkedList <Oggetto>();
	}
	
	
	private void initId() {
		switch (tipo) {
		
		case ARMADIO:
			this.id = "MAR" + IDCOUNTERARMADIO.toString();
			IDCOUNTERARMADIO++;
			break;
			
		case LIBRERIA:
			this.id = "MLI" + IDCOUNTERLIBRERIA.toString();
			IDCOUNTERLIBRERIA++;
			break;
		
		case SCRIVANIA:
			this.id = "MSC" + IDCOUNTERSCRIVANIA.toString();
			IDCOUNTERSCRIVANIA++;
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
		oggetti.add(new Oggetto(nome, descrizione, tipo));
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

	public String getId() {
		return id;
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
}
