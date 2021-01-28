package covidproject;

import java.util.ArrayList;
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
import covidproject.DataVaccinations;

public class App extends Application {
	public void start(Stage stage) {
		GraphTest lineGraph = new GraphTest (new ArrayList<Integer>(), new ArrayList<Integer>());
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
				} else if (command.equals("vaccinations")) {
					DataVaccinations dataVaccinations = Data.vaccinations();
					lineGraph.updateGraph(dataVaccinations.getXValues(), dataVaccinations.getYValues());
					System.out.println(dataVaccinations.getYValues().get(0));
				} else {
					output.setText("Your command was: " + command);
				}
			}
		});

		VBox box = new VBox(8, title, lineGraph.getGraph(), output, input);
		box.setMargin(title, new Insets(0, 0, 300, 0));

		StackPane stack = new StackPane(box);
		stack.setMargin(box, new Insets(30));

		Scene scene = new Scene(stack, 640, 480);
		stage.setTitle("Covid Project");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch (args);
	}
}
