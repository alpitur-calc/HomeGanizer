package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Stanza {

	private String id;
	private String nome;
	private int larghezza;
	private int profondità;
	private String proprietario;
	private LinkedList<Mobile> mobili;
	private ArrayList<String> whitelisted;
	private String matrice[][];

	private static Integer IDCOUNTER = 1;

	public Stanza(String nome, String proprietario, int larghezza, int profondità) {
		initId();
		this.nome = nome;
		this.proprietario = proprietario;
		this.larghezza = larghezza;
		this.profondità = profondità;
		this.mobili = new LinkedList<Mobile>();
		this.matrice = new String[larghezza][profondità];

		for (int i = 0; i < larghezza; i++)
			for (int k = 0; k < profondità; k++) {
				matrice[i][k] = null;
			}
	}

	private void initId() {
		this.id = "S" + IDCOUNTER.toString();
		IDCOUNTER++;
	}
	

	public void aggiungiMobile(String nome, String tipo) {

		mobili.add(new Mobile(nome, tipo));
		
		if (inserito())
			aggiornaMatrice();
		else
			System.out.println("Non c'è più spacyo");
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

	public boolean inserito() {
		for (int i = 0; i < larghezza; i++) {
			for (int k = 0; k < profondità; k++) {
				if (matrice[i][k] == null) {
					mobili.getLast().setX(i);
					mobili.getLast().setY(k);
					return true;
				}
			}
		}
		return false;
	}

	public Mobile getMobileSelezionato(int x, int y) {
		for(Mobile m : mobili) {
			if(matrice[x][y] == m.getId())
				return m;
		}
		return null;
	}
	
	// BOZZISSIMA
	public boolean traslazione(Mobile m, int x, int y) {
		boolean sicurow = false;
		boolean sicuroh = false;
		
		if (matrice[x][y] == null) {
			for (int l = x; l < m.getW(); l++) {
				if ((matrice[x + l][y] == null) && (l < larghezza))
					sicurow = true;
			}
			for (int p = y; p < m.getH(); p++) {
				if ((matrice[x][y + p] == null) && (p < profondità))
					sicuroh = true;
			}
			if (sicurow && sicuroh)
				return true;
		}
		return false;
	}

	public void aggiornaMatrice() {

		for (int i = 0; i < larghezza; i++)
			for (int k = 0; k < profondità; k++)
				matrice[i][k] = null;

		for (Mobile m : mobili) {
			if (m.getW() > 1)
				for (int i = 0; i < m.getW(); i++)
					matrice[m.getX() + i][m.getY()] = m.getId();

			if (m.getH() > 1)
				for (int i = 0; i < m.getH(); i++)
					matrice[m.getX()][m.getY() + i] = m.getId();
			else
				matrice[m.getX()][m.getY()] = m.getId();
		}

		/*
		 * //Stampa per prova for(int i = 0; i < larghezza; i++) { for(int k = 0; k <
		 * profondità; k++) System.out.print(matrice[i][k] + " "); System.out.println();
		 * } System.out.println();
		 */
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

	public int getProfondità() {
		return profondità;
	}

	public String getProprietario() {
		return proprietario;
	}

	public String[][] getMatrice() {
		return matrice;
	}

}
