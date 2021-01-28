package covidproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	public void start(Stage stage) {
		String javaVersion = System.getProperty("java.version");
		String javafxVersion = System.getProperty("javafx.version");
		Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
		Scene scene = new Scene(new StackPane(l), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Stage stage1;
		GraphTest test1 = new GraphTest (1, 750);
		launch (args);
	}
}
