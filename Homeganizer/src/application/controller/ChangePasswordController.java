package application.controller;

import application.SceneHandler;
import application.model.RegisterHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ChangePasswordController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private Button btnChangePassword;

    @FXML
    private PasswordField txtNewPassword2;

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
    void handleBtnRegisterPressed(MouseEvent event) throws Exception {
    	RegisterHandler.changePassword(txtUsername.getText(), txtNewPassword.getText(), txtNewPassword2.getText(), txtAnswer.getText());
    }

}
