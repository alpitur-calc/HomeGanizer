package application.view;

import application.controller.MainInterfaceController;
import application.model.Mobile;
import application.model.Stanza;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Piantina {

	private static Piantina istanza = null;
	private static GraphicsContext gc;
	private static final int l = 50;
	private Canvas c;
	private Stanza StanzaCorrente;
	private Mobile mobileSelezionato;

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
		c.setOnMouseClicked(selezionaMobile);
	}

	

	public void disegna() {

		c.setHeight(MainInterfaceController.getStanzaCorrente().getProfondità() * Piantina.l);
		c.setWidth(MainInterfaceController.getStanzaCorrente().getLarghezza() * Piantina.l);

		gc = c.getGraphicsContext2D();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());

		for (int i = 0; i < c.getHeight() * Piantina.l; i += Piantina.l) {
			for (int j = 0; j < c.getWidth() * Piantina.l; j += Piantina.l) {
				gc.setLineWidth(0.5);
				gc.setStroke(Color.DARKCYAN);
				gc.strokeRect(i, j, Piantina.l, Piantina.l);
			}
		}

		for (Mobile m : MainInterfaceController.getStanzaCorrente().getMobili()) {
			gc.setFill(Color.BLUE);
			gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
		}
	}

	public void evidenziaMobile() {
		disegna();
		gc.setFill(Color.GREEN);
		gc.fillRect(MainInterfaceController.getMobileCorrente().getX() * Piantina.l, MainInterfaceController.getMobileCorrente().getY() * Piantina.l, MainInterfaceController.getMobileCorrente().getW() * Piantina.l, MainInterfaceController.getMobileCorrente().getH() * Piantina.l);

	}

	public EventHandler<MouseEvent> spostaMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {

			int xPiantina = (int) (e.getX() - (e.getX() % Piantina.l)) / Piantina.l;
			int yPiantina = (int) (e.getY() - (e.getY() % Piantina.l)) / Piantina.l;

			if (MainInterfaceController.getStanzaCorrente().getMatrice()[xPiantina][yPiantina] == null) {

			}

		}

	};

	public EventHandler<MouseEvent> selezionaMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {

			int xPiantina = (int) (e.getX() - (e.getX() % Piantina.l)) / Piantina.l;
			int yPiantina = (int) (e.getY() - (e.getY() % Piantina.l)) / Piantina.l;

			System.out.println(xPiantina + ", " + yPiantina);

			if (MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina) != null) {
				MainInterfaceController.setMobileCorrente(
						MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina));

				evidenziaMobile();

				System.out.println(MainInterfaceController.getMobileCorrente().getId());
			}
			// finchè non si clicca su un mobile o su una stanza la current room e il
			// MobileCorrente sono null, tranne quando è stata fatta la ricerca di un
			// oggetto

		}

	};

}
