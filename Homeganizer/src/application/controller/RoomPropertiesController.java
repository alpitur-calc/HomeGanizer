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
		
		txtHeight.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	try{
					int h = Integer.parseInt(txtHeight.getText()); 
					if(h>50) { MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Profondità troppo alta. Inserire un numero da 1 a 50!"); }
				}
				catch(NumberFormatException e) {
					MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Prego, inserire un numero intero!");
					Platform.runLater(()->{txtHeight.clear();});
				}   	
		    }
		});
		
		txtWidth.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	try{
					int w = Integer.parseInt(txtWidth.getText()); 
					if(w>50) { MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Larghezza troppo grande. Inserire un numero da 1 a 50!"); }
					
				}
				catch(NumberFormatException e) {
					MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Prego, inserire un numero intero!");
					Platform.runLater(()->{txtWidth.clear();});
				}   	
		    }
		});
	}

    @FXML
    void handleBtnCancelClicked(MouseEvent event) {

    }

    @FXML
    void handleBtnConfirmClicked(MouseEvent event) {
    	Stage thisStage = (Stage) btnConfirm.getScene().getWindow();
    	thisStage.close();
    }

	

}

