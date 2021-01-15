package application.model;

import java.util.ArrayList;

public class Mobile {
	
	public static final String 	SCRIVANIA = "scrivania", 
								TAVOLO = "tavolo", 
								POLTRONA = "poltrona",
								SEDIA = "sedia",
								LIBRERIA = "libreria",
								ARMADIO = "armadio";
	
	private ArrayList<Oggetto> listaOggetti;
	private String id;
	private String nome;
	
	public Mobile(String nome, String id){
		this.id = id;
		this.nome = nome;
	}
	
	public void AggiungiOggetto(String id, String nome, String descrizione){
		listaOggetti.add(new Oggetto(id, nome, descrizione));
	}
	
	public void RimuoviOggetto(String id) {
		for (Oggetto i : listaOggetti) {
			if(i.getId().equals(id)) {
				listaOggetti.remove(i);
			}
		}
	}
		
	public ArrayList<Oggetto> getListaOggetti() {
		return listaOggetti;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
