package application.view;

import application.controller.MainInterfaceController;
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
	private static final int l = 50;
	private Canvas c;
	private int xPre = 0, yPre = 0;

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
		c.setOnMousePressed(selezionaMobile);
		// c.setOnMouseDragged(spostaMobile); -> rinominato in modificaMobile perchè ora
		// lo ridimensiona pure ma se trovi un nome migliore metti quello
		c.setOnMouseDragged(modificaMobile);
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

	public EventHandler<MouseEvent> modificaMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {

			if (MainInterfaceController.getStanzaCorrente() != null) {

				int xPiantina = (int) (e.getX() - (e.getX() % Piantina.l)) / Piantina.l;
				int yPiantina = (int) (e.getY() - (e.getY() % Piantina.l)) / Piantina.l;

				if (MainInterfaceController.getMobileCorrente() != null) {

					if (!(Math.abs(xPiantina - MainInterfaceController.getMobileCorrente().getX())
							+ 1 > MainInterfaceController.getStanzaCorrente().getLarghezza()
							|| (Math.abs(yPiantina - MainInterfaceController.getMobileCorrente().getY())
									+ 1) > MainInterfaceController.getStanzaCorrente().getProfondità())) {

						if (e.getButton() == MouseButton.SECONDARY) {
							if (xPiantina >= MainInterfaceController.getMobileCorrente().getX()
									&& yPiantina >= MainInterfaceController.getMobileCorrente().getY()) {
								// Allargamento più dinamico
								if (xPiantina > xPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 1, 0)) {
										MainInterfaceController.getMobileCorrente().setW(
												Math.abs(xPiantina - MainInterfaceController.getMobileCorrente().getX())
														+ 1);
										xPre = xPiantina;
									}
								} else if (xPiantina < xPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), -1, 0)) {
										MainInterfaceController.getMobileCorrente().setW(
												Math.abs(xPiantina - MainInterfaceController.getMobileCorrente().getX())
														+ 1);
										xPre = xPiantina;
									}
								}
								if (yPiantina > yPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 0, 1)) {
										MainInterfaceController.getMobileCorrente().setH(
												Math.abs(yPiantina - MainInterfaceController.getMobileCorrente().getY())
														+ 1);
										yPre = yPiantina;
									}
								} else if (yPiantina < yPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 0, -1)) {
										MainInterfaceController.getMobileCorrente().setH(
												Math.abs(yPiantina - MainInterfaceController.getMobileCorrente().getY())
														+ 1);
										yPre = yPiantina;
									}
								}
							}
						}

						else if (e.getButton() == MouseButton.PRIMARY) {
							System.out.println(xPiantina + ", " + yPiantina);
							// Traslazione più dinamica e precisa, avevo sottovalutato la matematica
							if (((xPiantina != xPre) || (yPiantina != yPre)) && MainInterfaceController
									.getStanzaCorrente().traslabile(MainInterfaceController.getMobileCorrente(),
											MainInterfaceController.getMobileCorrente().getX() + (xPiantina - xPre),
											MainInterfaceController.getMobileCorrente().getY() + (yPiantina - yPre))) {
								MainInterfaceController.getMobileCorrente()
										.setX(MainInterfaceController.getMobileCorrente().getX() + (xPiantina - xPre));
								MainInterfaceController.getMobileCorrente()
										.setY(MainInterfaceController.getMobileCorrente().getY() + (yPiantina - yPre));
								yPre = yPiantina;
								xPre = xPiantina;
							} else if (xPiantina > xPre
									&& (yPiantina >= MainInterfaceController.getStanzaCorrente().getProfondità()
											|| yPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX() + 1,
										MainInterfaceController.getMobileCorrente().getY())) {
									MainInterfaceController.getMobileCorrente()
											.setX(MainInterfaceController.getMobileCorrente().getX() + 1);
									xPre = xPiantina;
								}

							} else if (xPiantina < xPre
									&& (yPiantina >= MainInterfaceController.getStanzaCorrente().getProfondità()
											|| yPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX() - 1,
										MainInterfaceController.getMobileCorrente().getY())) {
									MainInterfaceController.getMobileCorrente()
											.setX(MainInterfaceController.getMobileCorrente().getX() - 1);
									xPre = xPiantina;
								}

							} else if (yPiantina > yPre
									&& (xPiantina >= MainInterfaceController.getStanzaCorrente().getLarghezza()
											|| xPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX(),
										MainInterfaceController.getMobileCorrente().getY() + 1)) {
									MainInterfaceController.getMobileCorrente()
											.setY(MainInterfaceController.getMobileCorrente().getY() + 1);
									yPre = yPiantina;
								}

							} else if (yPiantina < yPre
									&& (xPiantina >= MainInterfaceController.getStanzaCorrente().getLarghezza()
											|| xPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX(),
										MainInterfaceController.getMobileCorrente().getY() - 1)) {
									MainInterfaceController.getMobileCorrente()
											.setY(MainInterfaceController.getMobileCorrente().getY() - 1);
									yPre = yPiantina;
								}
							}

						}
						MainInterfaceController.getStanzaCorrente().aggiornaMatrice();
						aggiornaPiantina();
						evidenziaMobile();
					}
				}
			}
		}
	};

	public EventHandler<MouseEvent> selezionaMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {

			if (MainInterfaceController.getStanzaCorrente() != null) {

				int xPiantina = (int) (e.getX() - (e.getX() % Piantina.l)) / Piantina.l;
				int yPiantina = (int) (e.getY() - (e.getY() % Piantina.l)) / Piantina.l;

				if (MainInterfaceController.getStanzaCorrente().getMatrice()[xPiantina][yPiantina] == null) {

					MainInterfaceController.setMobileCorrente(null);
					deselezionaMobile();
				}

				if (MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina) != null) {

					MainInterfaceController.setMobileCorrente(
							MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina));

					xPre = xPiantina;
					yPre = yPiantina;
					deselezionaMobile();
					evidenziaMobile();
				}
			}
		}
		// finchè non si clicca su un mobile o su una stanza la current room e il
		// MobileCorrente sono null, tranne quando è stata fatta la ricerca di un
		// oggetto
	};

	public void clear() {
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
	}

}
