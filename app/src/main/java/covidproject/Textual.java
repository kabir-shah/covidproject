package covidproject;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

import covidproject.DataVaccinations;

class Textual {
	VBox box;

	public Textual() {
		box = new VBox();
	}

	public VBox getText() {
		return box;
	}

	private Text makeText(String content) {
		Text text = new Text(content);
		text.setFont(Font.font("helvetica", FontWeight.NORMAL, FontPosture.REGULAR, 18));
		return text;
	}

	public void clear() {
		box.getChildren().clear();
	}

	public void vaccinations(ArrayList<DataVaccinations> allDataVaccinations) {
		String content = "";

		// Iterate through vaccination data for all currently selected locations.
		for (int i = 0; i < allDataVaccinations.size(); i++) {
			// Get the vaccination data for the current specific location.
			DataVaccinations dataVaccinations = allDataVaccinations.get(i);

			// Get the y values of the vaccination data, i.e. total vaccination numbers.
			ArrayList<Integer> vaccinations = dataVaccinations.getYValues();

			// Add text to the content that displays the location and the most recent number of
			// total vaccinations.
			content += dataVaccinations.getLocation() + ": " + vaccinations.get(vaccinations.size() - 1) + " vaccinations\n";
		}

		// Clear the box and add a new text element with the content.
		clear();
		box.getChildren().add(makeText(content));
	}
}
