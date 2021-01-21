package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Stanza {

	private String id;
	private String nome;
	private int larghezza;
	private int profonditā;
	private String proprietario;
	private LinkedList<Mobile> mobili;
	private ArrayList<String> whitelisted;
	private String matrice[][];

	private static Integer IDCOUNTER = 1;
	
	public Stanza(String nome, String proprietario, int larghezza, int profonditā) {
		initId();
		this.nome = nome;
		this.proprietario = proprietario;
		this.larghezza = larghezza;
		this.profonditā = profonditā;
		this.mobili = new LinkedList<Mobile>();
		this.matrice = new String[larghezza][profonditā];

		for (Mobile m : mobili) {
			matrice[m.getX()][m.getY()] = m.getId();
		}
	}
	
	private void initId() {
		this.id = "S" + IDCOUNTER.toString();
		IDCOUNTER++;
	}

	public void aggiungiMobile(String nome, String tipo) {
		mobili.add(new Mobile(nome, tipo));
	}
	

	public void rimuoviMobile(String id) {
		for (Mobile i : mobili) {
			if (i.getId().equals(id)) {
				mobili.remove(i);
			}
		}
	}

	public boolean isWhitelisted(String id) {
		for (String p : whitelisted) {
			if (p.equals(id)) {
				return true;
			}
		}

		return false;
	}

	public LinkedList<Mobile> getMobili() {
		return mobili;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public int getProfonditā() {
		return profonditā;
	}

	public String getProprietario() {
		return proprietario;
	}

}
