package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.view.MessageView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
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
					Integer.parseInt(txtHeight.getText()); 
				}
				catch(NumberFormatException e) {
					MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Prego, inserire un numero intero!");
					txtHeight.setText("");
				}   	
		    }
		});
		
		txtWidth.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	try{
					Integer.parseInt(txtWidth.getText()); 
				}
				catch(NumberFormatException e) {
					MessageView.showMessageAlert(AlertType.ERROR, "Errore", "Prego, inserire un numero intero!");
					txtWidth.setText("");
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

