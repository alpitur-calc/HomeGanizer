package application.model;

import javafx.scene.layout.Pane;

public class RoomPane extends Pane{
	
	private String idstanza="";
	public RoomPane(String id) {
		super();
		idstanza = id;
	}


	public String getIdStanza() {
		return this.idstanza;
	}
	
}
