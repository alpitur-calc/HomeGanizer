package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.SceneHandler;
import application.model.Mobile;
import application.model.RoomHandler;
import application.model.RoomPane;
import application.model.Stanza;
import application.view.MessageView;
import application.view.Piantina;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainInterfaceController implements Initializable {

	private Stanza stanzaSelezionata = null;
	private Button btnAddRoom, btnAddMobile; 
	
    @FXML
    private ListView<RoomPane> lstRooms;

    @FXML
    private ListView<RoomPane> lstFurniture;

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
   
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	//-------- Inizializzazione Lista Stanze (a sx)-----------------
    	RoomPane p = new RoomPane("listaStanze");
    	Label lblListaStanze = new Label("Elenco delle stanze:");
    	lblListaStanze.setFont(new Font(16));
    	
    	p.getChildren().add(lblListaStanze);
    	
		RoomPane pbtn = new RoomPane("buttonAdd");
    	btnAddRoom = new Button();
    	btnAddRoom.setPrefSize(225.0, 35.0);
    	
    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddImage.png"));
    	ImageView imgv= new ImageView(buttonImg);
    	btnAddRoom.setGraphic(imgv);
    	btnAddRoom.setAlignment(Pos.CENTER);
    	btnAddRoom.setOnMousePressed(handleBtnAddRoom);
    	
    	pbtn.getChildren().add(btnAddRoom);

    	lstRooms.getItems().add(lstRooms.getItems().size(),p);  	
    	lstRooms.getItems().add(lstRooms.getItems().size(),pbtn);  	

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
			
			Stage roomProp;
			
			roomProp = new Stage();
			roomProp.setResizable(false);
	    	try {
	    		FXMLLoader loaderRoomProp = new FXMLLoader(getClass().getResource("/application/view/roomPropertiesInterface.fxml"));
	    		AnchorPane rootRoomProp = loaderRoomProp.load();
	    		Scene sceneRoomProp = new Scene(rootRoomProp);
	    		roomProp.setTitle("Inserire dati stanza");
	    		roomProp.setScene(sceneRoomProp);
			} catch (IOException e1) { e1.printStackTrace(); }
	
			roomProp.showAndWait();
			Pane p = (Pane) roomProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
			
			try {
			if(p.getChildren().get(1).getId().equals("txtRoomName")) {
				TextField txtRoomName = (TextField) p.getChildren().get(1);
				nome = txtRoomName.getText();
			}

			if(p.getChildren().get(2).getId().equals("txtHeight")) {
				TextField txtHeight = (TextField) p.getChildren().get(2);
				profondità = Integer.parseInt(txtHeight.getText());
			}

			if(p.getChildren().get(3).getId().equals("txtWidth")) {
				TextField txtWidth = (TextField) p.getChildren().get(3);
				larghezza = Integer.parseInt(txtWidth.getText());
			}
			
			RoomHandler.getInstance().aggiungiStanza(nome, larghezza, profondità);
			addStanzaToList(RoomHandler.getInstance().getStanze().getLast().getId(),nome);
			}
			catch(NumberFormatException e2) {
				MessageView.showMessageAlert(AlertType.WARNING, "Dati inseriti non validi", "Per favore, riprovare e inserire i dati correttamente.");
			}
		}
	};
	
	private void addStanzaToList(String id, String nome) {
		RoomPane p = new RoomPane(id);

		HBox h = new HBox();
		h.setAlignment(Pos.CENTER);
		Image img = new Image(getClass().getResourceAsStream("/resources/StanzaIcon.png"));
		ImageView imgViewIcona = new ImageView(img); 
		Label nomeStanza = new Label(nome);
		nomeStanza.setFont(new Font(14));
		h.getChildren().add(imgViewIcona);
		h.getChildren().add(nomeStanza);
		p.getChildren().add(h);
		p.setOnMouseClicked(handleStanzaInListClicked);
    	lstRooms.getItems().add(lstRooms.getItems().size()-1,p);  
	}
	
	private EventHandler<MouseEvent> handleStanzaInListClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			//----- Prendo la stanza selezionata -----
			LinkedList<Stanza> stanze = RoomHandler.getInstance().getStanze();
			String idStanza= ((RoomPane) event.getSource()).getIdStanza();
			
			for(Stanza S : stanze) {
				if(S.getId().equals(idStanza)) { stanzaSelezionata= S; break; }
			}
			
			//----- Genero la griglia nel canvas -----
			MessageView.showMessageAlert(AlertType.INFORMATION, "Stanza", "Stanza con id: " + stanzaSelezionata.getId());
			Piantina.disegna(cnvRoom, stanzaSelezionata);
			
			//----- Carico la lista Mobili -----
			caricaMobili();
		}
	};
	
	private EventHandler<MouseEvent> handleBtnAddMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			
			String nome="",tipo="";
			
			Stage mobileProp;
			mobileProp = new Stage();
			mobileProp.setResizable(false);
	    	try {
	    		FXMLLoader loaderMobileProp = new FXMLLoader(getClass().getResource("/application/view/mobilePropertiesInterface.fxml"));
	    		AnchorPane rootMobileProp = loaderMobileProp.load();
	    		Scene sceneMobileProp = new Scene(rootMobileProp);
	    		mobileProp.setTitle("Inserire dati mobile");
	    		mobileProp.setScene(sceneMobileProp);
			} catch (IOException e1) { e1.printStackTrace(); }
	
	    	mobileProp.showAndWait();
			Pane p = (Pane) mobileProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
			
			try {
				if(p.getChildren().get(1).getId().equals("txtNome")) {
					TextField txtNome = (TextField) p.getChildren().get(1);
					nome = txtNome.getText();
				}
	
				if(p.getChildren().get(2).getId().equals("cmbTipo")) {
					ComboBox<String> cmbTipo = (ComboBox<String>) p.getChildren().get(2);
					tipo = (String) cmbTipo.getValue();
				}
				
				if(stanzaSelezionata != null && !nome.equals("") && !tipo.equals("")) {
					stanzaSelezionata.aggiungiMobile(nome, tipo); 
					caricaMobili();
				}
			}
			catch(Exception e2) {
				MessageView.showMessageAlert(AlertType.WARNING, "Errore", "Oops, qualcosa è andato storto. Per favore, riprovare.");
			}
		}
	};
	
	private void caricaMobili() {
		//----- Inizializo la lista di mobili (a dx) -----
		lstFurniture.getItems().clear();
		
		RoomPane p = new RoomPane("listMobili");
    	Label lblListaStanze = new Label("Elenco dei mobili:");
    	lblListaStanze.setFont(new Font(16));
    	p.getChildren().add(lblListaStanze);
    	
		RoomPane pbtn = new RoomPane(stanzaSelezionata.getId());
    	btnAddMobile = new Button();
    	btnAddMobile.setPrefSize(205.0, 35.0);
    	btnAddMobile.setOnMouseClicked(handleBtnAddMobile);
    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddMobileImage.png"));
    	ImageView imgv= new ImageView(buttonImg);
    	btnAddMobile.setGraphic(imgv);
    	pbtn.getChildren().add(btnAddMobile);
    	
    	lstFurniture.getItems().add(lstFurniture.getItems().size(),p); 
    	
    	LinkedList<Mobile> mobili = stanzaSelezionata.getMobili();   	
    	for(Mobile m : mobili) {	
    		RoomPane mp = new RoomPane(m.getId());
    		HBox h = new HBox();
    		h.setAlignment(Pos.CENTER);
    		Image img = new Image(getClass().getResourceAsStream("/resources/MobileIcon.png"));
    		ImageView imgViewIcona = new ImageView(img); 
    		Label nomeStanza = new Label(m.getNome()+"("+m.getTipo()+")");
    		nomeStanza.setFont(new Font(14));
    		h.getChildren().add(imgViewIcona);
    		h.getChildren().add(nomeStanza);
    		mp.getChildren().add(h);
    		mp.setOnMouseClicked(handleStanzaInListClicked);
    		lstFurniture.getItems().add(lstFurniture.getItems().size(),mp);  
    	}

    	lstFurniture.getItems().add(lstFurniture.getItems().size(),pbtn);
	}
}
