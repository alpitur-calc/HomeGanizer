package application.view;

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
	private Mobile mobileSelezionato;

	public static Piantina getInstance() {
		if (istanza == null) {
			istanza = new Piantina();
		}
		return istanza;
	}

	private Piantina() {
		
		
		
	}

	public void setCanvas(Canvas c)
	{
		this.c = c;
		c.setOnMouseClicked(spostaMobile);
	}
	
	public void disegna(Stanza s) {

		
		c.setHeight(s.getProfondità() * Piantina.l);
		c.setWidth(s.getLarghezza() * Piantina.l);

		gc = c.getGraphicsContext2D();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());

		for (int i = 0; i < c.getHeight() * Piantina.l; i += Piantina.l) {
			for (int j = 0; j < c.getWidth() * Piantina.l; j += Piantina.l) {
				gc.setLineWidth(0.5);
				gc.setStroke(Color.DARKCYAN);
				gc.strokeRect(i, j, Piantina.l, Piantina.l);
			}
		}

		for (Mobile m : s.getMobili()) {
			gc.setFill(Color.BLUE);
			gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);
		}
	}

	public void evidenziaMobile(Stanza s, Mobile m) {
		disegna(s);
		gc.setFill(Color.GREEN);
		gc.fillRect(m.getX() * Piantina.l, m.getY() * Piantina.l, m.getW() * Piantina.l, m.getH() * Piantina.l);

	}
	
	public EventHandler<MouseEvent> spostaMobile = new EventHandler<MouseEvent>()
	{

		@Override
		public void handle(MouseEvent e) {
			
			System.out.println(e.getX() - (e.getX()%Piantina.l) + e.getY() - (e.getY()%Piantina.l));
			
		}
		
	};
	
	
	

}
