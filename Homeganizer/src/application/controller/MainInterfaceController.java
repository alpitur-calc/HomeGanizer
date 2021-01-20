package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.SceneHandler;
import application.model.RoomHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainInterfaceController implements Initializable {

	private Stage roomProp;
	
    @FXML
    private ListView<Pane> lstRooms;

    @FXML
    private ListView<?> lstFurniture;

    @FXML
    private Menu mnuAccount;

    @FXML
    private MenuItem mtmLogOut;

    @FXML
    private Menu mnuAzioni;

    @FXML
    private Menu mnuAbout;

    @FXML
    private Menu mnuSpotlight;

    @FXML
    private TextField txtSpotlight;

    @FXML
    private Canvas cnvRoom;

    @FXML
    private TextArea txtObjectDescription;
    
    	
    private Button btnAddRoom;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	roomProp = new Stage();
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/roomPropertiesInterface.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			roomProp.setTitle("Inserire dati stanza");
			roomProp.setScene(scene);
			
		} catch (IOException e1) { e1.printStackTrace(); }
    	
		Pane p = new Pane();
    	btnAddRoom = new Button();
    	btnAddRoom.setPrefSize(225.0, 35.0);
    	
    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddImage.png"));
    	ImageView imgv= new ImageView(buttonImg);
    	btnAddRoom.setGraphic(imgv);
    	btnAddRoom.setOnMousePressed(handleBtnAddRoom);
    	
    	p.getChildren().add(btnAddRoom);
    	lstRooms.getItems().add(lstRooms.getItems().size(),p);  	
	}

    @FXML
    void handleMnuAboutClicked(ActionEvent event) {

    }

    @FXML
    void handleMnuAccountCLicked(ActionEvent event) {

    }

    @FXML
    void handleMnuAzioniClicked(ActionEvent event) {

    }

    @FXML
    void handleMnuSpotlightClicked(ActionEvent event) {

    }

    @FXML
	void handleMtmLogOut(ActionEvent event) throws Exception {
    	SceneHandler.getInstance().goToScene("loginInterface.fxml", "Room Editor", 1280, 720);
    }
    
    private EventHandler<MouseEvent> handleBtnAddRoom = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
				
			String nome="";
			int larghezza = 0, profondità = 0;
			
			roomProp.showAndWait();
			Pane p = (Pane) roomProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
			
			if(p.getChildren().get(1).getId().equals("txtRoomName")) {
				TextField txtRoomName = (TextField) p.getChildren().get(1);
				nome = txtRoomName.getText();
				txtRoomName.setText("");
			}

			if(p.getChildren().get(2).getId().equals("txtHeight")) {
				TextField txtHeight = (TextField) p.getChildren().get(2);
				profondità = Integer.parseInt(txtHeight.getText());
				txtHeight.setText("");
			}

			if(p.getChildren().get(3).getId().equals("txtWidth")) {
				TextField txtWidth = (TextField) p.getChildren().get(3);
				larghezza = Integer.parseInt(txtWidth.getText());
				txtWidth.setText("");
			}
			
			//System.out.println(nome + " " + profondità +" " + larghezza);
			
			RoomHandler.getInstance().setProprietario("idManu");
			RoomHandler.getInstance().aggiungiStanza(nome, larghezza, profondità);
			
		}};
	
}



