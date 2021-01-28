package covidproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import covidproject.Data;

public class App extends Application {
	public void start(Stage stage) {
		Text title = new Text("Covid Project");
		title.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 30));

		Text output = new Text("");
		output.setFont(Font.font("helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));

		TextField input = new TextField("Enter a command");
		input.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String command = input.getText();

				if (command.equals("update")) {
					Data.update();
				} else if (command.equals("cases")) {
					Data.cases();
				} else {
					output.setText("Your command was: " + command);
				}
			}
		});

		VBox box = new VBox(8, title, output, input);
		box.setMargin(title, new Insets(0, 0, 300, 0));

		StackPane stack = new StackPane(box);
		stack.setMargin(box, new Insets(30));

		Scene scene = new Scene(stack, 640, 480);
		stage.setTitle("Covid Project");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}
