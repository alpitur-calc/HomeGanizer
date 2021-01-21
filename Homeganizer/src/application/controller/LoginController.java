package application.controller;

import application.SceneHandler;
import application.model.DatabaseHandler;
import application.model.LoginHandler;
import application.model.MemorizedUserPassword;
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
    private void handleBtnLoginPressed(MouseEvent e) throws Exception {
    	if (LoginHandler.login(txtUsername.getText(), txtPassword.getText(),chkRemember.isSelected()))
    	SceneHandler.getInstance().goToScene("mainInterface.fxml", "Room Editor", 1280, 720);
    }
    
    //Funzione che gestisce la pressione del tasto Register
    @FXML
    private void handleBtnRegisterPressed(MouseEvent e) throws Exception {
    	SceneHandler.getInstance().goToScene("registerInterface.fxml", "Homeganizer Login", 1280, 720);
    }
    
    //Funzione che gestisce la pressione sulla Label PasswordDimenticata
    @FXML
    private void handlelblForgotPasswordClicked(MouseEvent e) throws Exception {
    	SceneHandler.getInstance().goToScene("passwordChangeInterface.fxml", "Homeganizer Login", 1280, 720);
    }

	public void init() {
		MemorizedUserPassword mup = DatabaseHandler.getInstance().getMemorizedUser();
		if(mup != null) {
			chkRemember.setSelected(true);
			txtUsername.appendText(mup.getUsername());
			txtPassword.appendText(mup.getPassword());
		}
	}
    
}