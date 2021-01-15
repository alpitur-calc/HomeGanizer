package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnRegister;
<<<<<<< HEAD

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private TextField txtAnswer;

    @FXML
    private Label txtQuestion;

    @FXML
    void handleBtnRegisterPressed(MouseEvent event) {
    	
=======
    
    
    //Funzione che gestisce la pressione del tasto Login
    @FXML
    private void handleBtnLoginPressed(MouseEvent e) {
    	//fai il login
    }
    
    //Funzione che gestisce la pressione del tasto Register
    @FXML
    private void handleBtnRegisterPressed(MouseEvent e) {
    	//vai alla pagina di registrazione
    }
    
    //Funzione che gestisce la pressione sulla Label PasswordDimenticata
    @FXML
    private void handlelblForgotPasswordClicked(MouseEvent e) {
    	//vai alla pagina di recupero password
>>>>>>> branch 'main' of https://github.com/alpitur-calc/progettoingsw
    }
    
    ​​

}
