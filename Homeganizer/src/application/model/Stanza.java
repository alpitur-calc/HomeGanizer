package application.model;

import java.util.ArrayList;
import java.util.LinkedList;

import application.view.MessageView;
import javafx.scene.control.Alert.AlertType;

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
		mobili.add(new Mobile(this.id,nome, tipo));
		
		if (inserito())
			aggiornaMatrice();
		else {
			MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Non c'è più spacyo");
			rimuoviMobile(mobili.getLast().getId());
		}
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

	public boolean traslabile(Mobile m, int x, int y) {
		if(x < 0 || y < 0)
			return false;
		for(int l = 0; l < m.getW(); l++)
			for(int p = 0; p < m.getH(); p++) 
				if (!inMatrice(x + l, y + p) || (matrice[x + l][y + p] != null && matrice[x + l][y + p] != m.getId())) 
					return false;		
		return true;
	}
	
	public boolean allargabile(Mobile m, int x, int y) {
		for(int l = 0; l < m.getW() + x; l++)
			for(int p = 0; p < m.getH() + y; p++) 
				if (!inMatrice(m.getX() + l, m.getY() + p) || (matrice[m.getX() + l][m.getY() + p] != null && matrice[m.getX() + l][m.getY() + p] != m.getId())) 
					return false;					
		return true;
		
	}
	
	private boolean inMatrice(int x, int y) {
		if(x < larghezza && y < profondità)
			return true;
		return false;
		
	}

	public void aggiornaMatrice() {
		for (int i = 0; i < larghezza; i++)
			for (int k = 0; k < profondità; k++)
				matrice[i][k] = null;

		for (Mobile m : mobili) {
			for (int l = 0; l < m.getW(); l++)
				for (int p = 0; p < m.getH(); p++)
					if(inMatrice(m.getX() + l, m.getY() + p))
						matrice[m.getX() + l][m.getY() + p] = m.getId();	
			
		}
		 
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
