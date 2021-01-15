package application;

import application.model.DatabaseHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneHandler.getInstance().init(primaryStage);
		DatabaseHandler.getInstance(); //genera istanza di DatabaseHandler per evitare caricamenti
	}

	public static void main(String[] args) {
		launch(args);
	}
}