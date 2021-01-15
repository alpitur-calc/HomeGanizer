package application.controller;

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
    void handleBtnRegisterPressed(MouseEvent event) {

    }

}
