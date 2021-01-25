package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.SceneHandler;
import application.model.Mobile;
import application.model.Oggetto;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainInterfaceController implements Initializable {

	public static boolean ConfermaCreazioneStanza=false, ConfermaCreazioneMobile=false, ConfermaCreazioneOggetto=false;
	private static boolean vista = false;
	
	private static Stanza stanzaSelezionata = null; 
	private	static Mobile mobileSelezionato= null;
	private static Oggetto oggettoSelezionato= null;
	private Button btnAddRoom, btnAddMobile,btnAddOggetto; 	
	private Image stageIcon= new Image(getClass().getResourceAsStream("/resources/homeganizerIcon.png"));
	
    @FXML
    private ListView<RoomPane> lstRooms;

    @FXML
    private ListView<RoomPane> lstFurniture;
    
    @FXML
    private ListView<RoomPane> lstOggetti;

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
    
    @FXML
    private MenuItem mtmCreaStanza;

    @FXML
    private MenuItem mtmEliminaStanza;

    @FXML
    private MenuItem btnAbout;
      
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	//-------- Inizializzazione Lista Stanze (a sx)-----------------
    	caricaStanze();

    	mtmCreaStanza.setOnAction(handleBtnAddRoom);
    	mtmEliminaStanza.setOnAction(handleBtnEliminaStanza);
    	
    	txtObjectDescription.setEditable(false);
    	cnvRoom.setOnMouseClicked(handleMouseClickCanvas);
    	Piantina.getInstance().setCanvas(cnvRoom);
    	
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
    void handleBtnAboutClicked(ActionEvent event) {
    	MessageView.showMessageAlert(AlertType.INFORMATION, "About us", "Homeganizer" + System.lineSeparator()+
    																	"" + System.lineSeparator() +
    																	"Software sviluppato per il corso di Ingegneria del Software" +System.lineSeparator()+
    																	"A.A. 2020/21, Corso di Laurea in Informatica" +System.lineSeparator()+
    																	"" + System.lineSeparator() +
    																	"dagli studenti: " +System.lineSeparator() +
    																	"Domenico Brunetti," + System.lineSeparator() +
    																	"Stefano Bilotta," + System.lineSeparator() +
    																	"Lorenzo Piro," + System.lineSeparator() +
    																	"ed Emanuele Calabretta." + System.lineSeparator());
    }

    @FXML
    void handleMnuSpotlightClicked(ActionEvent event) {

    }

    @FXML
	void handleMtmLogOut(ActionEvent event) throws Exception {
    	SceneHandler.getInstance().goToScene("loginInterface.fxml", "Room Editor", 1280, 720);
    }
    
    private EventHandler<ActionEvent> handleBtnAddRoom = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent e) {
			
			if(!isModVista()) {
				String nome="";
				int larghezza = 0, profondità = 0;
				
				Stage roomProp;
				
				roomProp = new Stage();
				roomProp.setResizable(false);
				roomProp.getIcons().add(stageIcon);
		    	try {
		    		FXMLLoader loaderRoomProp = new FXMLLoader(getClass().getResource("/application/view/roomPropertiesInterface.fxml"));
		    		AnchorPane rootRoomProp = loaderRoomProp.load();
		    		Scene sceneRoomProp = new Scene(rootRoomProp);
		    		roomProp.setTitle("Inserire dati stanza");
		    		roomProp.setScene(sceneRoomProp);
				} catch (IOException e1) { e1.printStackTrace(); }
		
				roomProp.showAndWait();
				Pane p = (Pane) roomProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
				
				if(ConfermaCreazioneStanza) {
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
		
				    	ConfermaCreazioneStanza= false;
					}
					catch(NumberFormatException e2) {
						MessageView.showMessageAlert(AlertType.WARNING, "Dati inseriti non validi", "Per favore, riprovare e inserire i dati correttamente.");
					}
				}
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
	
	private void caricaStanze() {
		lstRooms.getItems().clear();
		
		RoomPane p = new RoomPane("listaStanze");
    	Label lblListaStanze = new Label("Elenco delle stanze:");
    	lblListaStanze.setFont(new Font(16));
    	
    	p.getChildren().add(lblListaStanze);
    	lstRooms.getItems().add(lstRooms.getItems().size(),p); 
    	
    	if(!isModVista()) {
			RoomPane pbtn = new RoomPane("buttonAdd");
	    	btnAddRoom = new Button();
	    	btnAddRoom.setPrefSize(225.0, 35.0);
	    	
	    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddImage.png"));
	    	ImageView imgv= new ImageView(buttonImg);
	    	btnAddRoom.setGraphic(imgv);
	    	btnAddRoom.setAlignment(Pos.CENTER);
	    	btnAddRoom.setOnAction(handleBtnAddRoom);
	    	
	    	pbtn.getChildren().add(btnAddRoom);
	 	
	    	lstRooms.getItems().add(lstRooms.getItems().size(),pbtn); 
    	}
    	
		for(Stanza s : RoomHandler.getInstance().getStanze()) {
			addStanzaToList(s.getId(),s.getNome());
		}
	}
	
	private EventHandler<MouseEvent> handleStanzaInListClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			
			lstFurniture.getItems().clear();
			lstOggetti.getItems().clear();
			
			//----- Prendo la stanza selezionata -----
			LinkedList<Stanza> stanze = RoomHandler.getInstance().getStanze();
			String idStanza= ((RoomPane) event.getSource()).getIdStanza();
			
			for(Stanza S : stanze) {
				if(S.getId().equals(idStanza)) { stanzaSelezionata= S; break; }
			}
			
			//----- Genero la griglia nel canvas -----
			Piantina.getInstance().disegna();
			
			//----- Carico la lista di mobili -----
			caricaMobili();
		}
	};
	
	private EventHandler<ActionEvent> handleBtnEliminaStanza = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			try {
				if(!isModVista()) {
					RoomHandler.getInstance().rimuoviStanza(stanzaSelezionata.getId());
					lstFurniture.getItems().clear();
					lstOggetti.getItems().clear();
					txtObjectDescription.setText("");
					setStanzaCorrente(null);
					caricaStanze();
					Piantina.getInstance().clear();
					Piantina.getInstance().disegna();
				}
			} catch (Exception e) {
				MessageView.showMessageAlert(AlertType.WARNING, "Attenzione", "Nessuna stanza selezionata");
			}
		}
		
	};
	
	private EventHandler<MouseEvent> handleBtnAddMobile = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			
			String nome="",tipo="";
			
			Stage mobileProp;
			mobileProp = new Stage();
			mobileProp.setResizable(false);
			mobileProp.getIcons().add(stageIcon);
	    	try {
	    		FXMLLoader loaderMobileProp = new FXMLLoader(getClass().getResource("/application/view/mobilePropertiesInterface.fxml"));
	    		AnchorPane rootMobileProp = loaderMobileProp.load();
	    		Scene sceneMobileProp = new Scene(rootMobileProp);
	    		mobileProp.setTitle("Inserire dati mobile");
	    		mobileProp.setScene(sceneMobileProp);
			} catch (IOException e1) { e1.printStackTrace(); }
	
	    	mobileProp.showAndWait();
			Pane p = (Pane) mobileProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
			
			if(ConfermaCreazioneMobile) {
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
						Piantina.getInstance().disegna();
					}
					ConfermaCreazioneMobile= false;
				}
				catch(Exception e2) {
					MessageView.showMessageAlert(AlertType.WARNING, "Errore", "Oops, qualcosa è andato storto. Per favore, riprovare.");
				}
			}
		}
	};
	
	private void caricaMobili() {
		//----- Inizializo la lista di mobili (a dx) -----
		lstFurniture.getItems().clear();
		
		RoomPane p = new RoomPane("listMobili");
    	Label lblListaMobili = new Label("Elenco dei mobili:");
    	lblListaMobili.setFont(new Font(16));
    	p.getChildren().add(lblListaMobili);
    	lstFurniture.getItems().add(lstFurniture.getItems().size(),p); 
    	
    	if(!isModVista()) {
			RoomPane pbtn = new RoomPane(stanzaSelezionata.getId());
	    	btnAddMobile = new Button();
	    	btnAddMobile.setPrefSize(205.0, 35.0);
	    	btnAddMobile.setOnMouseClicked(handleBtnAddMobile);
	    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddMobileImage.png"));
	    	ImageView imgv= new ImageView(buttonImg);
	    	btnAddMobile.setGraphic(imgv);
	    	pbtn.getChildren().add(btnAddMobile);
	    	lstFurniture.getItems().add(lstFurniture.getItems().size(),pbtn);
    	}
    	
    	LinkedList<Mobile> mobili = stanzaSelezionata.getMobili();   	
    	for(Mobile m : mobili) {	
    		RoomPane mp = new RoomPane(m.getId());
    		HBox h = new HBox();
    		h.setAlignment(Pos.CENTER);
    		Image img = new Image(getClass().getResourceAsStream("/resources/iconeMobili/"+ m.getIcona() +".png"));
    		ImageView imgViewIcona = new ImageView(img); 
    		Label nomeMobile = new Label(m.getNome()+"("+m.getTipo()+")");
    		nomeMobile.setFont(new Font(14));
    		h.getChildren().add(imgViewIcona);
    		h.getChildren().add(nomeMobile);
    		mp.getChildren().add(h);
    		mp.setOnMouseClicked(handleMobileInListClicked);
    		lstFurniture.getItems().add(lstFurniture.getItems().size()-1,mp);  
    	}

    	
	}
	
	private EventHandler<MouseEvent> handleMobileInListClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent e) {
			
			//----- Prendo il mobile selezionato -----
			String idMobile=  ((RoomPane)e.getSource()).getIdStanza();
			LinkedList<Mobile> mobili = stanzaSelezionata.getMobili();
			
			for(Mobile M : mobili) {
				if(M.getId().equals(idMobile)) { mobileSelezionato= M; break; }
			}
			
			//----- Carico la lista di oggetti -----
			if(mobileSelezionato != null) {
				Piantina.getInstance().deselezionaMobile();
				Piantina.getInstance().evidenziaMobile();
				caricaOggetti(mobileSelezionato);
				}
			}
	};
	
	private void caricaOggetti(Mobile M) {
		lstOggetti.getItems().clear();
		
		RoomPane p = new RoomPane("listOggetti");
    	Label lblListaOggetti = new Label("Elenco degli oggetti:");
    	lblListaOggetti.setFont(new Font(14));
    	p.getChildren().add(lblListaOggetti);
    	lstOggetti.getItems().add(lstOggetti.getItems().size(),p);
    	
    	if(!isModVista()) {
			RoomPane pbtn = new RoomPane(stanzaSelezionata.getId());
	    	btnAddOggetto = new Button();
	    	btnAddOggetto.setPrefSize(205.0, 20.0);
	    	btnAddOggetto.setOnMouseClicked(handleBtnAddOggetto);
	    	Image buttonImg =  new Image(getClass().getResourceAsStream("/resources/ButtonAddOggettoImage.png"));
	    	ImageView imgv= new ImageView(buttonImg);
	    	btnAddOggetto.setGraphic(imgv);
	    	pbtn.getChildren().add(btnAddOggetto);
	    	
			lstOggetti.getItems().add(lstOggetti.getItems().size(),pbtn);
		}
		
    	LinkedList<Oggetto> oggetti = M.getOggetti();   	
    	for(Oggetto o : oggetti) {	
    		RoomPane mp = new RoomPane(o.getId());
    		HBox h = new HBox();
    		h.setAlignment(Pos.CENTER);
    		Image img = new Image(getClass().getResourceAsStream("/resources/iconeOggetti/"+ o.getIcona()+ ".png"));
    		ImageView imgViewIcona = new ImageView(img); 
    		Label nomeOggetto = new Label(o.getNome()+"("+o.getTipo()+")");
    		nomeOggetto.setFont(new Font(14));
    		h.getChildren().add(imgViewIcona);
    		h.getChildren().add(nomeOggetto);
    		mp.getChildren().add(h);
    		mp.setOnMouseClicked(handleOggettoInListClicked);
    		lstOggetti.getItems().add(lstOggetti.getItems().size(),mp);  
    	}

		
	}
	
	private EventHandler<MouseEvent> handleOggettoInListClicked = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			//----- Prendo il mobile selezionato -----
			String idMobile=  ((RoomPane)event.getSource()).getIdStanza();
			LinkedList<Oggetto> oggetti = mobileSelezionato.getOggetti();
			
			for(Oggetto O : oggetti) {
				if(O.getId().equals(idMobile)) { oggettoSelezionato= O; break; }
			}
			
			//----- Carico la lista di oggetti -----
			caricaOggettoSelezionato(oggettoSelezionato);
		}
	};
	
	private EventHandler<MouseEvent> handleBtnAddOggetto = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			String nome="", tipo="", descrizione="";
			
			Stage oggettoProp;
			oggettoProp = new Stage();
			oggettoProp.setResizable(false);
			oggettoProp.getIcons().add(stageIcon);
	    	try {
	    		FXMLLoader loaderOggettoProp = new FXMLLoader(getClass().getResource("/application/view/oggettoPropertiesInterface.fxml"));
	    		AnchorPane rootOggettoProp = loaderOggettoProp.load();
	    		Scene sceneOggettoProp = new Scene(rootOggettoProp);
	    		oggettoProp.setTitle("Inserire dati mobile");
	    		oggettoProp.setScene(sceneOggettoProp);
			} catch (IOException e1) { e1.printStackTrace(); }
	
	    	oggettoProp.showAndWait();
			Pane p = (Pane) oggettoProp.getScene().getRoot().getChildrenUnmodifiable().get(0);
			
			if(ConfermaCreazioneOggetto) {
				try {
					if(p.getChildren().get(1).getId().equals("txtNome")) {
						TextField txtNome = (TextField) p.getChildren().get(1);
						nome = txtNome.getText();
					}
		
					if(p.getChildren().get(2).getId().equals("cmbTipo")) {
						ComboBox<String> cmbTipo = (ComboBox<String>) p.getChildren().get(2);
						tipo = (String) cmbTipo.getValue();
					}
					
					if(p.getChildren().get(5).getId().equals("txtDescrizione")) {
						TextArea txtDescrizione = (TextArea) p.getChildren().get(5);
						descrizione = txtDescrizione.getText();
					}
					
					if(stanzaSelezionata != null && !nome.equals("") && !tipo.equals("")) {
						mobileSelezionato.aggiungiOggetto(nome, descrizione, tipo);
						caricaOggetti(mobileSelezionato);
					}
					ConfermaCreazioneOggetto= false;
				}
				catch(Exception e2) {
					MessageView.showMessageAlert(AlertType.WARNING, "Errore", "Oops, qualcosa è andato storto. Per favore, riprovare.");
				}
			}
		}
	};
	
	private void caricaOggettoSelezionato(Oggetto O) {
		
		txtObjectDescription.clear();
		txtObjectDescription.setFont(new Font(16));
		String testo = "Nome Oggetto: " + O.getNome() + System.lineSeparator() +
					   "Tipo Oggetto: " + O.getTipo() + System.lineSeparator() +
					   "Descrizione: " + O.getDescrizione();
		txtObjectDescription.setText(testo);
		
	}
	
	@FXML
    void ricercaMobile(KeyEvent event) {
		if( txtSpotlight.isFocused() &&!txtSpotlight.getText().equals("")) {
			if(event.getCode().equals(KeyCode.ENTER)) {
				if(RoomHandler.getInstance().ricerca(txtSpotlight.getText())) {
					int cont = 1;
					for(RoomPane r : lstRooms.getItems()) {
						if(r.getIdStanza().equals(stanzaSelezionata.getId())) {
							lstRooms.getSelectionModel().select(cont);
							caricaMobili();
							int cont2 = 0;
							for(RoomPane r2 : lstFurniture.getItems()) {
								if(r2.getIdStanza().equals(mobileSelezionato.getId())) {
									for(Mobile m : getStanzaCorrente().getMobili()) {
										if(m.getId().equals(mobileSelezionato.getId())) {
											lstFurniture.getSelectionModel().select(cont2);
											caricaOggetti(mobileSelezionato);
											int cont3 = 0;
											//Da qui in poi non so cosa ho fatto
											//Funziona però non mostra la descrizione
											for(RoomPane r3 : lstOggetti.getItems()) {
												for(Oggetto o : getMobileCorrente().getOggetti()) {
													if(o.getNome().equals(txtSpotlight.getText())) {
														lstOggetti.getSelectionModel().select(cont3);
														txtObjectDescription.getText(); // questo è il maledetto
													}
												}
												cont3 ++;
											}
										}
									}
								}
								cont2 ++;
							}
						}	
					}
					cont ++;
				}
			}		
		}
    }
	
	public static void setStanzaCorrente(Stanza stanza) {
		stanzaSelezionata = stanza;
		Piantina.getInstance().disegna();
	}
	
	public static void setMobileCorrente(Mobile mobile) {
		mobileSelezionato = mobile;
	}
	
	public static Stanza getStanzaCorrente() {
		return stanzaSelezionata;
	}
	
	public static Mobile getMobileCorrente() {
		return mobileSelezionato;
	}
	
	public static void setModVista(boolean mod) {
		vista= mod;
	}
	
	public static boolean isModVista() {
		return vista;
	}
		
	private EventHandler<MouseEvent> handleMouseClickCanvas = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			if(mobileSelezionato == null) {
				lstFurniture.getSelectionModel().clearSelection();
				lstOggetti.getItems().clear();
			}
			else if(mobileSelezionato != null) {
				int cont = 0;
				for(RoomPane r : lstFurniture.getItems()) {
					if(r.getIdStanza().equals(mobileSelezionato.getId())) {
						for(Mobile m : getStanzaCorrente().getMobili()) {
							if(m.getId().equals(mobileSelezionato.getId())) {
								lstFurniture.getSelectionModel().select(cont);
								caricaOggetti(mobileSelezionato);
							}
						}
					}
					cont ++;
				}
			}
		}
		
	};
}