package application.view;

import application.model.Mobile;
import application.model.Stanza;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Piantina {

	private static Piantina istanza = null;
	private static GraphicsContext gc;
	private static final int l = 50;
	
	public static Piantina getInstance()
	{
		if ( istanza == null )
		{
			istanza = new Piantina();
		}
		return istanza;
	}
	
	private Piantina() 
	{}
	
	public static void disegna(Canvas c, Stanza s)
	{
		c.setHeight(s.getProfondità());
		c.setWidth(s.getLarghezza());
		
		
		
		gc = c.getGraphicsContext2D();
		
		for(int i=0; i< c.getHeight(); i+=Piantina.l)
		{
			for(int j=0; j < c.getWidth(); j+=Piantina.l)
			{
				gc.rect(i, j, Piantina.l, Piantina.l);
			}
		}
		
		
		
		for (Mobile m : s.getMobili())
		{
			gc.fillRect(m.getX()*Piantina.l, m.getY()*Piantina.l, Piantina.l, Piantina.l);
		}
	}
	
}
