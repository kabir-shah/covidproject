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
import covidproject.DataVaccinationsPerHundred;
import covidproject.GraphTest;
import covidproject.Textual;

public class App extends Application {
	public void start(Stage stage) {
		GraphTest graphs = new GraphTest();
		Textual texts = new Textual();
		ArrayList<DataVaccinations> allDataVaccinations = new ArrayList<DataVaccinations>();
		ArrayList<DataVaccinationsPerHundred> allDataVaccinationsPerHundred = new ArrayList<DataVaccinationsPerHundred>();

		Text title = new Text("Covid Project");
		title.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 30));

		Text output = new Text("");
		output.setFont(Font.font("helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));

		TextField input = new TextField("Enter a command");
		input.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String inputText = input.getText();
				String[] inputTextParts = inputText.split(" ");
				String command = inputTextParts[0];
				String[] args = inputTextParts.length > 1 ?
					inputText.substring(command.length() + 1).split(",") :
					new String[]{};

				// Clear old output.
				output.setText("");

				if (command.equals("clear")) {
					graphs.clearBox();
					texts.clear();
				} else if (command.equals("update")) {
					Data.update();
					output.setText("Data successfully updated.");
				} else if (command.equals("vaccinations")) {
					if (args.length == 0) {
						allDataVaccinations.add(Data.vaccinations("United States"));
					} else {
						for (String location : args) {
							allDataVaccinations.add(Data.vaccinations(location));
						}
					}

					graphs.updateGraph(allDataVaccinations);
					texts.vaccinations(allDataVaccinations);
				} else if (command.equals("vaccinations-per-hundred")) {
					if (args.length == 0) {
						allDataVaccinationsPerHundred.add(Data.vaccinationsPerHundred("United States"));
					} else {
						for (String location : args) {
							allDataVaccinationsPerHundred.add(Data.vaccinationsPerHundred(location));
						}
					}

					// TODO: add textual for this
					graphs.updateGraph(allDataVaccinationsPerHundred);
					// texts.vaccinations(allDataVaccinationsPerHundred);
				} else {
					output.setText("Command not recognized, your command was: " + command);
				}
			}
		});

		VBox box = new VBox(8, title, input, output, texts.getText(), graphs.getGraph());

		StackPane stack = new StackPane(box);
		stack.setMargin(box, new Insets(30));

		Scene scene = new Scene(stack, 1000, 750);
		stage.setTitle("Covid Project");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch (args);
	}
}
