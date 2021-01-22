package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.Oggetto;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class OggettoInterfaceController implements Initializable{


    @FXML
    private TextField txtNome;

    @FXML
    private ComboBox<String> cmbTipo;

    @FXML
    private Button btnConferma;

    @FXML
    private Button btnAnnulla;

    @FXML
    private TextArea txtDescrizione;

    @FXML
    void handleBtnConfermaClicked(MouseEvent event) {}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbTipo.getItems().add(Oggetto.BOTTIGLIA);
		cmbTipo.getItems().add(Oggetto.CONTENITORE);
		cmbTipo.getItems().add(Oggetto.DISCO);	
		cmbTipo.getItems().add(Oggetto.ELETTRONICA);
		cmbTipo.getItems().add(Oggetto.LIBRO);
		cmbTipo.getItems().add(Oggetto.UTENSILE);
		
		btnConferma.setOnMouseClicked(handleBtnConfermaClicked);
	}
	
	private EventHandler<MouseEvent> handleBtnConfermaClicked = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent event) {
			Stage thisStage = (Stage) btnConferma.getScene().getWindow();
	    	thisStage.close();
		}
		
	};
}
