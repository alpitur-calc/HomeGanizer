package application.controller;

import application.SceneHandler;
import application.model.LoginHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private CheckBox chkRemember;

    @FXML
    private Label lblForgotpassword;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;
 
    @FXML
    private Button btnRegister;
    
    
    //Funzione che gestisce la pressione del tasto Login
    @FXML
    private void handleBtnLoginPressed(MouseEvent e) {
    	LoginHandler.login(txtUsername.getText(), txtPassword.getText());
    }
    
    //Funzione che gestisce la pressione del tasto Register
    @FXML
    private void handleBtnRegisterPressed(MouseEvent e) {
    	SceneHandler.getInstance().goToScene("registerInterface.fxml", "Boh", 1280, 720);
    }
    
    //Funzione che gestisce la pressione sulla Label PasswordDimenticata
    @FXML
    private void handlelblForgotPasswordClicked(MouseEvent e) {
    	
    	//vai alla pagina di recupero password
    }
    
}