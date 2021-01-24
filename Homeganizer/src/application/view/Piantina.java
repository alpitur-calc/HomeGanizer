package application.view;

import application.controller.MainInterfaceController;
import application.model.Mobile;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Piantina {

	private static Piantina istanza = null;
	private static GraphicsContext gc;
	private static final int l = 50;
	private Canvas c;

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
		//c.setOnMouseDragged(spostaMobile); -> rinominato in modificaMobile perchè ora lo ridimensiona pure ma se trovi un nome migliore metti quello
		c.setOnMouseDragged(modificaMobile);
	}

	

	public void disegna() {

		c.setHeight(MainInterfaceController.getStanzaCorrente().getProfondità() * Piantina.l);
		c.setWidth(MainInterfaceController.getStanzaCorrente().getLarghezza() * Piantina.l);

		gc = c.getGraphicsContext2D();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());

		//Ho eliminato i " * Piantina.l" da "i < c.getHeight()" e "j < c.getWidth()" dal for e va più fluido. 
		//Prima era --> i < c.getHeight() * Piantina.l
		//Non so se servivano
		for (int i = 0; i < c.getHeight()* Piantina.l; i += Piantina.l) {
			for (int j = 0; j < c.getWidth()* Piantina.l; j += Piantina.l) {
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
	
	public void aggiornaPiantina() {
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		
		for (int i = 0; i < c.getHeight(); i += Piantina.l) {
			for (int j = 0; j < c.getWidth(); j += Piantina.l) {
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
		for (Mobile m : MainInterfaceController.getStanzaCorrente().getMobili()) {
			gc.setFill(Color.BLUE);
			gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
		}
		gc.setFill(Color.GREEN);
		gc.fillRect(MainInterfaceController.getMobileCorrente().getX() * Piantina.l, MainInterfaceController.getMobileCorrente().getY() * Piantina.l, MainInterfaceController.getMobileCorrente().getW() * Piantina.l, MainInterfaceController.getMobileCorrente().getH() * Piantina.l);

	}
	
	public void deselezionaMobile() {
		gc.setFill(Color.BLUE);
		gc.fillRect(MainInterfaceController.getMobileCorrente().getX() * Piantina.l, MainInterfaceController.getMobileCorrente().getY() * Piantina.l, MainInterfaceController.getMobileCorrente().getW() * Piantina.l, MainInterfaceController.getMobileCorrente().getH() * Piantina.l);

	}

	public EventHandler<MouseEvent> modificaMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {

			if (MainInterfaceController.getStanzaCorrente() != null) {
				
				int xPiantina = (int) (e.getX() - (e.getX() % Piantina.l)) / Piantina.l;
				int yPiantina = (int) (e.getY() - (e.getY() % Piantina.l)) / Piantina.l;
								
				if(MainInterfaceController.getMobileCorrente() != null) {
					
					if(MainInterfaceController.getStanzaCorrente().traslazione(MainInterfaceController.getMobileCorrente(), xPiantina, yPiantina)) {
						
						if(e.getButton() == MouseButton.SECONDARY) {
							MainInterfaceController.getMobileCorrente().setW(Math.abs( xPiantina - MainInterfaceController.getMobileCorrente().getX() ) +1 );
							MainInterfaceController.getMobileCorrente().setH(Math.abs( yPiantina - MainInterfaceController.getMobileCorrente().getY() ) +1 );
						}
						
						else {
						
						MainInterfaceController.getMobileCorrente().setX(xPiantina);
						MainInterfaceController.getMobileCorrente().setY(yPiantina);
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

				if(MainInterfaceController.getStanzaCorrente().getMatrice()[xPiantina][yPiantina] == null) {
					
					MainInterfaceController.setMobileCorrente(null);
					deselezionaMobile();
				}
				
				if (MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina) != null) {
					
					MainInterfaceController.setMobileCorrente(MainInterfaceController.getStanzaCorrente().getMobileSelezionato(xPiantina, yPiantina));
					evidenziaMobile();
				}
			}
		}
		// finchè non si clicca su un mobile o su una stanza la current room e il
		// MobileCorrente sono null, tranne quando è stata fatta la ricerca di un
		// oggetto
	};
	
	

}
