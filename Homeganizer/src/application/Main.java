package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("logInterface.fxml"));
			Parent root =  loader.load();
			Scene scene = new Scene(root, 650, 456);
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(400);
			primaryStage.setTitle("Homeganizer");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
