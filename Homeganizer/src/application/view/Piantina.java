package application.view;

import application.model.Mobile;
import application.model.Stanza;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
		
		c.setHeight(s.getProfondità()*Piantina.l);
		c.setWidth(s.getLarghezza()*Piantina.l); 
		
		
		
		gc = c.getGraphicsContext2D();
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		
		for(int i=0; i< c.getHeight()*Piantina.l; i+=Piantina.l)
		{
			for(int j=0; j < c.getWidth()*Piantina.l; j+=Piantina.l)
			{	
				gc.setLineWidth(0.5);
				gc.setStroke(Color.DARKGREEN);
				gc.strokeRect(i, j, Piantina.l, Piantina.l);
			}
		}
		
		
		
		for (Mobile m : s.getMobili())
		{
			gc.setFill(Color.BLUE);
			gc.fillRect(m.getX()*Piantina.l, m.getY()*Piantina.l, Piantina.l, Piantina.l);
		}
	}
	

    public static void evidenziaMobile(Canvas c, Stanza s,Mobile m)
    {
                Piantina .disegna(c, s);
                gc.setFill(Color.RED);
                gc.fillRect(m.getX()*Piantina.l, m.getY()*Piantina.l, Piantina.l, Piantina.l);

    }

}
	

