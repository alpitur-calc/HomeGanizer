package application.controller;

import application.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainInterfaceController {

    @FXML
    private ListView<?> lstRooms;

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
    
    

}



