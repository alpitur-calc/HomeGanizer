package application.model;

public class Oggetto {
	
	public static final String 	UTENSILE = "utensile",
								LIBRO = "libro";
	
	//Prendere i vari COUNTER dal DB
	/*
	private static Integer 	IDCOUNTERU = 1,
							IDCOUNTERL = 1;
	*/
	
	private String id;
	private String nome;
	private String descrizione;
	private String tipo;
	
	public Oggetto(String nome, String descrizione, String tipo){
		//initId();			
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
	}
	
	/*
	private void initId() {
		switch (tipo) {
		
		case UTENSILE:
			this.id = "OU" + IDCOUNTERU.toString();
			IDCOUNTERU++;
			break;
		
		case LIBRO:
			this.id = "OL" + IDCOUNTERL.toString();
			IDCOUNTERL++;
			break;
			
		default:
			break;
		}
	}
	*/
	
	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}
	
	public String getTipo() {
		return tipo;
	}
}
