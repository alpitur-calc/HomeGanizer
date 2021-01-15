package application.model;

public class Oggetto {
	
	public static final String UTENSILE = "utensile", LIBRO = "libro";
	
	private String id;
	private String nome;
	private String descrizione;
	
	public Oggetto(String id, String nome, String descrizione){
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
	}
		
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
}
