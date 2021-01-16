package application.controller;

import application.SceneHandler;
import application.model.RegisterHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegisterController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField txtPassword2;

    @FXML
    private TextField txtAnswer;

    @FXML
    private Label txtQuestion;
    
    @FXML
    private Button btnBack;

    @FXML
    private void handleBtnBackPressed(MouseEvent event) throws Exception {
    	SceneHandler.getInstance().goToScene("loginInterface.fxml", "Homeganizer Login", 1280, 720);
    }

    @FXML
    private void handleBtnRegisterPressed(MouseEvent event) {
    	RegisterHandler.createUser(txtUsername.getText(), txtPassword.getText(), txtPassword2.getText(), txtAnswer.getText());
    }

}
