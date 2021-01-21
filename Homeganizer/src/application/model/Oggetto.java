package application.model;

public class Oggetto {
	
	public static final String 	BOTTIGLIA = "bottiglia",
								CONTENITORE = "contenitore",
								DISCO = "disco",
								ELETTRONICA = "elettronica",
								//FOTO = "foto",
								//GIOIELLI = "gioielli",
								LIBRO = "libro",
								//MEDICINA = "medicina",
								//STATUA = "statua",
								UTENSILE = "utensile";
								
	
	//Prendere i vari COUNTER dal DB
	
	private static Integer 	IDCOUNTERBO = 1,
							IDCOUNTERCO = 1,
							IDCOUNTERDI = 1,
							IDCOUNTEREL = 1, 
							IDCOUNTERLI = 1,
							IDCOUNTERUT = 1;
	
	private String id;
	private String nome;
	private String descrizione;
	private String tipo;
	
	public Oggetto(String nome, String descrizione, String tipo){
		initId();			
		this.nome = nome;
		this.descrizione = descrizione;
		this.tipo = tipo;
	}
	
	
	private void initId() {
		switch (tipo) {
		
		case BOTTIGLIA:
			this.id = "OBO" + IDCOUNTERBO.toString();
			IDCOUNTERBO++;
			break;
			
		case CONTENITORE:
			this.id = "OCO" + IDCOUNTERCO.toString();
			IDCOUNTERCO++;
			break;
			
		case DISCO:
			this.id = "ODI" + IDCOUNTERDI.toString();
			IDCOUNTERDI++;
			break;
			
		case ELETTRONICA:
			this.id = "OEL" + IDCOUNTEREL.toString();
			IDCOUNTEREL++;
			break;
				
		case LIBRO:
			this.id = "OLI" + IDCOUNTERLI.toString();
			IDCOUNTERLI++;
			break;
			
		case UTENSILE:
			this.id = "OUT" + IDCOUNTERUT.toString();
			IDCOUNTERUT++;
			break;
			
		default:
			break;
		}
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
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getTipo() {
		return tipo;
	}
}
