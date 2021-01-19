package application.model;

import java.util.LinkedList;

public class Mobile {
	
	public static final String 	ARMADIO = "armadio",
								CASSAPANCA = "cassapanca",
								COMODINO = "comodino",
								CONTAINER = "container",
								CREDENZA = "credenza",
								//DIVANO = "divano",
								//LETTO = "letto",
								LIBRERIA = "libreria",
								TAVOLO = "tavolo",
								SCAFFALE = "scaffale",
								SCRIVANIA = "scrivania",
								VETRINA = "vetrina";
					
	
	//Prendere i vari COUNTER dal DB
	/*
	private static Integer IDCOUNTERSCRIVANIA = 1;
	private static Integer IDCOUNTERTAVOLO = 1;
	private static Integer IDCOUNTERPOLTRONA = 1;
	private static Integer IDCOUNTERSEDIA = 1;
	private static Integer IDCOUNTERLIBRERIA = 1;
	private static Integer IDCOUNTERARMADIO = 1;
	*/
	
	private LinkedList <Oggetto> oggetti;
	private String id;
	private String nome;
	private String tipo;
	//private boolean contenitore;
	
	public Mobile(String nome, String tipo) {
		//initId();
		//initCont();
		this.nome = nome;
		this.tipo = tipo;
		this.oggetti = new LinkedList <Oggetto>();
	}
	/*
	private void initCont() {
		if(this.tipo == Mobile.LETTO || this.tipo == Mobile.DIVANO)
			contenitore = false;
		else
			contenitore = true;
	}
	*/
	/*
	private void initId() {
		switch (tipo) {
		
		case SCRIVANIA:
			this.id = "MSC" + IDCOUNTERSCRIVANIA.toString();
			IDCOUNTERSCRIVANIA++;
			break;
		
		case TAVOLO:
			this.id = "MTA" + IDCOUNTERTAVOLO.toString();
			IDCOUNTERTAVOLO++;
			break;
			
		case POLTRONA:
			this.id = "MPO" + IDCOUNTERPOLTRONA.toString();
			IDCOUNTERPOLTRONA++;
			break;
			
		case SEDIA:
			this.id = "MSE" + IDCOUNTERSEDIA.toString();
			IDCOUNTERSEDIA++;
			break;
			
		case LIBRERIA:
			this.id = "MLI" + IDCOUNTERLIBRERIA.toString();
			IDCOUNTERLIBRERIA++;
			break;
			
		case ARMADIO:
			this.id = "MAR" + IDCOUNTERARMADIO.toString();
			IDCOUNTERARMADIO++;
			break;
			
		default:
			break;
		}
	}
	*/
	
	public void aggiungiOggetto(String nome, String descrizione, String tipo){
		//if(this.contenitore == true)
		oggetti.add(new Oggetto(nome, descrizione, tipo));
		//else
			//System.out.println("NON PUO' CONTENERE OGGETTI");
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

}
