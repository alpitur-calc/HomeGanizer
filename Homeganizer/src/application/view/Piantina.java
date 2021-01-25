package application.view;

import application.controller.MainInterfaceController;
import application.controller.PiantinaMouseController;
import application.model.Mobile;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Piantina {

	private static Piantina istanza = null;
	private static GraphicsContext gc;
	public static final int l = 50;
	private Canvas c;
	public int xPre = 0, yPre = 0;

	public static Piantina getInstance() {
		if (istanza == null) {
			istanza = new Piantina();
		}
		return istanza;
	}

	private Piantina() {

	}

	public void setCanvas(Canvas c) {
		this.c = c;
		c.setOnMousePressed(PiantinaMouseController.getInstance(this).selezionaMobile);
		c.setOnMouseDragged(PiantinaMouseController.getInstance(this).modificaMobile);
	}

	public void disegna() {

		if (MainInterfaceController.getStanzaCorrente() != null) {
			c.setHeight(MainInterfaceController.getStanzaCorrente().getProfondità() * Piantina.l);
			c.setWidth(MainInterfaceController.getStanzaCorrente().getLarghezza() * Piantina.l);

			gc = c.getGraphicsContext2D();
			gc.clearRect(0, 0, c.getWidth(), c.getHeight());
			// Ho eliminato i " * Piantina.l" da "i < c.getHeight()" e "j < c.getWidth()"
			// dal for e va più fluido.
			// Prima era --> i < c.getHeight() * Piantina.l
			// Non so se servivano
			for (int i = 0; i < c.getWidth(); i += Piantina.l) {
				for (int j = 0; j < c.getHeight(); j += Piantina.l) {
					gc.setLineWidth(0.5);
					gc.setStroke(Color.DARKCYAN);
					gc.strokeRect(i, j, Piantina.l, Piantina.l);
				}
			}

			for (Mobile m : MainInterfaceController.getStanzaCorrente().getMobili()) {
				gc.setFill(Color.BLUE);
				gc.setLineWidth(3);
				gc.setStroke(Color.BLACK);
				gc.strokeRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l,
						m.getH() * Piantina.l);
				gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
				Image img = new Image("/resources/mobili/" + m.getImmagine() + ".png");
				gc.drawImage(img, (double) m.getX() * l, (double) m.getY() * l, (double) l, (double) l);
			}
		}
	}

	public void aggiornaPiantina() {
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());

		for (int i = 0; i < c.getWidth(); i += Piantina.l) {
			for (int j = 0; j < c.getHeight(); j += Piantina.l) {
				gc.setLineWidth(0.5);
				gc.setStroke(Color.DARKCYAN);
				gc.strokeRect(i, j, Piantina.l, Piantina.l);
			}
		}

		for (Mobile m : MainInterfaceController.getStanzaCorrente().getMobili()) {
			gc.setFill(Color.BLUE);
			gc.setLineWidth(3);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
			gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
			Image img = new Image("/resources/mobili/" + m.getImmagine() + ".png");
			gc.drawImage(img, (double) m.getX() * l, (double) m.getY() * l, (double) l, (double) l);
		}
	}

	public void evidenziaMobile() {

		gc.setFill(Color.GREEN);
		gc.setLineWidth(3);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(MainInterfaceController.getMobileCorrente().getX() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getY() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getW() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getH() * Piantina.l);
		gc.fillRect(MainInterfaceController.getMobileCorrente().getX() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getY() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getW() * Piantina.l,
				MainInterfaceController.getMobileCorrente().getH() * Piantina.l);
		Image img = new Image(
				"/resources/mobili/" + MainInterfaceController.getMobileCorrente().getImmagine() + ".png");
		gc.drawImage(img, (double) MainInterfaceController.getMobileCorrente().getX() * l,
				(double) MainInterfaceController.getMobileCorrente().getY() * l, (double) l, (double) l);

	}

	public void deselezionaMobile() {
		for (Mobile m : MainInterfaceController.getStanzaCorrente().getMobili()) {
			gc.setFill(Color.BLUE);
			gc.setLineWidth(3);
			gc.setStroke(Color.BLACK);
			gc.strokeRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
			gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
			Image img = new Image("/resources/mobili/" + m.getImmagine() + ".png");
			gc.drawImage(img, (double) m.getX() * l, (double) m.getY() * l, (double) l, (double) l);
		}
	}

	public void clear() {
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
	}

}
