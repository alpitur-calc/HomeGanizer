package application;

import application.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneHandler {
	private Stage stage;
	private Scene scene;

	private static SceneHandler instance = null;

	public static SceneHandler getInstance() {
		if (instance == null)
			instance = new SceneHandler();
		return instance;
	}

	private SceneHandler() {
	}

	public void init(Stage primaryStage) throws Exception {
		stage = primaryStage;
		String filename = "loginInterface.fxml";
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/" + filename));
		Parent root = (Parent) loader.load();
		Image stageIcon= new Image(getClass().getResourceAsStream("/resources/homeganizerIcon.png"));
		scene = new Scene(root);
		stage.setWidth(1280);
		stage.setHeight(720);
		stage.setScene(scene);
		stage.setTitle("Homeganizer Login");
		stage.getIcons().add(stageIcon);
		stage.setResizable(false);
		LoginController l = loader.<LoginController>getController();
		l.init();
		stage.show();

	}

	public void goToScene(String filename, String title, int x, int y) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/view/" + filename));
		Parent root = (Parent) loader.load();
		scene.setRoot(root);
		stage.setTitle(title);
		stage.setWidth(x);
		stage.setHeight(y);
		stage.setResizable(false);
		stage.show();
	}
}
