package application.controller;

import application.view.Piantina;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PiantinaMouseController {
	
	private static PiantinaMouseController istanza = null;
	private Piantina p;
	
	public static PiantinaMouseController getInstance(Piantina p) {
		if (istanza == null) {
			istanza = new PiantinaMouseController(p);
		}
		return istanza;
	}
	
	public PiantinaMouseController(Piantina p) {
		this.p = p;
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
								if (xPiantina > p.xPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 1, 0)) {
										MainInterfaceController.getMobileCorrente().setW(
												Math.abs(xPiantina - MainInterfaceController.getMobileCorrente().getX())
														+ 1);
										p.xPre = xPiantina;
									}
								} else if (xPiantina < p.xPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), -1, 0)) {
										MainInterfaceController.getMobileCorrente().setW(
												Math.abs(xPiantina - MainInterfaceController.getMobileCorrente().getX())
														+ 1);
										p.xPre = xPiantina;
									}
								}
								if (yPiantina > p.yPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 0, 1)) {
										MainInterfaceController.getMobileCorrente().setH(
												Math.abs(yPiantina - MainInterfaceController.getMobileCorrente().getY())
														+ 1);
										p.yPre = yPiantina;
									}
								} else if (yPiantina < p.yPre) {
									if (MainInterfaceController.getStanzaCorrente()
											.allargabile(MainInterfaceController.getMobileCorrente(), 0, -1)) {
										MainInterfaceController.getMobileCorrente().setH(
												Math.abs(yPiantina - MainInterfaceController.getMobileCorrente().getY())
														+ 1);
										p.yPre = yPiantina;
									}
								}
							}
						}

						else if (e.getButton() == MouseButton.PRIMARY) {
							System.out.println(xPiantina + ", " + yPiantina);
							// Traslazione più dinamica e precisa, avevo sottovalutato la matematica
							if (((xPiantina != p.xPre) || (yPiantina != p.yPre)) && MainInterfaceController
									.getStanzaCorrente().traslabile(MainInterfaceController.getMobileCorrente(),
											MainInterfaceController.getMobileCorrente().getX() + (xPiantina - p.xPre),
											MainInterfaceController.getMobileCorrente().getY() + (yPiantina - p.yPre))) {
								MainInterfaceController.getMobileCorrente()
										.setX(MainInterfaceController.getMobileCorrente().getX() + (xPiantina - p.xPre));
								MainInterfaceController.getMobileCorrente()
										.setY(MainInterfaceController.getMobileCorrente().getY() + (yPiantina - p.yPre));
								p.yPre = yPiantina;
								p.xPre = xPiantina;
							} else if (xPiantina > p.xPre
									&& (yPiantina >= MainInterfaceController.getStanzaCorrente().getProfondità()
											|| yPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX() + 1,
										MainInterfaceController.getMobileCorrente().getY())) {
									MainInterfaceController.getMobileCorrente()
											.setX(MainInterfaceController.getMobileCorrente().getX() + 1);
									p.xPre = xPiantina;
								}

							} else if (xPiantina < p.xPre
									&& (yPiantina >= MainInterfaceController.getStanzaCorrente().getProfondità()
											|| yPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX() - 1,
										MainInterfaceController.getMobileCorrente().getY())) {
									MainInterfaceController.getMobileCorrente()
											.setX(MainInterfaceController.getMobileCorrente().getX() - 1);
									p.xPre = xPiantina;
								}

							} else if (yPiantina > p.yPre
									&& (xPiantina >= MainInterfaceController.getStanzaCorrente().getLarghezza()
											|| xPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX(),
										MainInterfaceController.getMobileCorrente().getY() + 1)) {
									MainInterfaceController.getMobileCorrente()
											.setY(MainInterfaceController.getMobileCorrente().getY() + 1);
									p.yPre = yPiantina;
								}

							} else if (yPiantina < p.yPre
									&& (xPiantina >= MainInterfaceController.getStanzaCorrente().getLarghezza()
											|| xPiantina < 0)) {
								if (MainInterfaceController.getStanzaCorrente().traslabile(
										MainInterfaceController.getMobileCorrente(),
										MainInterfaceController.getMobileCorrente().getX(),
										MainInterfaceController.getMobileCorrente().getY() - 1)) {
									MainInterfaceController.getMobileCorrente()
											.setY(MainInterfaceController.getMobileCorrente().getY() - 1);
									p.yPre = yPiantina;
								}
							}

						}
						MainInterfaceController.getStanzaCorrente().aggiornaMatrice();
						p.aggiornaPiantina();
						p.evidenziaMobile();
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
					p.deselezionaMobile();
				}

				if (MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina) != null) {

					MainInterfaceController.setMobileCorrente(
							MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina));

					p.xPre = xPiantina;
					p.yPre = yPiantina;
					p.deselezionaMobile();
					p.evidenziaMobile();
				}
			}
		}
	};
}
