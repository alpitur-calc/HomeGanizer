package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.view.MessageView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConfirmSaveInterfaceController implements Initializable{

    @FXML
    private Button btnSi;

    @FXML
    private Button btnNo;

    @FXML
    private Button btnAnnulla;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSi.setOnMouseClicked(handleBtnSiClicked);
		btnNo.setOnMouseClicked(handleBtnNoClicked);
		btnAnnulla.setOnMouseClicked(handleBtnAnnullaClicked);
		
	}
	
	private EventHandler<MouseEvent> handleBtnSiClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			MessageView.showMessageAlert(AlertType.CONFIRMATION, "Successo", "Salvataggio effettuato.");
			Stage thisStage = (Stage) btnSi.getScene().getWindow();
			thisStage.close();
		}
		
	};
	
	private EventHandler<MouseEvent> handleBtnNoClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			MessageView.showMessageAlert(AlertType.CONFIRMATION, "Successo", "Salvataggio non effettuato.");
			Stage thisStage = (Stage) btnNo.getScene().getWindow();
			thisStage.close();
		}
		
	};
	
	private EventHandler<MouseEvent> handleBtnAnnullaClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			MessageView.showMessageAlert(AlertType.CONFIRMATION, "Successo", "Salvataggio annullato.");	
			Stage thisStage = (Stage) btnAnnulla.getScene().getWindow();
			thisStage.close();
		}
		
	};


}
