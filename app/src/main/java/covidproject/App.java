package covidproject;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import covidproject.Data;
import covidproject.DataVaccinations;
import covidproject.DataVaccinationsPerHundred;
import covidproject.DataPeopleVaccinated;
import covidproject.DataDailyVaccinations;
import covidproject.GraphTest;
import covidproject.Textual;

public class App extends Application {
	/**
	 * Start the application given a stage and launch the UI.
	 * @param {Stage} stage
	 */
	public void start(Stage stage) {
		// Store graph and text element classes for managing them and their state.
		GraphTest graphs = new GraphTest();
		Textual texts = new Textual();

		// Store states of all vaccination sets that the user is currently visualizing or working with.
		ArrayList<DataVaccinations> allDataVaccinations = new ArrayList<DataVaccinations>();
		ArrayList<DataVaccinationsPerHundred> allDataVaccinationsPerHundred = new ArrayList<DataVaccinationsPerHundred>();
		ArrayList<DataPeopleVaccinated> allDataPeopleVaccinated = new ArrayList<DataPeopleVaccinated>();
		ArrayList<DataDailyVaccinations> allDataDailyVaccinations = new ArrayList<DataDailyVaccinations>();
		ArrayList<DataDailyVaccinationsPerM> allDataDailyVaccinationsPerM = new ArrayList<DataDailyVaccinationsPerM>();

		// Create title.
		Text title = new Text("Covid Project");
		title.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 30));

		// Create text for basic UI feedback like errors or success.
		Text output = new Text("");
		output.setFont(Font.font("helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 12));

		// Create a text input with an event handler for when the user hits enter.
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
					allDataVaccinations.clear();
					allDataVaccinationsPerHundred.clear();
					allDataPeopleVaccinated.clear();
					allDataDailyVaccinations.clear();
					allDataDailyVaccinationsPerM.clear();
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

					graphs.vaccinations(allDataVaccinations);
					texts.vaccinations(allDataVaccinations);
				} else if (command.equals("vaccinations-per-hundred")) {
					if (args.length == 0) {
						allDataVaccinationsPerHundred.add(Data.vaccinationsPerHundred("United States"));
					} else {
						for (String location : args) {
							allDataVaccinationsPerHundred.add(Data.vaccinationsPerHundred(location));
						}
					}

					graphs.vaccinationsPerHundred(allDataVaccinationsPerHundred);
					texts.vaccinationsPerHundred(allDataVaccinationsPerHundred);
				} else if (command.equals("people-vaccinated")) {
					if (args.length == 0) {
						allDataPeopleVaccinated.add(Data.peopleVaccinated("United States"));
					} else {
						for (String location : args) {
							allDataPeopleVaccinated.add(Data.peopleVaccinated(location));
						}
					}

					graphs.peopleVaccinated(allDataPeopleVaccinated);
					texts.peopleVaccinated(allDataPeopleVaccinated);
				} else if (command.equals("daily-vaccinations")) {
					if (args.length == 0) {
						allDataDailyVaccinations.add(Data.dailyVaccinations("United States"));
					} else {
						for (String location : args) {
							allDataDailyVaccinations.add(Data.dailyVaccinations(location));
						}
					}

					graphs.dailyVaccinations(allDataDailyVaccinations);
					texts.dailyVaccinations(allDataDailyVaccinations);
				} else if (command.equals("daily-vaccinations-per-million")) {
					if (args.length == 0) {
						allDataDailyVaccinationsPerM.add(Data.dailyVaccinationsPerM("United States"));
					} else {
						for (String location : args) {
							allDataDailyVaccinationsPerM.add(Data.dailyVaccinationsPerM(location));
						}
					}

					graphs.dailyVaccinationsPerM(allDataDailyVaccinationsPerM);
					texts.dailyVaccinationsPerM(allDataDailyVaccinationsPerM);
				} else {
					output.setText("Command not recognized, your command was: " + command);
				}
			}
		});

		// Create a vertical box that holds the title, input, UI output, text output, and graph output.
		VBox box = new VBox(8, title, input, output, texts.getText(), graphs.getGraph());

		// Create a stack that places the vertical box within some margins.
		StackPane stack = new StackPane(box);
		stack.setMargin(box, new Insets(30));

		// Make a scrollpane that allows the user to scroll.
		ScrollPane pane = new ScrollPane();
		pane.setPrefSize(1000, 750);
		pane.setFitToWidth(true);
		pane.setContent(stack);

		// Make the final window and show it.
		Scene scene = new Scene(pane, 1000, 750);
		stage.setTitle("Covid Project");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch (args);
	}
}
