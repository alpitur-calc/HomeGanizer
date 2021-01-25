package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.view.MessageView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RoomPropertiesController implements Initializable {

    @FXML
    private TextField txtRoomName;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtWidth;

    @FXML
    private Button btnConfirm;

    @FXML
    private Button btnCancel;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {

	}

    @FXML
    void handleBtnCancelClicked(MouseEvent event) {
    	MainInterfaceController.ConfermaCreazioneStanza= false;
    	Stage thisStage = (Stage) btnConfirm.getScene().getWindow();
    	thisStage.close();
    }

    @FXML
    void handleBtnConfirmClicked(MouseEvent event) {
    	int w =0, h=0;
    	boolean conferma = true;
    	try{
			h = Integer.parseInt(txtHeight.getText()); 
			w = Integer.parseInt(txtWidth.getText()); 
			if((h<=0 || h>50) || (w<=0 || w>50)) 
			{
				MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Dimensioni troppo grandi. Inserire un numero da 1 a 50!"); 
				conferma = false;
			}		
		}
		catch(NumberFormatException e) {
			MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Prego, inserire un numeri interi nelle dimensioni!");
			conferma = false;
		}  
    	
    	if(conferma) {
	    	MainInterfaceController.ConfermaCreazioneStanza=true;
	    	Stage thisStage = (Stage) btnConfirm.getScene().getWindow();
	    	thisStage.close();
    	}
    }

}

